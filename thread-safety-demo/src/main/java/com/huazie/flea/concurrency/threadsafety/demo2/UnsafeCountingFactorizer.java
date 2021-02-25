package com.huazie.flea.concurrency.threadsafety.demo2;

import com.huazie.frame.algorithm.factorization.Factor;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * <p> 在没有同步的情况下统计已经处理请求数量的Servlet </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnsafeCountingFactorizer extends HttpServlet {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(UnsafeCountingFactorizer.class);

    private long count = 0;

    public long getCount() {
        return count;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = Factor.factor(i);
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "待因数分解的大数：{}", i);
            LOGGER.debug1(obj, "因数分解的结果为：{}", Arrays.toString(factors));
        }
        ++count;
        encodeIntoResponse(resp, factors);
    }

    private BigInteger extractFromRequest(HttpServletRequest request) {
        String factor = request.getParameter("factor");
        return new BigInteger(factor);
    }

    private void encodeIntoResponse(HttpServletResponse response, BigInteger[] factors) throws IOException {
        response.setHeader("content-type", "text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("因数分解的结果为：" + Arrays.toString(factors));
    }
}
