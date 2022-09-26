package com.huazie.flea.concurrency.objectcombination.demo7.notthreadsafe;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E e) {
        boolean isAbsent = !list.contains(e);
        if (isAbsent)
            list.add(e);
        return isAbsent;
    }
}

