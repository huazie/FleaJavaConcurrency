package com.huazie.flea.concurrency.taskcancel.demo2;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来取消
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrimeProducer extends Thread {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrimeProducer.class);

    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                LOGGER.debug("before = {}", p);
                queue.put(p = p.nextProbablePrime());
                LOGGER.debug("prime = {}", p);
            }
        } catch (InterruptedException e) {
            // 允许线程退出
            LOGGER.error("InterruptedException");
        }
    }

    public void cancel() {
        interrupt(); // 中断线程
        LOGGER.debug("interrupt");
    }
}
