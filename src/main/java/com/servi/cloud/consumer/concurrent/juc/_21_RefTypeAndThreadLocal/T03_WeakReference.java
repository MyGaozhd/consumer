package com.servi.cloud.consumer.concurrent.juc._21_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用遭到gc就会回收
 * 一般用在容器里
 * M  有个强引用指向M ，如果强引用不在指向M
 * M 存在于容器中 只要有垃圾回收就会把M回收掉
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();

    }
}

