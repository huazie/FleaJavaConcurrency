package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static com.huazie.flea.concurrency.common.util.ExceptionUtils.launderThrowable;

/**
 * 使用 ConcurrentHashMap 中的原子方法 putIfAbsent，来避免 Memoizer3 中的问题
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Memoizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };

                FutureTask<V> futureTask = new FutureTask<V>(eval);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); // 这里将会调用 eval.call()
                }
            }

            try {
                return future.get();
            } catch (CancellationException e) {
                // 计算被取消，把Future从缓存中移除
                cache.remove(arg, future);
            } catch (RuntimeException e) {
                // 计算失败，把Future从缓存中移除
                cache.remove(arg, future);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }
}
