package com.servi.cloud.consumer.socket.onthread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerOnThread {

    public static void main(String[] args) {
        try {
            new SocketServerOnThread().doServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ServerSocket server = null;
    private int port = 8080;
    private ExecutorService executors = Executors.newFixedThreadPool(10);

    public SocketServerOnThread() throws IOException {
        server = new ServerSocket(port, 0, InetAddress.getByName("localhost"));
    }

    public void doServer() {
        Socket client = null;
        try {
            while (true) {
                client = server.accept();
                executors.submit(new ServerRunable(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
