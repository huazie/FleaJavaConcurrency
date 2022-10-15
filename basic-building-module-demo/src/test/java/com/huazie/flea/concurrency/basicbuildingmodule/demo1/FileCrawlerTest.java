package com.huazie.flea.concurrency.basicbuildingmodule.demo1;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileCrawlerTest {

    private static final int BOUND = 1000;

    private static final int N_CONSUMERS = 5;

    public static void main(String[] args) throws Exception {
        File file = new File("E:\\fleaworkspace");
        File file1 = new File("E:\\Software\\Maven\\Repository");
        File[] roots = {file, file1};
        startIndexing(roots);
    }

    private static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        };

        for (File root : roots)
            new Thread(new FileCrawler(queue, fileFilter, root)).start();

        for (int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue)).start();
    }
}
