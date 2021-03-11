package com.servi.cloud.consumer.util.proxy;

public class Test {

    public static void main(String[] args) {

        CGlibProxy proxy = new CGlibProxy();
        Test test = proxy.getProxy(Test.class);
        test.doTest();

    }

    public void doTest() {
        System.out.println("kkkkkk");
    }
}
