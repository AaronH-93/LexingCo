package grpc.services.LexingCoFactory;

import grpc.services.LexingCoWarehouse.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class FactoryServiceClient extends FactoryServer implements ServiceListener {

    private static final FactoryServiceClient FACTORY_SERVICE_CLIENT = new FactoryServiceClient();
    private static int warehousePort = 50053;
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", warehousePort).usePlaintext().build();
    private static LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceBlockingStub blockStub;
    private static LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceStub asyncStub;


    public static void main(String[] args) {
        try {
            //Creates a JmDNS instance of the warehouse service type.
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            String service_type = "_warehouse._tcp.local.";
            jmdns.addServiceListener(service_type, FACTORY_SERVICE_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serviceAdded(ServiceEvent event) { System.out.println("Service added: " + event.getInfo()); }

    @Override
    public void serviceRemoved(ServiceEvent event) { System.out.println("Service removed: " + event.getInfo()); }

    @Override
    public void serviceResolved(ServiceEvent event) { System.out.println("Service resolved: " + event.getInfo()); }

    //requestPartsStream calls the Client Side rpc stream in the warehouse service.
    //It sends a message for each car part that needs to be restocked and receives a quantity to restock.
    public void requestPartsStream(ArrayList<String> parts){
        asyncStub = LexingCoWarehouseServiceGrpc.newStub(channel);

        StreamObserver<RestockReply> responseObserver = new StreamObserver<RestockReply>() {
            @Override
            public void onNext(RestockReply restockReply) {
                System.out.println("Receiving new stock of " + restockReply.getText());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream complete, accepting stock.");
            }
        };

        StreamObserver<RestockRequest> requestObserver = asyncStub.restockFactoryStream(responseObserver);
        try{
            for(String part : parts){
                requestObserver.onNext(RestockRequest.newBuilder().setText(part).build());
                restockFactory(part, 2);
                Thread.sleep(500);
            }
            requestObserver.onCompleted();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //repairPartsBiDiStream is a bidirectional stream rpc message for the repair car implementation.
    //It sends a message for each part that needs to be repaired/replaced
    //and receives a message per message it sends.
    //Since the replacement parts are sourced directly from the warehouse,
    //there is no consideration for factory storage
    public void repairPartsBiDiStream(ArrayList<String> parts) {
        asyncStub = LexingCoWarehouseServiceGrpc.newStub(channel);

        StreamObserver<RestockReply> responseObserver = new StreamObserver<RestockReply>() {

            @Override
            public void onNext(RestockReply factoryRestockReply) {
                System.out.println("Replacement parts for: " + factoryRestockReply.getText() + "\nsourced.");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Delivery received.");
            }
        };

        StreamObserver<RestockRequest> requestObserver = asyncStub.repairStockFactoryStream(responseObserver);
        try{
            for(String part : parts){
                requestObserver.onNext(RestockRequest.newBuilder().setText(part).build());
            }
            requestObserver.onCompleted();
            Thread.sleep(500);
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
