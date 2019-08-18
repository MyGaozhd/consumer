package com.servi.cloud.consumer.socket;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoPlayer {

    public static void main(String[] args) throws IOException {
        new EchoPlayer().talk();
    }

    private String echo(String msg) {
        return "echo:" + msg;
    }

    public void talk() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = null;
        while ((msg = br.readLine()) != null) {
            ServiLogger.log(echo(msg));
            if (msg.equals("bye")) {
                break;
            }
        }
    }
}
