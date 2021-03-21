package grpc.services.LexingCoWarehouse;

import grpc.services.LexingCoOrdering.LexingCoOrderingServiceGrpc;
import grpc.services.LexingCoOrdering.MessageReply;
import grpc.services.LexingCoOrdering.MessageRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;

public class WarehouseServiceListener implements ServiceListener {

    private static final WarehouseServiceListener warehouseServiceListener = new WarehouseServiceListener();
    private static LexingCoOrderingServiceGrpc.LexingCoOrderingServiceBlockingStub blockStub;
    private int orderingPort = 50052;

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

    void orderParts(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", orderingPort).usePlaintext().build();
        blockStub = LexingCoOrderingServiceGrpc.newBlockingStub(channel);
        MessageRequest request = MessageRequest.newBuilder().build();
        MessageReply reply = blockStub.sendMessage(request);
        System.out.println("ordering parts! " + reply.getText());
    }
}
