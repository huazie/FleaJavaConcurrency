package com.huazie.flea.concurrency.threadexecutor.demo2;

import java.util.concurrent.Executor;

/**
 * 为每个请求都创建新线程
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
