package grpc.services.LexingCoFactory;

import grpc.services.LexingCoOrdering.StockReply;
import grpc.services.LexingCoOrdering.StockRequest;
import grpc.services.LexingCoWarehouse.LexingCoWarehouseServiceGrpc;
import grpc.services.LexingCoWarehouse.RestockReply;
import grpc.services.LexingCoWarehouse.RestockRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;


public class FactoryServiceListener extends FactoryServer implements ServiceListener {

    private static final FactoryServiceListener factoryServiceListener = new FactoryServiceListener();
    private static LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceBlockingStub blockStub;
    private int warehousePort = 50053;

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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", warehousePort).usePlaintext().build();
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);

        RestockRequest request = RestockRequest.newBuilder().setText(part).build();
        RestockReply reply = blockStub.restockFactory(request);
        restockFactory(part, Integer.parseInt(reply.getText()));
        System.out.println("Receiving new stock of " + part +"'s!");
    }



    public void requestParts(String[] parts) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", warehousePort).usePlaintext().build();
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);

        RestockRequest request = RestockRequest.newBuilder().build();
        RestockReply reply = blockStub.restockFactory(request);
        System.out.println("parts! " + reply.getText());
    }
}
