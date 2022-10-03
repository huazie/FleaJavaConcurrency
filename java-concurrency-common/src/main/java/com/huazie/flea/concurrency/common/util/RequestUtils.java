package com.huazie.flea.concurrency.common.util;

import com.huazie.flea.concurrency.common.pojo.Request;

import java.net.Socket;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequestUtils {

    public static void handleRequest(Socket connection) {
        // ...
    }

    public static void dispatchRequest(Request request) {
        // ...
    }

    public static boolean isShutdownRequest(Request request) {
        return false;
    }

    public static Request readRequest(Socket connection) {
        return new Request();
    }
}
