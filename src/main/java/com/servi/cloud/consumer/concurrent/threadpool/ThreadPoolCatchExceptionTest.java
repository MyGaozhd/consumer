package com.servi.cloud.consumer.concurrent.threadpool;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试结论
 * 线程池 在线程工厂【efaultThreadFactory】创建线程时，如果设置了setUncaughtExceptionHandler，则如果线程出现异常，会被setUncaughtExceptionHandler捕获，并在子线程中捕获，前提是执行的是 runnable，
 * beforeExecute 和 afterExecute 内的异常同样会被setUncaughtExceptionHandler捕获
 * 线程池 如果接受 future任务，只有在get时 捕获异常，不会被setUncaughtExceptionHandler 捕获异常。
 *
 */
public class ThreadPoolCatchExceptionTest {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private int count = 1;
    ThreadPoolExecutor service = new ThreadPoolExecutor(count, count * 2,
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
            if (r instanceof TestRunnable) {
                threadLocal.set(((TestRunnable) r).getCount());
            }
            ServiLogger.log("beforeExecute：" + threadLocal.get());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            threadLocal.remove();
            ServiLogger.log("afterExecute：" + threadLocal.get());
//            throw  new RuntimeException();
        }
    };

    public void submit(Runnable r) {
//       service.submit(r);
        service.execute(r);
    }


    public <E> FutureTask<E> execute(Callable<E> c) {

        FutureTask<E> future = new SSCFutureTask<E>(c);
        service.execute(future);
        return future;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolCatchExceptionTest pool = new ThreadPoolCatchExceptionTest();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
//            FutureTask task = pool.execute(new AsyncRequestCallable(i));
//            try {
//                System.out.println("结果：" + task.get() + "");
//
//            } catch (ExecutionException e) {
////                e.printStackTrace();
//                System.out.println("异常：" + e.getMessage() + "");
//            }
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
            throw new RuntimeException();
        }
    }

    public static class AsyncRequestCallable implements Callable<Object> {

        private int count;

        public int getCount() {
            return count;
        }

        public AsyncRequestCallable(int count) {
            this.count = count;
        }


        @Override
        public Object call() throws Exception {

            ServiLogger.log("执行当前任务：" + count);
            throw new RuntimeException();
//            return count;

        }
    }

    /**
     * @param <V>
     * @author gaozhdf@yonyou.com
     * @ClassName SSCFutureTask
     * @Description Future任务
     * @date 2020年1月9日 下午5:46:23
     */
    public static final class SSCFutureTask<V> extends FutureTask<V> {

        private Callable<V> c = null;

        public SSCFutureTask(Callable<V> callable) {

            super(callable);
            c = callable;
        }

        public Callable<V> getC() {

            return c;
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
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(Thread.currentThread().getName() + "：111");
                }
            });
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
