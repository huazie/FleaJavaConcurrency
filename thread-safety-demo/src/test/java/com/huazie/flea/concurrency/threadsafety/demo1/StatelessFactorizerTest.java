package com.huazie.flea.concurrency.threadsafety.demo1;

import com.huazie.fleaframework.algorithm.factorization.Factor;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class StatelessFactorizerTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(StatelessFactorizerTest.class);

    @Test
    public void testFactor() {
        Integer[] resultArr = Factor.factor(900);
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void testFactorForBigInteger() {
        // 19777745 * 123456747 = 2,441,696,060,695,515
        BigInteger[] resultArr = Factor.factor(new BigInteger("19777745"));
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr));
        BigInteger[] resultArr1 = Factor.factor(new BigInteger("123456747"));
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr1));
        BigInteger[] resultArr2 = Factor.factor(new BigInteger("2441696060695515"));
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr2));
    }

}