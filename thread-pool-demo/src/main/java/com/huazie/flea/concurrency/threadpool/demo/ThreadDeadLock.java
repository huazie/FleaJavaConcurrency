package com.huazie.flea.concurrency.threadpool.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在单线程 Executor 中任务发生死锁（Don't do that）
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class ThreadDeadLock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁 ----- 由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            return "";
        }
    }

}
