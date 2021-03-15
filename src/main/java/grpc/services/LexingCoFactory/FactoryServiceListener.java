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
    private static String warehouseHost;
    private static int  warehousePort;



    public static void main(String[] args) {
        JmDNS jmdns = null;
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String service_type = "_warehouse._tcp.local.";
        jmdns.addServiceListener(service_type, factoryServiceListener);
    }

    @Override
    public void serviceAdded(ServiceEvent event) {
        System.out.println("Service added: " + event.getInfo());

    }

    @Override
    public void serviceRemoved(ServiceEvent event) {
        System.out.println("Service removed: " + event.getInfo());
    }

    @Override
    public void serviceResolved(ServiceEvent event) {
        System.out.println("Service resolved: " + event.getInfo());
        warehouseHost = event.getInfo().getHostAddresses()[0];
        warehousePort = event.getInfo().getPort();
    }

    void requestParts(String partName){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(warehouseHost, warehousePort).usePlaintext().build();
        blockStub = LexingCoWarehouseServiceGrpc.newBlockingStub(channel);
        MessageRequest request = MessageRequest.newBuilder().build();
        MessageReply reply = blockStub.sendMessage(request);
        System.out.println("Sending parts! " + reply.getText());
    }
}
