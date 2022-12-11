package com.huazie.flea.concurrency.taskshutdown.demo;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 不支持关闭的生产者--消费者日志服务
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LogWriter {

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(100);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread {

        private final PrintWriter writer;

        LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }

        public void run() {

            try {
                while (true)
                    writer.println(queue.take());
            } catch (InterruptedException e) {
                //
            } finally {
                writer.close();
            }
        }
    }
}

