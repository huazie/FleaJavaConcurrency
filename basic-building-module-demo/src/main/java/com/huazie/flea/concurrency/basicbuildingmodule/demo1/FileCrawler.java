package com.huazie.flea.concurrency.basicbuildingmodule.demo1;

import com.huazie.flea.concurrency.common.util.FileRecord;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者任务，即在某个文件层次结构中搜索符合索引标准的文件，并将它们的名称放入工作队列。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;

    private final FileFilter fileFilter;

    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries)
                if (entry.isDirectory())
                    crawl(entry);
                else if (!FileRecord.alreadyIndexed(entry))
                    fileQueue.put(entry);
        }
    }

}

