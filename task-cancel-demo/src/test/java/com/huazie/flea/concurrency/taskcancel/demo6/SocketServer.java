package com.huazie.flea.concurrency.taskcancel.demo6;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.huazie.flea.concurrency.common.util.ExceptionUtils.launderThrowable;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SocketServer {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SocketServer.class);

    private static CancellingExecutor executor = new CancellingExecutor();

    public static void main(String[] args) throws IOException {
        // 创建服务端socket
        ServerSocket serverSocket = new ServerSocket(8888);

        //循环监听等待客户端的连接
        while (true) {
            // 监听客户端
            LOGGER.debug("start serverSocket.accept()");
            // 创建客户端socket
            Socket socket = serverSocket.accept();
            LOGGER.debug("end serverSocket.accept()");

            PrimeSumTask primeSumTask = new PrimeSumTask(socket);
            Future<BigInteger> future = executor.submit(primeSumTask);

            try {
                BigInteger result = future.get(5, TimeUnit.SECONDS);
                LOGGER.debug("result = {}", result);
            } catch (ExecutionException e) {
                // 如果任务中抛出了异常，那么重新抛出该异常
                throw launderThrowable(e.getCause());
            } catch (TimeoutException e) {
                // 任务超时，最终 finally 也会将任务取消
                LOGGER.error("TimeoutException");
            } catch (InterruptedException e) {
                // 中断异常
            } finally {
                // 如果任务已经结束，那么执行取消操作也不会带来任何影响
                // 如果任务正在运行，那么将被中断
                LOGGER.debug( "task is done : {}", future.isDone());
                LOGGER.debug( "future cancel start");
                future.cancel(true);
                LOGGER.debug( "future cancel end");
                LOGGER.debug( "task is cancelled : {}", future.isCancelled());
            }

        }
    }
}
