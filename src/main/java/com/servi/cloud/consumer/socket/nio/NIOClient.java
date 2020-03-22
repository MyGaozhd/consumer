package com.servi.cloud.consumer.socket.nio;

import java.util.concurrent.ConcurrentHashMap;

public class NIOClient {

    static ConcurrentHashMap<String, String> c = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        c.put("", "");
        System.out.println(c.getClass().getClassLoader());
    }
}
