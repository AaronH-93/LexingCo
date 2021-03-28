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

public class WarehouseServiceClient extends WarehouseServer implements ServiceListener {

    private static final WarehouseServiceClient WAREHOUSE_SERVICE_CLIENT = new WarehouseServiceClient();
    private static int orderingPort = 50052;
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", orderingPort).usePlaintext().build();
    private static LexingCoOrderingServiceGrpc.LexingCoOrderingServiceStub asyncStub;

    public static void main(String[] args) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            String service_type = "_ordering._tcp.local.";
            jmdns.addServiceListener(service_type, WAREHOUSE_SERVICE_CLIENT);
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

    //orderStockServerStream in the orderPartsServerStream function is a server side rpc stream that takes
    //in a single message and returns multiple it takes the message containing the parts it needs to order
    //and splits it into an array and creates a new car part from each message, which is added back to the warehouse.
    public void orderPartsServerStream(String partsToOrder){
        asyncStub = LexingCoOrderingServiceGrpc.newStub(channel);
        WarehouseRestockRequest request = WarehouseRestockRequest.newBuilder().setText(partsToOrder).build();

        StreamObserver<OrderRestockReply> responseObserver = new StreamObserver<OrderRestockReply>() {
            @Override
            public void onNext(OrderRestockReply orderRestockReply) {
                System.out.println("Received new stock of: " + orderRestockReply.getText());
                String[] reply = orderRestockReply.getText().split("\n");
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
