import grpc.services.LexingCoFactory.*;
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
    private static final LexingCoFactoryServiceGrpc.LexingCoFactoryServiceBlockingStub blockStub = LexingCoFactoryServiceGrpc.newBlockingStub(channel);;
    private ServiceInfo factoryServiceInfo;
    private JPanel lexingCoGUI;
    private JButton buildCarButton;
    private JButton repairCarButton;
    private JLabel lexingCoTextArea;
    private JLabel buildCarTextArea;
    private JLabel repairCarTextArea;

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

        lexingCoTextArea.setText(" Welcome to LexingCo! \n" +
                " Home of the Lexington Automobile, you may request a car to be built or send a car for in for repairs\n" +
                        " from this application!");

        buildCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildCar();
            }
        });
        repairCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairCar();
            }
        });
    }

    //Method to discover the factory service
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

    //Simple RPC message to and from the factory service, returning and displaying a status message.
    private void buildCar() {
        BuildRequest request = BuildRequest.newBuilder().build();
        BuildReply reply = blockStub.buildCar(request);
        buildCarTextArea.setText("Building car! " + reply.getText());
        System.out.println("Building car! " + reply.getText());
    }

    //Simple RPC message to and from the factory service, returning and displaying a status message.
    private void repairCar() {
        RepairRequest request = RepairRequest.newBuilder().build();
        RepairReply reply = blockStub.repairCar(request);
        repairCarTextArea.setText("Repairing Car! " + reply.getText());
        System.out.println("Repairing Car! " + reply.getText());
    }
}
