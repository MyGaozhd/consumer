package com.servi.cloud.consumer.javabase;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.ArrayList;
import java.util.HashMap;

public class Yw {
    public static void main(String[] args) {
//        int a = 50;
//        ServiLogger.log(a >> 1);
//        ArrayList<String> arrayList = new ArrayList<>(20);
//
//        for (int i = 0; i < a; i++) {
//            arrayList.add("" + i);
//        }
//        arrayList.get(0);
//        arrayList.remove(0);

//        ServiLogger.log(Integer.toBinaryString(15));
//        ServiLogger.log(Integer.toBinaryString(-15));
//        ServiLogger.log(Integer.toBinaryString(15^-15));
//        ServiLogger.log(Integer.toBinaryString((15)>>>16));
//        ServiLogger.log(Integer.toBinaryString((-15)>>>16));
//        ServiLogger.log(Integer.toBinaryString((15^-15)>>>16));
//
//        ServiLogger.log(Integer.toBinaryString((15)>>16));
//        ServiLogger.log(Integer.toBinaryString((-15)>>16));
//        ServiLogger.log(Integer.toBinaryString((15^-15)>>16));
        HashMap<Key, String> h = new HashMap<>();

        for (int i = 0; i < 111; i++) {
            Key key = new Key(1);
            h.put(key, "" + i);
        }

        Key key = new Key(999);
        h.put(key, "" + 999);
        h.get(key);
    }

    static class Key {

        private int i;

        public Key(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return 5;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
