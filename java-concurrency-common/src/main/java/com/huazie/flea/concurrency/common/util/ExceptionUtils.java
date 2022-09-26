package com.huazie.flea.concurrency.common.util;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class ExceptionUtils {

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

}
