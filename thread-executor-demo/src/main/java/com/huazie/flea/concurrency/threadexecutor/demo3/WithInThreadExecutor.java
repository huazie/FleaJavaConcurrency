package com.huazie.flea.concurrency.threadexecutor.demo3;

import java.util.concurrent.Executor;

/**
 * 类似单线程的行为，以同步的方式执行每个任务。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class WithInThreadExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
