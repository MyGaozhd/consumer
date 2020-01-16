package com.servi.cloud.consumer.concurrent.thread;

public class TestCurrentThread {
    public static void main(String[] args) {
        MyThread thread = new MyThread("mythread");
//        thread.setName("kkk");
        thread.start();
//        thread.run();

        System.out.println("执行结束。");
    }

    static class MyThread extends Thread {

        MyThread(String name) {
//            setName(name);
            System.out.println("MyThread 构造方法：" + this.currentThread().getName());
        }

        @Override
        public void run() {
            super.run();
            System.out.println("MyThread run方法："+this.currentThread().getName());
            System.out.println("MyThread run方法--："+this.getName());
            System.out.println("mythread 执行结束。");
        }
    }


    static class MyThread1 extends Thread {

        MyThread1(String name) {
            setName(name);
            System.out.println("MyThread 构造方法：" + this.currentThread().getName());
        }

        @Override
        public void run() {
            super.run();
            System.out.println("MyThread run方法："+this.currentThread().getName());
            System.out.println("MyThread run方法--："+this.getName());
            System.out.println("mythread 执行结束。");
        }
    }
}
