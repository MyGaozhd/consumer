package com.servi.cloud.consumer.javabase.map;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,String> h = new HashMap<>(16);
        h.put("1","1");
        h.get(null);
        h.put(null,null);
    }
}
