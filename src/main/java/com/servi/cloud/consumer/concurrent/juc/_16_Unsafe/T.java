package com.servi.cloud.consumer.concurrent.juc._16_Unsafe;

//import sun.misc.*;

import sun.misc.Unsafe;

public class T {
    static class M {
        private M() {}

        int i =0;
    }

   public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
       unsafe.fullFence();
    }
}


