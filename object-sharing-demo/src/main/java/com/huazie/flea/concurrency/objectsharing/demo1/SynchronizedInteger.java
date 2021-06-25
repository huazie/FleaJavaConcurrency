package com.huazie.flea.concurrency.objectsharing.demo1;

import javax.annotation.concurrent.GuardedBy;

/**
 * <p> 非线程安全的可变整数类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
