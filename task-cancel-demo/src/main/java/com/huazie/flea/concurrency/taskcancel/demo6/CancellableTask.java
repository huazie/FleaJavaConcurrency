package com.huazie.flea.concurrency.taskcancel.demo6;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}
