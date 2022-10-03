package com.huazie.flea.concurrency.threadexecutor.demo2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

import static com.huazie.flea.concurrency.common.util.RequestUtils.handleRequest;

/**
 * 基于 Executor 的 Web 服务器。采用了一个固定长度的线程池，可以容纳 100 个线程
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TaskExecutionWebServer {

    private static final Executor exec = new ThreadPerTaskExecutor();

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
            };
            exec.execute(task);
        }
    }
}
