package com.huazie.flea.concurrency.taskcancel.demo6;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrimeSumTask extends SocketUsingTask<BigInteger> {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrimeSumTask.class);

    public PrimeSumTask(Socket socket) {
        super(socket);
    }

    @Override
    public BigInteger call() {
        BigInteger result = null;
        try {
            InputStream in = getSocket().getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                result = processData(data);
            }
        } catch (IOException e) {
            // 允许线程退出
        }
        return result;
    }

    /**
     * 计算 0 ~ data 区间内的素数总和
     */
    private BigInteger processData(String data) {
        LOGGER.debug("0 < All Primes < {}", data);
        BigInteger prime = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        while (!Thread.currentThread().isInterrupted() && prime.compareTo(BigInteger.valueOf(Long.valueOf(data))) < 0) {
            sum = sum.add(prime);
            prime = prime.nextProbablePrime();
        }
        return sum;
    }
}
