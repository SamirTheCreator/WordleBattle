package com.irgashevsamir.javafx_samples;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Sockets {
}

class HostPlayer extends ServerSocket {

    public HostPlayer(int port, int backlog, InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
    }

    String username;
}