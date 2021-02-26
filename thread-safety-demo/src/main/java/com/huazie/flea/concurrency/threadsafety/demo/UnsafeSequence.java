package com.huazie.flea.concurrency.threadsafety.demo;

/**
 * <p> 非线程安全的数值序列生成器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnsafeSequence {

    private int value;

    /**
     * <p> 返回一个下一个数值 </p>
     *
     * @return 下一个数值
     */
    public int getNext() {
        return value++;
    }
}
