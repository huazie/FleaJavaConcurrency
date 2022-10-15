package com.huazie.flea.concurrency.threadexecutor.demo9;

import java.util.concurrent.Callable;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FetchAdTask implements Callable<Ad> {

    @Override
    public Ad call() throws Exception {
        return new Ad();
    }
}
