package com.servi.cloud.consumer.socket.echo;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        new EchoServer().server();
    }

    private ServerSocket echoSocket = null;

    private void server() throws IOException {
        if (echoSocket == null)
            echoSocket = new ServerSocket(8080);
        Socket client = null;
        try {
            while (true) {
                client = echoSocket.accept();
                ServiLogger.log("开始接收请求！");
                BufferedReader br = reader(client);
                PrintWriter pw = writer(client);
                String msg = null;
                while ((msg = br.readLine()) != null) {
                    ServiLogger.log(msg);
                    pw.println("accept:" + msg);
                    if (msg.equals("bye")) {
                        break;
                    }
                }
            }
        } finally {
            if (client != null)
                client.close();
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
