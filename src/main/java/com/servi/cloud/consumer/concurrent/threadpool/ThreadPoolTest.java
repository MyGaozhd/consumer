package com.servi.cloud.consumer.concurrent.threadpool;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.concurrent.*;

public class ThreadPoolTest {
    private int count = 1;
    ExecutorService service = new ThreadPoolExecutor(count, count * 2,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof TestRunnable) {
                ServiLogger.log("拒绝当前任务：" + ((TestRunnable) r).getCount());
            }
        }
    });

    public void submit(Runnable r) {
        service.submit(r);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTest pool = new ThreadPoolTest();
        for (int i = 0; i < 10; i++) {
//            Thread.sleep(500);
            pool.submit(new TestRunnable(i));
        }
    }

    public static class TestRunnable implements Runnable {
        private int count;

        public int getCount() {
            return count;
        }

        public TestRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            ServiLogger.log("执行当前任务：" + count);
        }
    }
}
