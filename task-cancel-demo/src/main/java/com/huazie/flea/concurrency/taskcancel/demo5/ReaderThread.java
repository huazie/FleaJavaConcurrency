package com.huazie.flea.concurrency.taskcancel.demo5;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写 interrput 方法将非标准的取消操作封装在 Thread 中
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class ReaderThread extends Thread {

    private final Socket socket;

    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            //
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[2048];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) {
            // 允许线程退出
        }
    }

    private void processBuffer(byte[] buf, int count) {
    }
}
