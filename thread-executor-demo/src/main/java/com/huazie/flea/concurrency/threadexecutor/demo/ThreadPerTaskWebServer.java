package com.huazie.flea.concurrency.threadexecutor.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.huazie.flea.concurrency.common.util.RequestUtils.handleRequest;

/**
 * 通过为每个请求创建一个新的线程来提供服务，从而实现更高的响应性
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }
}
