package grpc.services.LexingCoWarehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import models.CarPart;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class WarehouseServer extends LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceImplBase {
    private static JmDNS jmDNS;
    private static WarehouseServer warehouseServer;
    private static WarehouseServiceListener warehouseServiceListener;
    private final static int  port = 50053;
    private static ArrayList<HashMap> warehouseList;
    private static HashMap northWarehouse;
    private static HashMap westWarehouse;
    private static HashMap southWarehouse;
    private static HashMap eastWarehouse;

    public static void main(String[] args){
        warehouseServiceListener = new WarehouseServiceListener();
        warehouseServer = new WarehouseServer();
        warehouseServer.registerService();
        stockWarehouses();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(warehouseServer).build().start();
            System.out.println("Warehouse Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stockWarehouses() {
        northWarehouse = new HashMap();
        northWarehouse.put("Engine", 5);
        northWarehouse.put("Battery", 5);
        westWarehouse = new HashMap();
        westWarehouse.put("Chassis", 5);
        westWarehouse.put("Brakes", 5);
        southWarehouse = new HashMap();
        southWarehouse.put("Doors", 5);
        southWarehouse.put("Seats", 5);
        eastWarehouse = new HashMap();
        eastWarehouse.put("Lights", 5);
        eastWarehouse.put("Wheels", 5);

        warehouseList = new ArrayList<>();
        warehouseList.add(northWarehouse);
        warehouseList.add(westWarehouse);
        warehouseList.add(southWarehouse);
        warehouseList.add(eastWarehouse);

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

    //This needs to be renamed to something more appropriate
    @Override
    public void restockFactory(FactoryRestockRequest request, StreamObserver<FactoryRestockReply> responseObserver) {
        String quantity = "2";
        System.out.println("Receiving Factory request for parts");
        //removeParts(request.getText(), Integer.parseInt(quantity));
        responseObserver.onNext(FactoryRestockReply.newBuilder().setText(quantity).build());
        responseObserver.onCompleted();
    }

    public StreamObserver<FactoryRestockRequest> restockFactoryStream(StreamObserver<FactoryRestockReply> responseObserver){
        return new StreamObserver<FactoryRestockRequest>() {
            ArrayList<String> list = new ArrayList<>();
            String quantity = "2";
            @Override
            public void onNext(FactoryRestockRequest restockRequest) {
                System.out.println("Receiving Factory request for: " + restockRequest.getText());
                list.add(restockRequest.getText());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                String response = "";
                for(String part : list){
                    response += "\n " + part;
                }
                FactoryRestockReply reply = FactoryRestockReply.newBuilder().setText(response).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
                removeParts(list, Integer.parseInt(quantity));
            }
        };
    }

    public StreamObserver<FactoryRestockRequest> repairStockFactoryStream(StreamObserver<FactoryRestockReply> responseObserver){
        return new StreamObserver<FactoryRestockRequest>() {
            ArrayList<String> list = new ArrayList<>();
            String quantity = "1";
            @Override
            public void onNext(FactoryRestockRequest restockRequest) {
                System.out.println("Receiving repair request for: " + restockRequest.getText());
                list.add(restockRequest.getText());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                String response = "";
                for(String part : list){
                    response += "\n " + part;
                }
                FactoryRestockReply reply = FactoryRestockReply.newBuilder().setText(response).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
                removeParts(list, Integer.parseInt(quantity));
            }
        };
    }

    private void removeParts(ArrayList<String> parts, int quantity) {
        int newQuantity = 0;
        String partsToOrder = "";
        for(String part : parts){
            System.out.println("Sending new stock of " + part + "'s to factory, Quantity: " + quantity);
            for(HashMap warehouse : warehouseList){
                if(warehouse.containsKey(part)){
                    newQuantity = (int) warehouse.get(part);
                    newQuantity -= quantity;
                    warehouse.replace(part, newQuantity);

                    if((int) warehouse.get(part) <= 2){
                        System.out.println("Ordering new stock of " + part + " for warehouse");
                        partsToOrder += part + "\n";
                    }
                }
            }
            System.out.println("Quantity of " + part + " in warehouse stock is " + newQuantity);
        }
        if(!partsToOrder.isEmpty()){
            warehouseServiceListener.orderPartsServerStream(partsToOrder);
        }
    }

    public void restockWarehouse(String part, int quantity){
        for(HashMap warehouse : warehouseList){
            if(warehouse.containsKey(part)){
                warehouse.replace(part, quantity);
            }
        }
    }

    public void restockWarehouseWithPart(CarPart part){
        for(HashMap warehouse : warehouseList){
            if(warehouse.containsKey(part.getPartName())){
                warehouse.replace(part.getPartName(), part.getQuantity());
            }
        }
    }
}
