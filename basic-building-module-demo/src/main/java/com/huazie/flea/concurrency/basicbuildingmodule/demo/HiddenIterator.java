package com.huazie.flea.concurrency.basicbuildingmodule.demo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏的迭代器
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++)
            add(r.nextInt());
        // 隐藏在字符串连接中的迭代操作
        System.out.println("DEBUG: added ten elements to " + set);
    }
}

