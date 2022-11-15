package com.huazie.flea.concurrency.taskcancel.demo6;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import javax.annotation.concurrent.GuardedBy;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SocketUsingTask.class);

    @GuardedBy("this")
    private Socket socket;

    public SocketUsingTask(Socket socket) {
        this.socket = socket;
    }

    protected synchronized Socket getSocket() {
        return socket;
    }

    public synchronized void cancel() {
        LOGGER.debug("start custom cancel");
        try {
            if (socket != null) {
                socket.close();
                LOGGER.debug("socket close");
            }
        } catch (IOException e) {
            //
        }
        LOGGER.debug("end custom cancel");
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                LOGGER.debug("start cancel");
                SocketUsingTask.this.cancel();
                boolean result = super.cancel(mayInterruptIfRunning);
                LOGGER.debug("end cancel");
                LOGGER.debug("cancel result = {}", result);
                return result;
            }
        };
    }
}
