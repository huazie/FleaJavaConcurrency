package com.huazie.flea.concurrency.taskshutdown.demo5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
