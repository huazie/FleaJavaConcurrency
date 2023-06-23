package com.huazie.flea.concurrency.threadpool.demo1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 自定义饱和策略的处理逻辑
    }
}
