package com.huazie.flea.concurrency.objectsharing.demo2.event;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EventSource {

    void registerListener(EventListener listener);
}
