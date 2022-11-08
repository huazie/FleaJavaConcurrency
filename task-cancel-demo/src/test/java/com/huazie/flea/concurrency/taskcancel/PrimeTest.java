package com.huazie.flea.concurrency.taskcancel;

import com.huazie.flea.concurrency.taskcancel.demo.PrimeGenerator;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 素数生成器测试
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrimeTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrimeTest.class);

    @Test
    public void primeTest() throws InterruptedException {
        LOGGER.debug("Primes = {}", aSecondOfPrimes());
    }

    /**
     * 一个仅运行一秒钟的素数生成器
     *
     * @return 素数列表
     */
    private List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            LOGGER.debug("sleep start");
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
