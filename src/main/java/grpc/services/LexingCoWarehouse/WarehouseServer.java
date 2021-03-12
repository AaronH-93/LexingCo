package grpc.services.LexingCoWarehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class WarehouseServer extends LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceImplBase {
    private static JmDNS jmDNS;
    private final static int  port = 50053;

    public static void main(String[] args){

        WarehouseServer warehouseServer = new WarehouseServer();
        warehouseServer.registerService();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(warehouseServer).build().start();
            System.out.println("Warehouse Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerService() {

        String service_type = "_warehouse._tcp.local.";
        String service_name = "LexingCoWarehouse";
        String service_description = "Warehouse server will store parts";

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
        System.out.println("Receiving Warehouse request");
        responseObserver.onNext(MessageReply.newBuilder().setText("200 - OK from warehouse service").build());
        responseObserver.onCompleted();
    }
}
