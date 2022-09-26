package com.huazie.flea.concurrency.basicbuildingmodule.demo1;

import com.huazie.flea.concurrency.common.util.FileRecord;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 一个消费者任务，即从队列中取出文件名称并对它们建立索引，它会一直运行下去。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Indexer implements Runnable {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(Indexer.class);

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while(true) {
                File file = queue.take();
                FileRecord.indexFile(file);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(file.getAbsolutePath());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

