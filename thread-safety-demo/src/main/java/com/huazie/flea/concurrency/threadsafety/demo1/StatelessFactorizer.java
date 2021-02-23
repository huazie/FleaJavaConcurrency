package com.huazie.flea.concurrency.threadsafety.demo1;

import com.huazie.frame.algorithm.factorization.Factor;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * <p> 无状态对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StatelessFactorizer implements Runnable {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(StatelessFactorizer.class);

    private String factor; // 因数

    public StatelessFactorizer(String factor) {
        this.factor = factor;
    }

    @Override
    public void run() {
        BigInteger input = new BigInteger(factor);
        BigInteger[] resultArr = Factor.factor(input);
        LOGGER.debug("因式分解的结果为：{}", Arrays.toString(resultArr));
    }
}
