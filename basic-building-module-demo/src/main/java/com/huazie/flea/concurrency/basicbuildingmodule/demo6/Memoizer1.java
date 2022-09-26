package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过 HashMap 和 并发机制来构建缓存
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Memoizer1<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();

    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
