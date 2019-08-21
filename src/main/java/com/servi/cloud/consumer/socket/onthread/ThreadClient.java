package com.servi.cloud.consumer.socket.onthread;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.*;
import java.net.Socket;

public class ThreadClient {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            new ThreadClient("ThreadClient-" + i).connect();
        }
    }

    private String name;
    private Socket client;

    public ThreadClient(String name) {
        this.name = name;
    }

    public void connect() throws IOException {
        if (client == null) {
            client = new Socket("localhost", 8080);
        }
        try {
            BufferedReader br = reader(client);
            PrintWriter pw = writer(client);
            String msg = null;
            for (int i = 0; i < 100; i++) {
                if (i == 99) {
                    pw.println("bye");
                } else {
                    pw.println(name + ":" + i);
                }
            }
        } finally {
            try {
                client.close();
            } catch (Exception e) {
            }
        }
    }

    private BufferedReader reader(Socket server) throws IOException {
        InputStream inputStream = server.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private PrintWriter writer(Socket server) throws IOException {
        OutputStream outputStream = server.getOutputStream();
        return new PrintWriter(outputStream, true);
    }
}
