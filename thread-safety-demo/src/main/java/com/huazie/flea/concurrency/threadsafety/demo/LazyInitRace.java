package com.huazie.flea.concurrency.threadsafety.demo;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * <p> 延迟初始化中的竞态条件（非线程安全，不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}
