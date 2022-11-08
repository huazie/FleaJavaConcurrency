package com.huazie.flea.concurrency.taskcancel.demo4;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.huazie.flea.concurrency.common.util.ExceptionUtils.launderThrowable;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TaskUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TaskUtils.class);

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);

    public static void timeRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }

    public static void timeRunNew(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (null != t)
                    throw launderThrowable(t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
//        cancelExec.schedule(new Runnable() {
//            public void run() {
//                taskThread.interrupt();
//            }
//        }, timeout, unit);
        LOGGER.debug("join start");
        // 线程 taskThread 至多等待指定时间后结束
        taskThread.join(unit.toMillis(timeout));
        LOGGER.debug("join end");
        task.rethrow();
    }

    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timeRunByFuture(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            // 如果任务中抛出了异常，那么重新抛出该异常
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            // 任务超时，最终 finally 也会将任务取消
            LOGGER.error1(new Object(){}, "TimeoutException");
        } finally {
            // 如果任务已经结束，那么执行取消操作也不会带来任何影响
            // 如果任务正在运行，那么将被中断
            LOGGER.debug1(new Object(){}, "task is done : {}", task.isDone());
            LOGGER.debug1(new Object(){}, "cancel start");
            task.cancel(true);
            LOGGER.debug1(new Object(){}, "cancel end");
            LOGGER.debug1(new Object(){}, "task is cancelled : {}", task.isCancelled());

        }
    }
}