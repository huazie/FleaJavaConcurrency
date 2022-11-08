package com.huazie.flea.concurrency.taskcancel.demo;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 素数生成器，使用 volatile 类型的域来保存取消状态
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrimeGenerator.class);

    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();
    // 为了使这个过程能可靠地工作，标志 cancelled 必须为 volatile 类型
    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        while(!cancelled) {
            LOGGER.debug("before = {}", p);
            p = p.nextProbablePrime();
            LOGGER.debug("prime = {}", p);
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }
}
