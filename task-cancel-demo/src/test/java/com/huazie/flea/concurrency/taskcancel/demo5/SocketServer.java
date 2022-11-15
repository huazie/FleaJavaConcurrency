package com.huazie.flea.concurrency.taskcancel.demo5;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SocketServer {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SocketServer.class);

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws IOException {
        // 创建服务端socket
        ServerSocket serverSocket = new ServerSocket(8888);

        //循环监听等待客户端的连接
        while (true) {
            // 监听客户端
            LOGGER.debug("start serverSocket.accept()");
            // 创建客户端socket
            Socket socket = serverSocket.accept();
            LOGGER.debug("end serverSocket.accept()");

            ReaderThread readerThread = new ReaderThread(socket);
            readerThread.start();

            // 演示 2s后中断 ReaderThread
            cancelExec.schedule(new Runnable() {
                public void run() {
                    readerThread.interrupt();
                }
            }, 2, SECONDS);

        }
    }
}
