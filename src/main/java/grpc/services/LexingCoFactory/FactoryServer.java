package grpc.services.LexingCoFactory;


import grpc.services.LexingCoWarehouse.LexingCoWarehouseServiceGrpc;
import grpc.services.LexingCoWarehouse.MessageReply;
import grpc.services.LexingCoWarehouse.MessageRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import models.CarPart;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class FactoryServer extends LexingCoFactoryServiceGrpc.LexingCoFactoryServiceImplBase{

    private static JmDNS jmDNS;
    private final static int  port = 50051;
    private static ArrayList<CarPart> factoryStorage;
    static FactoryServer factoryServer;
    static FactoryServiceListener factoryServiceListener;


    public static void main(String[] args){

        factoryServiceListener = new FactoryServiceListener();
        factoryServer = new FactoryServer();
        factoryServer.registerService();
        stockFactory();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(factoryServer).build().start();
            System.out.println("Factory Server started, listening on " + port);
            server.awaitTermination();

            //JmDNS stuff

            // ---------
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stockFactory() {
        factoryStorage = new ArrayList<CarPart>();
        factoryStorage.add(new CarPart("Battery", 2));
        factoryStorage.add(new CarPart("Brakes", 2));
        factoryStorage.add(new CarPart("Engine", 2));
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

    //Make a factory storage
    //When a build request comes in, check if all parts have available stock, if not, request more from warehouse.
    //When a repair request comes in, check if the part is in stock, if not, request more from warehouse.

    @Override
    public void buildCar(BuildRequest request, StreamObserver<BuildReply> responseObserver) {
        System.out.println("Receiving build request");
        sourceParts();
        responseObserver.onNext(BuildReply.newBuilder().setText("200 - OK from factory build service").build());
        responseObserver.onCompleted();
    }

    private void sourceParts() {
        for(CarPart part: factoryStorage){
            part.setQuantity(part.getQuantity() - 1);
            if(part.getQuantity() <= 1){
                factoryServiceListener.requestParts(part.getPartName());
            }
        }
    }

    @Override
    public void repairCar(RepairRequest request, StreamObserver<RepairReply> responseObserver) {
        System.out.println("Receiving repair request");
        responseObserver.onNext(RepairReply.newBuilder().setText("200 - OK from factory repair service").build());
        responseObserver.onCompleted();
    }
}
