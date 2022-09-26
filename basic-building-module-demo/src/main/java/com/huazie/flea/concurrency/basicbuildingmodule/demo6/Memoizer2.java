package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过 ConcurrentHashMap 代替 HashMap 来构建缓存
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
