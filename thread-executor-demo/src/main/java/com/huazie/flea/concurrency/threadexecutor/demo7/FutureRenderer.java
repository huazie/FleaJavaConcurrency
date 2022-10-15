package com.huazie.flea.concurrency.threadexecutor.demo7;

import com.huazie.flea.concurrency.threadexecutor.ImageData;
import com.huazie.flea.concurrency.threadexecutor.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.huazie.flea.concurrency.common.util.ExceptionUtils.launderThrowable;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderImage;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderText;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.scanForImageInfo;

/**
 * 使用 Future 等待图像下载
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FutureRenderer {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage (CharSequence source){
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
                public List<ImageData> call() {
                    List<ImageData> result = new ArrayList<ImageData>() ;
                    for (ImageInfo imageInfo : imageInfos)
                        result.add(imageInfo.downloadImage());
                    return result;
                }
            };
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData)
                renderImage(data);
        } catch (InterruptedException e){
            //重新设置线程的中断状态
            Thread.currentThread().interrupt() ;
            //由于不需要结果，因此取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
