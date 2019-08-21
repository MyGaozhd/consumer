package com.servi.cloud.consumer.socket.onthread;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.*;
import java.net.Socket;

public class ServerRunable implements Runnable {

    private final Socket client;

    public ServerRunable(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        if (client == null) return;

        BufferedReader br = null;
        try {
            br = reader(client);
            PrintWriter pw = writer(client);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                ServiLogger.log(msg);
                pw.println("accept:" + msg);
                if (msg.equals("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BufferedReader reader(Socket client) throws IOException {
        InputStream inputStream = client.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private PrintWriter writer(Socket client) throws IOException {
        OutputStream outputStream = client.getOutputStream();
        return new PrintWriter(outputStream, true);
    }
}
