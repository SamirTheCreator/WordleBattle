package com.irgashevsamir.javafx_samples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // responsible for listening to incoming connections and creating a socket object to
    // communicate with them
    ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("New client");
                Player player = new Player(socket);
                Thread thread = new Thread(player);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();

    }
}
