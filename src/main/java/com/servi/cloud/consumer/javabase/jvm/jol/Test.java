package com.servi.cloud.consumer.javabase.jvm.jol;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Test t = new Test();

        System.out.println(ClassLayout.parseInstance(t).toPrintable());

        synchronized (t) {
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void add() {
        int i = 0;
        while (i++ < 100) {
            synchronized (Test.class) {
                System.out.println();
            }
        }
    }
}
