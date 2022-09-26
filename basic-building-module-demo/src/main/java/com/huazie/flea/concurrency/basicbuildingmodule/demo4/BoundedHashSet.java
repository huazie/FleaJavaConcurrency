package com.huazie.flea.concurrency.basicbuildingmodule.demo4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 有界阻塞容器Set
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class BoundedHashSet<T> {
    private final Set<T> set;

    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(t);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object t) {
        boolean wasRemoved = set.remove(t);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }
}

