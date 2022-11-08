package com.huazie.flea.concurrency.taskcancel.demo2;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class PrimeConsumer {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrimeConsumer.class);

    private static final int BOUND = 100;

    private int times = 0; // 消费次数

    public void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(BOUND);
        PrimeProducer producer = new PrimeProducer(primes);
        producer.start();
        try {
            while (needMorePrimes())
                consume(primes.take());
        } finally {
            producer.cancel();
        }
    }

    private void consume(BigInteger value) {
        times++;
        LOGGER.debug1(new Object() {}, "value = {}", value);
    }

    private boolean needMorePrimes() throws InterruptedException {
        Thread.sleep(1000);
        return times < 5;
    }
}
