package com.huazie.flea.concurrency.basicbuildingmodule.demo2;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁 是一种同步工具类，它可以延迟线程的进度直到其到达终止状态。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);

        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}

