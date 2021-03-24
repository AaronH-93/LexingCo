package grpc.services.LexingCoWarehouse;

import grpc.services.LexingCoOrdering.LexingCoOrderingServiceGrpc;
import grpc.services.LexingCoOrdering.OrderRestockReply;
import grpc.services.LexingCoOrdering.WarehouseRestockRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import models.CarPart;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;

public class WarehouseServiceListener extends WarehouseServer implements ServiceListener {

    private static final WarehouseServiceListener warehouseServiceListener = new WarehouseServiceListener();
    private static int orderingPort = 50052;
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", orderingPort).usePlaintext().build();
    private static LexingCoOrderingServiceGrpc.LexingCoOrderingServiceBlockingStub blockStub;
    private static LexingCoOrderingServiceGrpc.LexingCoOrderingServiceStub asyncStub;

    public static void main(String[] args) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            String service_type = "_ordering._tcp.local.";
            jmdns.addServiceListener(service_type, warehouseServiceListener);
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

    //Not used right now
    public void orderParts(String part){
        blockStub = LexingCoOrderingServiceGrpc.newBlockingStub(channel);
        WarehouseRestockRequest request = WarehouseRestockRequest.newBuilder().setText(part).build();
        OrderRestockReply reply = blockStub.orderStock(request);
        restockWarehouse(part, Integer.parseInt(reply.getText()));
        System.out.println("ordering parts! " + reply.getText());
    }

    public void orderPartsServerStream(String partsToOrder){
        asyncStub = LexingCoOrderingServiceGrpc.newStub(channel);
        WarehouseRestockRequest request = WarehouseRestockRequest.newBuilder().setText(partsToOrder).build();

        StreamObserver<OrderRestockReply> responseObserver = new StreamObserver<OrderRestockReply>() {
            @Override
            public void onNext(OrderRestockReply orderRestockReply) {
                System.out.println("Received new stock of: " + orderRestockReply.getText());
                //add method that adds stock to warehouse based on this reply.
                //maybe make the next two lines a function in the service?
                String[] reply = orderRestockReply.getText().split("\n");
                //not working right now because the response is only sending the quantity
                CarPart part = new CarPart(reply[0], Integer.parseInt(reply[1]));
                restockWarehouseWithPart(part);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("All parts delivered to warehouse.");
            }
        };
        asyncStub.orderStockServerStream(request, responseObserver);

        try{
            Thread.sleep(500);
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
