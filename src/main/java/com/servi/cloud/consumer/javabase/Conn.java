package com.servi.cloud.consumer.javabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Conn {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        m.put("123", "");

        HashMap<String, String> h = new HashMap<>();
        h.put("123", "123");
    }
}
