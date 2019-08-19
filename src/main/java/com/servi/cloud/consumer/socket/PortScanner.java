package com.servi.cloud.consumer.socket;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.IOException;
import java.net.Socket;

/**
 * 检测主机哪些端口处于被监听状态
 */
public class PortScanner {


    public static void main(String[] args) {
        Scan();
    }

    public static void Scan() {

        Socket client = null;
        for (int i = 0; i <= 65535; i++) {
            try {
                client = new Socket("localhost", i);
                ServiLogger.log("可监听端口：" + i);
            } catch (IOException e) {
                ServiLogger.log("无监听端口：" + i);
//                e.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
