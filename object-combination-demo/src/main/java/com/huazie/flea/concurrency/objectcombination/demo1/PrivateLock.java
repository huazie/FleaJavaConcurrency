package com.huazie.flea.concurrency.objectcombination.demo1;

import javax.annotation.concurrent.GuardedBy;

/**
 * <p> 通用一个私有锁来保护状态 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("myLock")
    Person person;

    void someMethod() {
        synchronized (myLock) {
            // 访问和修改Widget的状态
        }
    }
}
