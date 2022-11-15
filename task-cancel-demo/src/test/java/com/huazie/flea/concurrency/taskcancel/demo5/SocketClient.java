package com.huazie.flea.concurrency.taskcancel.demo5;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        // 和服务器创建连接
        Socket socket = new Socket("localhost", 8888);

        // 要发送给服务器的信息
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("1000000\n" +
                "10000");
        pw.flush();
        socket.shutdownOutput();

        pw.close();
        os.close();
        socket.close();
    }
}
