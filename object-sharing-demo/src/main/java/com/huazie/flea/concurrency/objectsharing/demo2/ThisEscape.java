package com.huazie.flea.concurrency.objectsharing.demo2;

import com.huazie.flea.concurrency.objectsharing.demo2.event.Event;
import com.huazie.flea.concurrency.objectsharing.demo2.event.EventSource;

/**
 * <p> 隐式地使this引用逸出（不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        // Java Lambda 表达式【https://www.runoob.com/java/java8-lambda-expressions.html】
        source.registerListener(e -> doSomething(e));
    }

    private void doSomething(Event e) {
        // 事件处理
    }
}
