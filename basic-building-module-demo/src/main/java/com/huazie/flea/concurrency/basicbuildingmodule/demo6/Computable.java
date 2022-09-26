package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

/**
 * 计算接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
