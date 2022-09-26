package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import java.math.BigInteger;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
        // 在经过长时间的计算后。。。
        return new BigInteger(arg);
    }
}
