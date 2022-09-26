package com.huazie.flea.concurrency.objectcombination.demo7.threadsafe;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E e) {
        synchronized (list) {
            boolean isAbsent = !list.contains(e);
            if (isAbsent)
                list.add(e);
            return isAbsent;
        }
    }
}


