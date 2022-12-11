package com.huazie.flea.concurrency.taskshutdown.demo2;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LogService {

    private static final long TIMEOUT = 1;

    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    private final PrintWriter writer;

    public LogService(Writer writer) {
        this.writer = (PrintWriter) writer;
    }

    public void start() {

    }

    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        } finally {
            writer.close();
        }
    }

    public void log(String msg) {
        try {
            exec.execute(new WriteTask(msg));
        } catch (RejectedExecutionException ignored) {
            //
        }
    }

    class WriteTask implements Runnable {

        String msg;

        WriteTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            writer.println(msg);
        }
    }
}
