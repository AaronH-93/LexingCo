package grpc.services.LexingCoFactory;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class FactoryServer extends LexingCoFactoryServiceGrpc.LexingCoFactoryServiceImplBase {

    private static JmDNS jmDNS;
    private final static int  port = 50051;

    public static void main(String[] args){

        FactoryServer factoryServer = new FactoryServer();
        factoryServer.registerService();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(factoryServer).build().start();
            System.out.println("Factory Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerService() {

        String service_type = "_factory._tcp.local.";
        String service_name = "LexingCoFactory";
        String service_description = "Factory Server will build cars";

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
        System.out.println("Receiving factory request");
        responseObserver.onNext(MessageReply.newBuilder().setText("200 - OK from factory service").build());
        responseObserver.onCompleted();
    }
}
