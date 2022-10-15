package com.huazie.flea.concurrency.threadexecutor.demo6;

import com.huazie.flea.concurrency.threadexecutor.ImageData;
import com.huazie.flea.concurrency.threadexecutor.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderImage;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.renderText;
import static com.huazie.flea.concurrency.threadexecutor.RendererUtil.scanForImageInfo;

/**
 * 串行地渲染页面元素
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SingleThreadRenderer {

    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        for (ImageData data : imageData)
            renderImage(data);
    }

}
