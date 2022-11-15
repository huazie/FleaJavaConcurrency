package com.huazie.flea.concurrency.taskcancel.demo4;

import com.huazie.flea.concurrency.taskcancel.demo.PrimeGenerator;
import com.huazie.flea.concurrency.taskcancel.demo2.PrimeProducer;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeRunTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TimeRunTest.class);

    /**
     * 任务响应中断请求，在限时1s后结束
     */
    @Test
    public void timeRun() {
        LOGGER.debug("timeRun start");
        BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(100);
        PrimeProducer producer = new PrimeProducer(primes);
        TaskUtils.timeRun(producer, 1, SECONDS);
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务在超时之前完成
     */
    @Test
    public void timeRun1() {
        LOGGER.debug("timeRun start");
        TaskUtils.timeRun(new Runnable() {
            @Override
            public void run() {
                LOGGER.debug("task");
            }
        }, 400, TimeUnit.MILLISECONDS);
        try {
            LOGGER.debug("sleep start");
            SECONDS.sleep(1);
            LOGGER.debug("sleep end");
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务不响应中断请求
     */
    @Test
    public void timeRun2() {
        LOGGER.debug("timeRun start");
        TaskUtils.timeRun(new PrimeGenerator(), 400, TimeUnit.MILLISECONDS);
        try {
            LOGGER.debug("sleep start");
            SECONDS.sleep(1);
            LOGGER.debug("sleep end");
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务不响应中断请求
     */
    @Test
    public void timeRunNew() {
        LOGGER.debug("timeRun start");
        try {
            TaskUtils.timeRunNew(new PrimeGenerator(), 1, SECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务在超时之前完成
     */
    @Test
    public void timeRunNew1() {
        LOGGER.debug("timeRun start");
        try {
            TaskUtils.timeRunNew(new Runnable() {
                @Override
                public void run() {
                    LOGGER.debug("task");
                }
            }, 400, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务响应中断请求
     */
    @Test
    public void timeRunNew2() {
        LOGGER.debug("timeRun start");
        try {
            BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(100);
            PrimeProducer producer = new PrimeProducer(primes);
            TaskUtils.timeRunNew(producer, 1, SECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务运行中会响应中断请求
     */
    @Test
    public void timeRunByFuture() {
        LOGGER.debug("timeRun start");
        try {
            BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(100);
            PrimeProducer producer = new PrimeProducer(primes);
            TaskUtils.timeRunByFuture(producer, 1, SECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务运行中不会响应中断请求
     */
    @Test
    public void timeRunByFuture1() {
        LOGGER.debug("timeRun start");
        try {
            TaskUtils.timeRunByFuture(new PrimeGenerator(), 500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }

    /**
     * 任务超时之前完成
     */
    @Test
    public void timeRunByFuture2() {
        LOGGER.debug("timeRun start");
        try {
            TaskUtils.timeRunByFuture(new Runnable() {
                @Override
                public void run() {
                    LOGGER.debug("task");
                }
            }, 400, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.debug("InterruptedException");
        }
        LOGGER.debug("timeRun end");
    }
}
