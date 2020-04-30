package com.servi.cloud.consumer.concurrent.juc._17_juc_tool;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段
 * person 3 到达
 * person 4 到达
 * person 1 到达
 * person 2 到达
 * person 0 到达
 * 所有人到齐了！
 * person 1 开吃!
 * person 4 开吃!
 * person 3 开吃!
 * person 0 开吃!
 * person 2 开吃!
 * 所有人吃完了！
 * person 0 离开
 * person 4 离开
 * person 1 离开
 * person 3 离开
 * person 2 离开
 * 所有人离开了！
 * 婚礼结束！
 */
public class T08_TestPhaser {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //批量注册parties,有点类似于CountdownLatch的倒数计数的初始化
        phaser.bulkRegister(5);

        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(() -> {

                Person p = new Person("person " + nameIndex);
                p.arrive();
                phaser.arriveAndAwaitAdvance();

                p.eat();
                phaser.arriveAndAwaitAdvance();

                p.leave();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }

    }


    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + "phase = " + phase + " registeredParties = " + registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + "phase = " + phase + " registeredParties = " + registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + "phase = " + phase + " registeredParties = " + registeredParties);
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 开吃!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开\n", name);
        }

    }
}


