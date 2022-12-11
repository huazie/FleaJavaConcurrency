package com.huazie.flea.concurrency.taskshutdown.demo3;

import com.huazie.flea.concurrency.common.util.FileRecord;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 通过 “毒丸” 对象来关闭服务
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class IndexingService {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(IndexingService.class);

    private static final File POISON = new File("");

    private final CrawlerThread producer = new CrawlerThread();

    private final IndexerThread consumer = new IndexerThread();

    private final BlockingQueue<File> queue;

    private final FileFilter fileFilter;

    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

    class CrawlerThread extends Thread {

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                // 发生异常
                LOGGER.debug("Occur InterruptedException");
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException el) {
                        // 重新尝试
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries)
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!FileRecord.alreadyIndexed(entry))
                        queue.put(entry);
            }
        }
    }

    class IndexerThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON)
                        break;
                    else
                        FileRecord.indexFile(file);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(file.getAbsolutePath());
                    }
                }
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
