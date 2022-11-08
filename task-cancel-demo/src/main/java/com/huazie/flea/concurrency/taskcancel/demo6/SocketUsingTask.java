package com.huazie.flea.concurrency.taskcancel.demo6;

import javax.annotation.concurrent.GuardedBy;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    @GuardedBy("this")
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    public synchronized void cancel() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            //
        }
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                SocketUsingTask.this.cancel();
                return super.cancel(mayInterruptIfRunning);
            }
        };
    }
}
