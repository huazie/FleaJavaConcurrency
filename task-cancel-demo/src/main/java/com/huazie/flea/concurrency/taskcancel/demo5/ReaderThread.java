package com.huazie.flea.concurrency.taskcancel.demo5;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;

/**
 * 通过改写 interrput 方法将非标准的取消操作封装在 Thread 中
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReaderThread extends Thread {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(ReaderThread.class);

    private final Socket socket;

    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        LOGGER.debug("start interrupt");
        try {
            socket.close();
            LOGGER.debug("socket close");
        } catch (IOException e) {
            //
        } finally {
            super.interrupt();
            LOGGER.debug("end interrupt");
        }
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                processData(data);
            }
        } catch (IOException e) {
            // 允许线程退出
        }
    }

    /**
     * 输出 0 ~ data 区间内的素数
     */
    private void processData(String data) {
        LOGGER.debug("0 < All Primes < {}", data);
        BigInteger prime = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted() && prime.compareTo(BigInteger.valueOf(Long.valueOf(data))) < 0) {
            LOGGER.debug("prime = {}", prime);
            prime = prime.nextProbablePrime();
        }
    }
}
