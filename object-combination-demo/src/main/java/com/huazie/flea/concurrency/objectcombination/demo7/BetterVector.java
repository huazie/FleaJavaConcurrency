package com.huazie.flea.concurrency.objectcombination.demo7;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E e) {
        boolean isAbsent = !contains(e);
        if (isAbsent)
            add(e);
        return isAbsent;
    }
}

