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
import java.util.Random;


public class FactoryServer extends LexingCoFactoryServiceGrpc.LexingCoFactoryServiceImplBase{

    private static JmDNS jmDNS;
    private final static int  port = 50051;
    private static ArrayList<CarPart> factoryStorage;
    private static FactoryServer factoryServer;
    private static FactoryServiceClient factoryServiceClient;
    private Random random = new Random();

    public static void main(String[] args){

        //Create an instance of the FactoryServer and Service Listener
        factoryServiceClient = new FactoryServiceClient();
        factoryServer = new FactoryServer();

        factoryServer.registerService();
        stockFactory();
        Server server;
        try {
            //Builds and starts the factory service.
            server = ServerBuilder.forPort(port).addService(factoryServer).build().start();
            System.out.println("Factory Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Provides the factory with its initial stock.
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

    //Registers and creates the JmDNS instance of the factory service.
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

    //When a repair car request is sent, the factory service will call the repairPartsBiDiStream method in the FactoryServiceListener class
    //and make a request to the Warehouse Service.
    @Override
    public void repairCar(RepairRequest request, StreamObserver<RepairReply> responseObserver) {
        System.out.println("Receiving repair request");
        factoryServiceClient.repairPartsBiDiStream(vehicleInspection());
        responseObserver.onNext(RepairReply.newBuilder().setText("200 - OK from factory repair service").build());
        responseObserver.onCompleted();
    }

    //When a build car request is sent, the factory service will source the parts for building the car, if there is
    //a shortage of parts, it will request more from the Warehouse Service.
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

    //sourceParts finds stock of car parts in the factory storage used to build a car, when the number of parts in
    //factoryStorage becomes 0, the Factory service will request a restock from the Warehouse Service.
    private void sourceParts() {
        ArrayList<String> newStockRequest = new ArrayList<>();
        for(CarPart part: factoryStorage){
            part.setQuantity(part.getQuantity() - 1);
            System.out.println("Quantity of "+ part.getPartName() +" parts in stock: " + part.getQuantity());
            //If the quantity of parts in low, the part will be added to newStockRequest.
            if(part.getQuantity() == 0){
                System.out.println("Requesting more stock of "+ part.getPartName() +" from warehouse.");
                newStockRequest.add(part.getPartName());
            }
        }

        //If newStockRequest is not empty, then a restock will be requested from Warehouse.
        if(!newStockRequest.isEmpty()){
            factoryServiceClient.requestPartsStream(newStockRequest);
        }
    }

    //restockFactory restocks the factory storage with the quantity returned from the Warehouse service.
    public void restockFactory(String part, int quantity) {
        for (CarPart carPart : factoryStorage) {
            if (carPart.getPartName().equals(part)) {
                carPart.setQuantity(quantity);
            }
        }
    }

    //vehicleInspection simulates the inspection of a vehicle and returns a random number of
    //parts that need to be repaired/replaced
    private ArrayList<String> vehicleInspection() {
        System.out.println("Inspecting vehicle for potential repairs");
        ArrayList<String> listOfRepairs = new ArrayList<>();
        for(int i = 0; i <= getRandomNumber(); i++){
            int randomPart = getRandomNumber();
            if(!listOfRepairs.contains(factoryStorage.get(randomPart).getPartName())){
                listOfRepairs.add(factoryStorage.get(randomPart).getPartName());
            }
        }
        for(String part : listOfRepairs){
            System.out.println(part + "needs to be replaced.");
        }
        return listOfRepairs;
    }

    //Simple function to generate a random number between 0 and 7.
    private int getRandomNumber(){
        int randomNumber = random.nextInt(8);
        return randomNumber;
    }
}
