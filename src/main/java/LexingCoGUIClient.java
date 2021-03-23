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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class LexingCoGUIClient {
    private static int factoryPort = 50051;
    private static String factoryHost = "localhost";
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress(factoryHost, factoryPort).usePlaintext().build();
    private static LexingCoFactoryServiceGrpc.LexingCoFactoryServiceBlockingStub blockStub = LexingCoFactoryServiceGrpc.newBlockingStub(channel);;
    private ServiceInfo factoryServiceInfo;
    private JButton button1;
    private JPanel lexingCoGUI;
    private JButton button2;

    public static void main(String[] args) {
        LexingCoGUIClient gui = new LexingCoGUIClient();
        JFrame frame = new JFrame("LexingCo");
        frame.setContentPane(gui.lexingCoGUI);
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
                buildCar();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairCar();
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
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buildCar() {
        BuildRequest request = BuildRequest.newBuilder().build();
        BuildReply reply = blockStub.buildCar(request);
        System.out.println("Building car! " + reply.getText());
    }

    private void repairCar() {
        BuildRequest request = BuildRequest.newBuilder().build();
        BuildReply reply = blockStub.buildCar(request);
        System.out.println("Repairing Car " + reply.getText());
    }
}
