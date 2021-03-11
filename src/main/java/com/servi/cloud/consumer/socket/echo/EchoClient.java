package com.servi.cloud.consumer.socket.echo;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        new EchoClient().connect();
    }

    private Socket client;

    public void connect() throws IOException {
        if (client == null) {
            client = new Socket("localhost", 8080);
        }
        try {
            BufferedReader br = reader(client);
            PrintWriter pw = writer(client);
            BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = lr.readLine()) != null) {
                pw.println(msg);
                ServiLogger.log(br.readLine());
                if (msg.equals("bye")) {
                    break;
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
