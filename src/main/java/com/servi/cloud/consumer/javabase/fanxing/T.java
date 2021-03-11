package com.servi.cloud.consumer.javabase.fanxing;

import java.util.concurrent.Callable;

public class T {

    public static void main(String[] args) {
        T.run(new TT());
    }

    public static <E extends Runnable & Callable> void run(E e) {

    }

    static class TT implements Runnable, Callable {

        @Override
        public void run() {

        }

        @Override
        public Object call() throws Exception {
            return null;
        }
    }
}
