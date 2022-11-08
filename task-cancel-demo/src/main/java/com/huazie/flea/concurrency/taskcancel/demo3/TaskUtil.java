package com.huazie.flea.concurrency.taskcancel.demo3;

import java.util.concurrent.BlockingQueue;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TaskUtil {

    private TaskUtil() {
    }

    public static Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // 重新尝试
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt(); // 恢复中断
        }
    }


}
