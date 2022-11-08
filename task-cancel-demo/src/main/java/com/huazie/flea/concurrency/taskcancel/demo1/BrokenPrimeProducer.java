package com.huazie.flea.concurrency.taskcancel.demo1;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作中（不要这么做）
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class BrokenPrimeProducer extends Thread {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(BrokenPrimeProducer.class);

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                LOGGER.debug("before = {}", p);
                queue.put(p = p.nextProbablePrime());
                LOGGER.debug("prime = {}", p);
            }
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException");
        }
    }

    public void cancel() {
        cancelled = true;
        LOGGER.debug("cancel");
    }
}
