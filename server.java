import java.util.*;

import javax.management.openmbean.OpenMBeanInfoSupport;

import java.io.*;
import java.net.*;



public class server {
    private DataInputStream fromClient;
    private DataOutputStream toClient;


    public static void main(String[] args) throws IOException {
        new Server();

    }

    private static ServerSocket server;
    server() throws IOException {
        server = new ServerSocket(9876);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        
        
        Socket client = server.accept();
    
        toClient = new DataOutputStream(client.getOutputStream());
        fromClient = new DataInputStream(client.getInputStream());

        int message = fromClient.readInt();
        System.out.println(message + " was received ");    
    }
}
