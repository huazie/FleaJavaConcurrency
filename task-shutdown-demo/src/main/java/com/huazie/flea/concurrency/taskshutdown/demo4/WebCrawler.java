package com.huazie.flea.concurrency.taskshutdown.demo4;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class WebCrawler {

    private static final long TIMEOUT = 1;

    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    private volatile TrackingExecutor exec;

    private final Set<URL> urlsToCrawl = new HashSet<>();

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl) {
            submitCrawlTask(url);
        }
        urlsToCrawl .clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT))
                saveUncrawled(exec.getCancelledTasks());
        } finally {
            exec = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable task : uncrawled)
            urlsToCrawl.add(((CrawlTask) task).getPage());
    }

    private void submitCrawlTask(URL url) {
        exec.execute(new CrawlTask(url));
    }

    private class CrawlTask implements Runnable {

        private final URL url;

        CrawlTask(URL url) {
            this.url = url;
        }

        public URL getPage() {
            return url;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted())
                    return;
                submitCrawlTask(link);
            }
        }
    }
}
