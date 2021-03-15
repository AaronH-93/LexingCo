import grpc.services.LexingCoFactory.BuildReply;
import grpc.services.LexingCoFactory.BuildRequest;
import grpc.services.LexingCoFactory.LexingCoFactoryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;

public class LexingCoClient implements ServiceListener {

    private static LexingCoFactoryServiceGrpc.LexingCoFactoryServiceBlockingStub blockStub;

    public static void main(String[] args) {
        LexingCoClient client = new LexingCoClient();

        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            String service_type = "_factory._tcp.local.";

            // Add a service listener
            jmdns.addServiceListener(service_type, client);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
        //Ideally we will determine what function to use from here in the GUI
        buildCar(event.getInfo().getHostAddresses()[0], event.getInfo().getPort());
    }

    private void buildCar(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockStub = LexingCoFactoryServiceGrpc.newBlockingStub(channel);
        BuildRequest request = BuildRequest.newBuilder().build();
        BuildReply reply = blockStub.buildCar(request);
        System.out.println("Building car! " + reply.getText());
    }

    private void repairCar(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockStub = LexingCoFactoryServiceGrpc.newBlockingStub(channel);
        BuildRequest request = BuildRequest.newBuilder().build();
        BuildReply reply = blockStub.buildCar(request);
        System.out.println("Repairing Car " + reply.getText());
    }
}
