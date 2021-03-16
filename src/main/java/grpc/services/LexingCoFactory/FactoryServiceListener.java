package grpc.services.LexingCoFactory;

import grpc.services.LexingCoWarehouse.LexingCoWarehouseServiceGrpc;
import grpc.services.LexingCoWarehouse.MessageReply;
import grpc.services.LexingCoWarehouse.MessageRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;

public class FactoryServiceListener implements ServiceListener {

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

    void requestParts(){
        //This should be one of the streaming RPC functions probably
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", warehousePort).usePlaintext().build();
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        MessageRequest request = MessageRequest.newBuilder().build();
        MessageReply reply = blockStub.sendMessage(request);
        System.out.println("Sending parts! " + reply.getText());
    }
}
