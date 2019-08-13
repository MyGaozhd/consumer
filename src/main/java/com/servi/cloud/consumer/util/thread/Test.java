package com.servi.cloud.consumer.util.thread;

public class Test {

    public static void main(String[] args) {
        IThreadLocal<Integer> iThreadLocal = new IThreadLocal<Integer>(){
            @Override
            protected Integer initValue() {
                return 1;
            }
        };

        System.out.println(iThreadLocal.getValue());
    }
}
