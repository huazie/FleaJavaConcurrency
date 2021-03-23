package com.huazie.flea.concurrency.objectsharing.demo;

/**
 * <p> 在没有同步的情况下共享变量（不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoVisibility {

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
