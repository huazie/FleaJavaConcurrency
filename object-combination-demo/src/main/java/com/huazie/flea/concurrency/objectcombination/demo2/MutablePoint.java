package com.huazie.flea.concurrency.objectcombination.demo2;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 与 Java.awt.Point 类似的可变Point类（不推荐）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class MutablePoint {

    public int x;

    public int y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

}
