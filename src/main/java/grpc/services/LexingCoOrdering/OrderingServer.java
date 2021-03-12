package grpc.services.LexingCoOrdering;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class OrderingServer extends LexingCoOrderingServiceGrpc.LexingCoOrderingServiceImplBase {
    private static JmDNS jmDNS;
    private final static int  port = 50052;

    public static void main(String[] args){

        OrderingServer orderingServer = new OrderingServer();
        orderingServer.registerService();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(orderingServer).build().start();
            System.out.println("Ordering Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerService() {

        String service_type = "_ordering._tcp.local.";
        String service_name = "LexingCoOrdering";
        String service_description = "Ordering Server will order parts";

        try {
            //Create JmDNS instance
            jmDNS = JmDNS.create(InetAddress.getLocalHost());

            // Register factory service
            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, port, service_description);
            jmDNS.registerService(serviceInfo);

            System.out.printf("Registered %s in port %d \n", service_type, port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
        System.out.println("Receiving Order request");
        responseObserver.onNext(MessageReply.newBuilder().setText("200 - OK from ordering service").build());
        responseObserver.onCompleted();
    }
}
