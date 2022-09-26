package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import com.huazie.flea.concurrency.common.util.ExceptionUtils;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 将用于缓存值的 Map 重新定义为 ConcurrentHashMap<A, Future>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> eval = new Callable<V>() {
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };

            FutureTask<V> futureTask = new FutureTask<V>(eval);
            future = futureTask;
            cache.put(arg, futureTask);
            futureTask.run(); // 这里将会调用 eval.call()
        }

        try {
            return future.get();
        } catch (ExecutionException e) {
            throw ExceptionUtils.launderThrowable(e.getCause());
        }
    }
}
