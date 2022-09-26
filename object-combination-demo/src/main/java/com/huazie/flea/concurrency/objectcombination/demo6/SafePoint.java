package com.huazie.flea.concurrency.objectcombination.demo6;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 可变且线程安全的车辆位置类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class SafePoint {

    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    /**
     * 如果将拷贝构造函数实现为 this(p.x, p.y), 那么会产生竞态条件，而私有构造函数则可以避免这种竞态条件。
     * 这是私有构造函数捕获模式的一个实例。
     */
    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[] {x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

