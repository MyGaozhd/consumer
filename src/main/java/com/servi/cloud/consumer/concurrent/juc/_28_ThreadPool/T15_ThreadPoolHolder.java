
package com.servi.cloud.consumer.concurrent.juc._28_ThreadPool;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class T15_ThreadPoolHolder {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };

        for (int i = 0; i < 20; i++) {
            T15_ThreadPoolHolder.getInstance().exec(r);
        }
        //T15_ThreadPoolHolder.getInstance().exe.allowCoreThreadTimeOut(true);

        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getCorePoolSize());

        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getTaskCount());

        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getPoolSize());

        Thread.sleep(10000);

        System.out.println("===========================================");
        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getCorePoolSize());
        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getTaskCount());
        System.out.println(T15_ThreadPoolHolder.getInstance().exe.getPoolSize());
    }

    /**
     * 核心线程池大小
     */
//    private int corePoolSize = (Runtime.getRuntime().availableProcessors()) > 0 ? Runtime.getRuntime().availableProcessors() << 1 : 2;
    private int corePoolSize = 3;

    /**
     * 无界队列的线程池。默认线程池的核心线程数是cpu核数的2倍。
     */
    private SSCThreadPoolExecutor exe = new SSCThreadPoolExecutor(corePoolSize);

    /**
     * 单路对象
     */
    private static volatile T15_ThreadPoolHolder INSTANCE = null;

    /**
     * @return
     * @Description 简单的单例构造
     */
    public static T15_ThreadPoolHolder getInstance() {

        if (INSTANCE == null) {
            synchronized (T15_ThreadPoolHolder.class) {
                if (INSTANCE == null) {
                    INSTANCE = new T15_ThreadPoolHolder();
                }
            }
        }

        return INSTANCE;
    }

    private T15_ThreadPoolHolder() {

    }

    public <R extends Runnable> void exec(R r) {

        exe.execute(r);
    }

    ;

    public <E, C extends Callable<E>> FutureTask<E> submit(C c) {

        FutureTask<E> future = new SSCFutureTask<E>(c);
        exe.execute(future);
        return future;
    }

    ;

    /**
     * @author gaozhdf@yonyou.com
     * @ClassName SSCThreadPoolExecutor
     * @Description 共享服务平台的线程池
     * @date 2020年1月9日 下午5:09:05
     */
    private static final class SSCThreadPoolExecutor extends ThreadPoolExecutor {

        private SSCThreadPoolExecutor(int corePoolSize) {

            super(corePoolSize, corePoolSize * 2, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), new SSCThreadFactory(), new SSCRejectedExecutionHandler());
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {


        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {

        }
    }

    /**
     * @author gaozhdf@yonyou.com
     * @ClassName SSCRejectedExecutionHandler
     * @Description 拒绝策略
     * @date 2020年1月9日 下午5:09:05
     */
    private static final class SSCRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            ServiLogger.log("SSCExecutorService拒绝执行:");
        }
    }

    /**
     * @author gaozhdf@yonyou.com
     * @ClassName SSCThreadFactory
     * @Description 自定义线程工厂，定义线程名称
     * @date 2020年1月9日 下午5:08:47
     */
    private static class SSCThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        private SSCThreadFactory() {

            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "servipool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {

            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
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
}
