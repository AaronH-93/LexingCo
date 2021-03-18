import grpc.services.LexingCoFactory.BuildReply;
import grpc.services.LexingCoFactory.BuildRequest;
import grpc.services.LexingCoFactory.LexingCoFactoryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class LexingCoGUIClient {
    private static LexingCoFactoryServiceGrpc.LexingCoFactoryServiceBlockingStub blockStub;
    private ServiceInfo factoryServiceInfo;
    private JButton button1;
    private JPanel lexingCoGUI;


    public static void main(String[] args) {
        JFrame frame = new JFrame("LexingCo");
        frame.setContentPane(new LexingCoGUIClient().lexingCoGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public LexingCoGUIClient(){
        String factory_service_type = "_factory._tcp.local.";
        discoverFactoryService(factory_service_type);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildCar("localhost", 50051);
            }
        });
    }

    private void discoverFactoryService(String service_type){
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // Add a service listener
            jmdns.addServiceListener(service_type, new ServiceListener() {
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
                    factoryServiceInfo = event.getInfo();

                    int port = factoryServiceInfo.getPort();

                    System.out.println("Service resolved: " + event.getInfo());
                    System.out.println("resolving " + service_type + " with properties ...");
                    System.out.println("\t port: " + port);
                    System.out.println("\t type:"+ event.getType());
                    System.out.println("\t name: " + event.getName());
                    System.out.println("\t description/properties: " + factoryServiceInfo.getNiceTextString());
                    System.out.println("\t host: " + factoryServiceInfo.getHostAddresses()[0]);

                    //Ideally we will determine what function to use from here in the GUI
                    //buildCar(event.getInfo().getHostAddresses()[0], event.getInfo().getPort());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
