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

    //not used right now
    public void requestParts(String part){
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        FactoryRestockRequest request = FactoryRestockRequest.newBuilder().setText(part).build();
        FactoryRestockReply reply = blockStub.restockFactory(request);
        restockFactory(part, Integer.parseInt(reply.getText()));
        System.out.println("Receiving new stock of " + part +"'s!");
    }

    public void requestParts(String[] parts) {
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        FactoryRestockRequest request = FactoryRestockRequest.newBuilder().build();
        FactoryRestockReply reply = blockStub.restockFactory(request);
        System.out.println("parts! " + reply.getText());
    }

    public void requestPartsStream(ArrayList<String> parts){
        asyncStub = LexingCoWarehouseServiceGrpc.newStub(channel);

        StreamObserver<FactoryRestockReply> responseObserver = new StreamObserver<FactoryRestockReply>() {
            @Override
            public void onNext(FactoryRestockReply restockReply) {
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

        StreamObserver<FactoryRestockRequest> requestObserver = asyncStub.restockFactoryStream(responseObserver);
        try{
            for(String part : parts){
                requestObserver.onNext(FactoryRestockRequest.newBuilder().setText(part).build());
                restockFactory(part, 2);
                Thread.sleep(500);
            }
            requestObserver.onCompleted();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void repairPartsBiDiStream(String[] parts) {
        asyncStub = LexingCoWarehouseServiceGrpc.newStub(channel);

        StreamObserver<FactoryRestockReply> responseObserver = new StreamObserver<FactoryRestockReply>() {

            @Override
            public void onNext(FactoryRestockReply factoryRestockReply) {
                System.out.println("Replacement part for " + factoryRestockReply.getText() + "sourced.");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Client onCompleted here");
            }
        };

        StreamObserver<FactoryRestockRequest> requestObserver = asyncStub.repairStockFactoryStream(responseObserver);
        try{
            for(String part : parts){
                requestObserver.onNext(FactoryRestockRequest.newBuilder().setText(part).build());
            }
            requestObserver.onCompleted();
            Thread.sleep(500);
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
