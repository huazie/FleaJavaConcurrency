package com.huazie.flea.concurrency.threadpool.demo5;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.CountDownLatch;

/**
 * 由 ConcurrentPuzzleSolver 使用的携带结果的闭锁
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@ThreadSafe
public class ValueLatch<T> {

    @GuardedBy("this")
    private T value = null;

    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return (done.getCount() == 0);
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
