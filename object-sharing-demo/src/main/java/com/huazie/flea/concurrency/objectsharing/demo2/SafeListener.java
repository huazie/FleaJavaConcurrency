package com.huazie.flea.concurrency.objectsharing.demo2;

import com.huazie.flea.concurrency.objectsharing.demo2.event.Event;
import com.huazie.flea.concurrency.objectsharing.demo2.event.EventListener;
import com.huazie.flea.concurrency.objectsharing.demo2.event.EventSource;

/**
 * <p> 使用工厂方法来防止this引用在构造过程中逸出 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener() {
        // Java Lambda 表达式【https://www.runoob.com/java/java8-lambda-expressions.html】
        listener = e -> doSomething(e);
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    private void doSomething(Event e) {
        // 事件处理
    }
}
