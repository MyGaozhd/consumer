package com.servi.cloud.consumer.concurrent.thread.interrupt;

/**
 * 如何优雅的停止线程
 */
public class InterruptThread {

//    //方法一：外部结束
//    public static void main(String[] args) throws InterruptedException {
//        MyThread1 thread = new MyThread1("mythread");
//        System.out.println("执行开始：" + thread.isAlive());
//        thread.start();
//        Thread.sleep(5);
//        thread.interrupt();
//
//        System.out.println("执行结束？：" + thread.isAlive());
//    }

//    //方法二：异常结束
//    public static void main(String[] args) throws InterruptedException {
//        MyThread2 thread = new MyThread2("mythread");
//        System.out.println("执行开始：" + thread.isAlive());
//        thread.start();
//        Thread.sleep(5);
//        thread.interrupt();
//
//        System.out.println("执行结束？：" + thread.isAlive());
//    }

    //方法三：return结束
    public static void main(String[] args) throws InterruptedException {
        MyThread3 thread = new MyThread3("mythread");
        System.out.println("执行开始：" + thread.isAlive());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();

        System.out.println("执行结束？：" + thread.isAlive());
    }

    //方法一：外部结束,结束后循环体外继续进行
    static class MyThread1 extends Thread {

        MyThread1(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 50000000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("外部结束：" + i);
                    break;
                }
                System.out.println("MyThread run方法：" + i);
            }
            System.out.println("MyThread run方法：执行完毕");
        }
    }

    //方法二：异常结束,结束后循环体外不继续进行
    static class MyThread2 extends Thread {

        MyThread2(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            try {
                for (int i = 0; i < 50000000; i++) {
                    if (this.isInterrupted()) {
                        System.out.println("外部结束：" + i);
                        throw new InterruptedException("异常结束");
                    }
                    System.out.println("MyThread run方法：" + i);
                }
                System.out.println("MyThread run方法：执行完毕");
            } catch (Exception e) {
                System.out.println("MyThread run方法：执行完毕" + e.getMessage());
            }
        }
    }


    //方法三：外部结束,结束后循环体外继续进行
    static class MyThread3 extends Thread {

        MyThread3(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            int i=0;
            while (true){
                if (this.isInterrupted()) {
                    System.out.println("外部结束：" + i++);
                    return;
                }
                System.out.println("MyThread run方法：" + i++);
            }
        }
    }
}
