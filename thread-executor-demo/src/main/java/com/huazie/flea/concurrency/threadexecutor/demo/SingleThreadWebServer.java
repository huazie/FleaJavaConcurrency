package com.huazie.flea.concurrency.threadexecutor.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.huazie.flea.concurrency.common.util.RequestUtils.handleRequest;

/**
 * 串行地处理它的任务（即通过 80 端口接收到的 HTTP 请求）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }
}
