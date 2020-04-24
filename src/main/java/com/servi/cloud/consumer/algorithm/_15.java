package com.servi.cloud.consumer.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数列
 */
public class _15 {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.print(add(i) + " ");
        }

    }

    private static Map<String, Integer> m = new HashMap<>();

    private static int add(int a) {
        if (a <= 1) {
            return 1;
        }
        if (m.containsKey(a + "")) {
            return m.get(a + "");
        }
        int b = add(a - 1) + add(a - 2);
        m.put(a + "", b);
        return b;
    }
}
