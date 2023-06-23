package com.huazie.flea.concurrency.threadpool.demo3;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
