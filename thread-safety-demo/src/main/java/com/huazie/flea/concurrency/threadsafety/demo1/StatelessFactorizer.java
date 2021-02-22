package com.huazie.flea.concurrency.threadsafety.demo1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 无状态对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StatelessFactorizer implements Runnable {

    private BigInteger factor; // 因数

    public StatelessFactorizer(BigInteger factor) {
        this.factor = factor;
    }

    @Override
    public void run() {

    }
}
