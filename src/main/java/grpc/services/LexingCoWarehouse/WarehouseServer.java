package grpc.services.LexingCoWarehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class WarehouseServer extends LexingCoWarehouseServiceGrpc.LexingCoWarehouseServiceImplBase {
    private static JmDNS jmDNS;
    WarehouseServiceListener warehouseServiceListener = new WarehouseServiceListener();
    private final static int  port = 50053;
    ArrayList<HashMap> warehouseList;
    private HashMap northWarehouse;
    private HashMap westWarehouse;
    private HashMap southWarehouse;
    private HashMap eastWarehouse;

    public static void main(String[] args){

        WarehouseServer warehouseServer = new WarehouseServer();
        warehouseServer.registerService();
        //StockWarehouses could use OrderingService
        warehouseServer.stockWarehouses();
        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(warehouseServer).build().start();
            System.out.println("Warehouse Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stockWarehouses() {
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

    private void restockWarehouses(String part){
        for(HashMap warehouse : warehouseList){
            if(warehouse.containsKey(part)){
                warehouse.replace(part, 5);
            }
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

    //This needs to be renamed to something more appropriate
    @Override
    public void restockFactory(RestockRequest request, StreamObserver<RestockReply> responseObserver) {
        String quantity = "2";
        System.out.println("Receiving Factory request for parts");
        removeParts(request.getText(), Integer.parseInt(quantity));
        responseObserver.onNext(RestockReply.newBuilder().setText(quantity).build());
        responseObserver.onCompleted();
    }

    private void removeParts(String part, int quantity) {
        System.out.println("Sending new stock of " + part + "'s, Quantity: " + quantity);
        int newQuantity = 0;
        for(HashMap warehouse : warehouseList){
            if(warehouse.containsKey(part)){
                newQuantity = (int) warehouse.get(part);
                newQuantity -= quantity;
                warehouse.replace(part, newQuantity);

                if((int) warehouse.get(part) <= 2){
                    System.out.println("Restocking");
                    warehouseServiceListener.orderParts();
                }
            }
        }
        System.out.println("Quantity of " + part + " in warehouse stock is " + newQuantity);
    }

}
