package grpc.services.LexingCoFactory;

import io.grpc.Context;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import models.CarPart;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class FactoryServer extends LexingCoFactoryServiceGrpc.LexingCoFactoryServiceImplBase{

    private static JmDNS jmDNS;
    private final static int  port = 50051;
    private static ArrayList<CarPart> factoryStorage;
    private static FactoryServer factoryServer;
    private static FactoryServiceListener factoryServiceListener;

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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stockFactory() {
        factoryStorage = new ArrayList<>();
        factoryStorage.add(new CarPart("Battery", 2));
        factoryStorage.add(new CarPart("Brakes", 2));
        factoryStorage.add(new CarPart("Engine", 2));
        factoryStorage.add(new CarPart("Doors", 2));
        factoryStorage.add(new CarPart("Seats", 2));
        factoryStorage.add(new CarPart("Lights", 2));
        factoryStorage.add(new CarPart("Wheels", 2));
        factoryStorage.add(new CarPart("Chassis", 2));
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
    public void repairCar(RepairRequest request, StreamObserver<RepairReply> responseObserver) {
        System.out.println("Receiving repair request");
        factoryServiceListener.repairPartsBiDiStream(vehicleInspection());
        responseObserver.onNext(RepairReply.newBuilder().setText("200 - OK from factory repair service").build());
        responseObserver.onCompleted();
    }

    @Override
    public void buildCar(BuildRequest request, StreamObserver<BuildReply> responseObserver) {
        System.out.println("Receiving build request");
        Context forked = Context.current().fork();
        Context old = forked.attach();
        try {
            responseObserver.onNext(BuildReply.newBuilder().setText("200 - OK from factory build service").build());
            sourceParts();
        } finally {
            forked.detach(old);
        }
        responseObserver.onCompleted();
    }

    private void sourceParts() {
        ArrayList<String> newStockRequest = new ArrayList<>();
        for(CarPart part: factoryStorage){
            part.setQuantity(part.getQuantity() - 1);
            System.out.println("Quantity of "+ part.getPartName() +" parts in stock: " + part.getQuantity());
            if(part.getQuantity() == 0){
                System.out.println("Requesting more stock of "+ part.getPartName() +" from warehouse.");
                //requestParts is what restocks the factory stocks
                //When the factory runs out of parts, it requests more
                newStockRequest.add(part.getPartName());
                //factoryServiceListener.requestParts(part.getPartName());
                //When this is called, the factory service should add two of each part used to its stores
                //and the warehouse should remove 2 from its stores.
            }
        }
        if(!newStockRequest.isEmpty()){
            factoryServiceListener.requestPartsStream(newStockRequest);
        }
    }

//    private void replaceParts(String[] parts){
//        for(CarPart part: factoryStorage){
//            for(String replaceParts: parts){
//                if(factoryStorage.contains(replaceParts)){
//                    part.setQuantity(part.getQuantity() - 1);
//                    if(part.getQuantity() == 0){
//                        //requestParts is what restocks the factory stocks
//                    }
//                }
//            }
//        }
//    }

    void restockFactory(String part, int quantity) {
        for (CarPart carPart : factoryStorage) {
            if (carPart.getPartName().equals(part)) {
                carPart.setQuantity(quantity);
            }
        }
    }

    private String[] vehicleInspection() {
        //Randomly select car parts that need to be replaced simulating parts to be replaced.
        //Make this return a random array of repairs
        System.out.println("Inspecting vehicle for potential repairs");
        String[] listOfRepairs = {"Engine", "Wheels"};
        return listOfRepairs;
    }
}
