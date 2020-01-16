package com.servi.cloud.consumer.concurrent.thread.suspendandresume;

/**
 * 停止和恢复
 */
public class SuspendAndResume {


//    //外部停止，外部恢复
//    public static void main(String[] args) throws InterruptedException {
//        MyThread1 thread = new MyThread1("mythread");
//        System.out.println("执行开始：" + thread.isAlive());
//        thread.start();
//        Thread.sleep(50);
//        thread.suspend();
//        System.out.println("suspend---1:i = " + thread.getI());
//        Thread.sleep(50);
//        System.out.println("suspend---2:i = " + thread.getI());
//        thread.resume();
//        System.out.println("resume---1:i = " + thread.getI());
//        Thread.sleep(50);
//        System.out.println("resume---2:i = " + thread.getI());
//    }


    //内部停止，外部恢复
    public static void main(String[] args) throws InterruptedException {
        MyThread2 thread = new MyThread2("mythread");
        System.out.println("执行开始：" + thread.isAlive());
        thread.start();
        Thread.sleep(50);
        System.out.println("suspend---2:i = " + thread.isAlive());
        thread.resume();
    }

    static class MyThread1 extends Thread {
        int i = 0;

        MyThread1(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                i++;
            }
        }

        public int getI() {
            return i;
        }
    }

    static class MyThread2 extends Thread {

        MyThread2(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            System.out.println("MyThread run方法：开始执行");
            this.suspend();
            System.out.println("MyThread run方法：执行结束");
        }
    }
}
