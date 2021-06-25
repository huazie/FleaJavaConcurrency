package com.huazie.flea.concurrency.objectcombination.demo;

import javax.annotation.concurrent.GuardedBy;

/**
 * <p> 使用 Java 监视器模式的线程安全计数器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }
}
