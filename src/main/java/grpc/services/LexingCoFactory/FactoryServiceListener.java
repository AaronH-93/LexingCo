package grpc.services.LexingCoFactory;

import grpc.services.LexingCoOrdering.StockReply;
import grpc.services.LexingCoOrdering.StockRequest;
import grpc.services.LexingCoWarehouse.LexingCoWarehouseServiceGrpc;
import grpc.services.LexingCoWarehouse.RestockReply;
import grpc.services.LexingCoWarehouse.RestockRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class FactoryServiceListener extends FactoryServer implements ServiceListener {

    private static final FactoryServiceListener factoryServiceListener = new FactoryServiceListener();
    private static int warehousePort = 50053;
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", warehousePort).usePlaintext().build();
    private static LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceBlockingStub blockStub;
    private static LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceStub asyncStub;


    public static void main(String[] args) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            String service_type = "_warehouse._tcp.local.";
            jmdns.addServiceListener(service_type, factoryServiceListener);
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

    public void requestParts(String part){
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        RestockRequest request = RestockRequest.newBuilder().setText(part).build();
        RestockReply reply = blockStub.restockFactory(request);
        restockFactory(part, Integer.parseInt(reply.getText()));
        System.out.println("Receiving new stock of " + part +"'s!");
    }

    public void requestParts(String[] parts) {
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        RestockRequest request = RestockRequest.newBuilder().build();
        RestockReply reply = blockStub.restockFactory(request);
        System.out.println("parts! " + reply.getText());
    }

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
}
