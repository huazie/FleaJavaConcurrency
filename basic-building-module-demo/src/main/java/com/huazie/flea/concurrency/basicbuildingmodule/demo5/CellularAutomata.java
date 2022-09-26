package com.huazie.flea.concurrency.basicbuildingmodule.demo5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过栅栏来计算细胞的自动化模拟
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CellularAutomata {
    private final Board mainBroad;

    private final CyclicBarrier barrier;

    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBroad = board;
        int count = Runtime.getRuntime().availableProcessors();
        // 栅栏的构造参数可以传入一个 Runnable 的匿名内部类
        this.barrier = new CyclicBarrier(count, new Runnable(){
            public void run() {
                mainBroad.commitNewValues();
            }
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++)
            workers[i] = new Worker(mainBroad.getSubBoard(count, i));
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++)
                    for (int y = 0; y < board.getMaxY(); y++)
                        board.setNewValue(x, y, computeValue(x, y));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private Board computeValue(int x, int y) {
            x = 2 * x + y;
            y = 2 * y + x;
            return new Board(x, y);
        }
    }

    public void start() {
        for (int i = 0; i < workers.length; i++)
            new Thread(workers[i]).start();
        mainBroad.waitForConvergence();
    }
}

