package com.huazie.flea.concurrency.threadexecutor.demo8;

import com.huazie.flea.concurrency.threadexecutor.ImageData;
import com.huazie.flea.concurrency.threadexecutor.ImageInfo;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.huazie.flea.concurrency.common.util.ExceptionUtils.launderThrowable;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderImage;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderText;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.scanForImageInfo;

/**
 * 使用 CompletionService，使页面元素在下载完成后立即显示出来
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompletionServiceRenderer {

    private final ExecutorService executor;

    CompletionServiceRenderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {

        List<ImageInfo> info = scanForImageInfo(source);

        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);

        for (final ImageInfo imageInfo : info)
            completionService.submit(new Callable<ImageData>() {
                public ImageData call() {
                    return imageInfo.downloadImage();
                }
            });

        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
