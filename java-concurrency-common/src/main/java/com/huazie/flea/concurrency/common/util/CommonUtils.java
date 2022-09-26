package com.huazie.flea.concurrency.common.util;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * <p> 通用工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CommonUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(CommonUtils.class);

    public static BigInteger extractFromRequest(HttpServletRequest request) {
        String factor = request.getParameter("factor");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "待因数分解的大数：{}", factor);
        }
        return new BigInteger(factor);
    }

    public static void encodeIntoResponse(HttpServletResponse response, BigInteger[] factors) throws IOException {
        response.setHeader("content-type", "text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "因数分解的结果为：{}", Arrays.toString(factors));
        }
        out.write("因数分解的结果为：" + Arrays.toString(factors));
    }

    public static void encodeError(HttpServletResponse response, String msg) throws IOException {
        response.setHeader("content-type", "text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("Error => " + msg);
    }
}
