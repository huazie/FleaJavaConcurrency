package com.huazie.flea.concurrency.objectcombination.demo3;

import javax.annotation.concurrent.Immutable;

/**
 * 在 DelegatingVehicleTracker中使用的不可变Point类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
