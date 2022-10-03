package com.huazie.flea.concurrency.threadexecutor.demo4;

import com.huazie.flea.concurrency.common.pojo.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import static com.huazie.flea.concurrency.common.util.RequestUtils.dispatchRequest;
import static com.huazie.flea.concurrency.common.util.RequestUtils.isShutdownRequest;
import static com.huazie.flea.concurrency.common.util.RequestUtils.readRequest;

/**
 * 通过增加生命周期支持来扩展 Web 服务器的功能
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LifecycleWebServer {
    private final ExecutorService exec = Executors.newCachedThreadPool();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                exec.execute(new Runnable() {
                    public void run() {
                        handleRequest(connection);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {

                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    void handleRequest(Socket connection) {
        Request request = readRequest(connection);
        if (isShutdownRequest(request))
            stop();
        else
            dispatchRequest(request);
    }


}
