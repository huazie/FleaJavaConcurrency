package com.huazie.flea.concurrency.objectsharing.demo4;

import javax.annotation.concurrent.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * <p> 对数值及其因数分解结果进行缓存的不可变容器类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        if (null != lastFactors) {
            this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
        } else {
            this.lastFactors = null;
        }
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (null == lastNumber || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
