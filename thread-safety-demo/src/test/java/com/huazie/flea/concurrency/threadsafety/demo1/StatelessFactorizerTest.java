package com.huazie.flea.concurrency.threadsafety.demo1;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StatelessFactorizerTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(StatelessFactorizerTest.class);

    @Test
    public void factor() {
        factor(900);
    }

    private void factor(int num) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                result.append(i).append(", ");
                num /= i;
                i--;
            }

        }
        result.append(num).append("]");
        LOGGER.debug1(new Object() {},"因数分解的结果为：{}", result.toString());
    }

    @Test
    public void factorForBigInteger() {
        // 19777745 * 123456747 = 2,441,696,060,695,515
        BigInteger[] resultArr = factor(new BigInteger("19777745"));
        LOGGER.debug1(new Object() {},"因数分解的结果为：{}", Arrays.toString(resultArr));
        BigInteger[] resultArr1 = factor(new BigInteger("123456747"));
        LOGGER.debug1(new Object() {},"因数分解的结果为：{}", Arrays.toString(resultArr1));
        BigInteger[] resultArr2 = factor(new BigInteger("2441696060695515"));
        LOGGER.debug1(new Object() {},"因数分解的结果为：{}", Arrays.toString(resultArr2));
    }

    /**
     * 因数因数分解
     *
     * @param factor 待因数分解的因数
     * @return 因数因数分解结果数组
     */
    private BigInteger[] factor(BigInteger factor) {
        List<BigInteger> resultList = new ArrayList<>();

        BigInteger mFactor = new BigInteger(factor.toString());
        for (BigInteger first = BigInteger.valueOf(2); first.compareTo(sqrt(mFactor)) <= 0; first = first.add(BigInteger.ONE)) {
            if (mFactor.remainder(first).compareTo(BigInteger.ZERO) == 0) {
                resultList.add(first);
                mFactor = mFactor.divide(first);
                first = first.min(BigInteger.ONE);
            }
        }
        resultList.add(mFactor);
        return resultList.toArray(new BigInteger[0]);
    }

    /**
     * 大数的开平方根
     *
     * @param num 指定大数
     * @return 大数的开平方根
     */
    private BigInteger sqrt(BigInteger num) {
        BigInteger two = BigInteger.valueOf(2);
        BigInteger prv = num.divide(two);
        BigInteger now = prv.add(num.divide(prv)).divide(two);
        while (prv.compareTo(now) > 0) {
            prv = now;
            now = prv.add(num.divide(prv)).divide(two);
        }
        return now;
    }

    @Test
    public void run() {
        assertTrue(false);
    }
}