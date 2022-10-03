package com.huazie.flea.concurrency.threadexecutor.demo5;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 演示 Timer 问题
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class OutOfTime {

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {
        public void run() {
            throw new RuntimeException();
        }
    }
}
