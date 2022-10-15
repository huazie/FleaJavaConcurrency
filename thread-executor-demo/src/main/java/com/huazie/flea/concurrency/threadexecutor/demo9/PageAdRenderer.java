package com.huazie.flea.concurrency.threadexecutor.demo9;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderPageBody;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 在指定时间内获取广告信息
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PageAdRenderer {

    private static final Long TIME_BUDGET = 2000000000L;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final Ad DEFAULT_AD = new Ad();

    public Page renderPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = executor.submit(new FetchAdTask());
        // 等待广告的同时显示页面
        Page page = renderPageBody();
        Ad ad;
        try {
            // 只等待指定的时间长度
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft, NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }
        page.setAd(ad);
        return page;
    }
}
