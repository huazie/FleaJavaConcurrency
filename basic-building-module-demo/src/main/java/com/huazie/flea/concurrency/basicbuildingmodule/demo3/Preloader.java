package com.huazie.flea.concurrency.basicbuildingmodule.demo3;

import com.huazie.flea.concurrency.common.util.ExceptionUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
            new FutureTask<>(new Callable<ProductInfo>() {
                public ProductInfo call() throws DataLoadException {
                    return ProductInfoUtils.loadProductInfo();
                }
            });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw ExceptionUtils.launderThrowable(cause);
        }
    }
}

