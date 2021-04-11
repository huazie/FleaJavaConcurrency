package com.huazie.flea.concurrency.objectsharing.demo1;

import com.huazie.flea.concurrency.common.NotThreadSafe;

/**
 * <p> 非线程安全的可变整数类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
