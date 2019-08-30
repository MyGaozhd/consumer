package com.servi.cloud.consumer.concurrent.threadpool;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private int count = 1;
    ExecutorService service = new ThreadPoolExecutor(count, count * 2,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(2), new DefaultThreadFactory(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof TestRunnable) {
                ServiLogger.log("拒绝当前任务：" + ((TestRunnable) r).getCount());
            }
        }
    }) {
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            threadLocal.set(((TestRunnable) r).getCount());
            ServiLogger.log("beforeExecute：" + threadLocal.get());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            threadLocal.remove();
            ServiLogger.log("afterExecute：" + threadLocal.get());
        }
    };

    public void submit(Runnable r) {
//        service.submit(r);
        service.execute(r);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTest pool = new ThreadPoolTest();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            pool.submit(new TestRunnable(i));
        }

        Thread.sleep(3000);
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

    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
//            if (t.isDaemon())
            t.setDaemon(true);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
