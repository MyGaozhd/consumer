package com.servi.cloud.consumer.concurrent.jmm;

public class TestUser {

    public static void main(String[] args) {
        User user = new User();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.add();
            System.out.println("age：" + user.age);
        }).start();

        while (user.age == 0) {

        }

        System.out.println("结束");
    }
}
