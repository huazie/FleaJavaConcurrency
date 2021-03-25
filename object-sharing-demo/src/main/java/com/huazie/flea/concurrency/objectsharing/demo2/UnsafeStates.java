package com.huazie.flea.concurrency.objectsharing.demo2;

/**
 * <p> 使内部的可变状态逸出（不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnsafeStates {

    private String[] states = new String[] {"HELLO", "HUAZIE"};

    public String[] getStates() {
        return states;
    }
}
