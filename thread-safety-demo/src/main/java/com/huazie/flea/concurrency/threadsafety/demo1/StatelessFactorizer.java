package com.huazie.flea.concurrency.threadsafety.demo1;

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
 * <p> 无状态的Servlet </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StatelessFactorizer extends HttpServlet {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(StatelessFactorizer.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = Factor.factor(i);
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "待因式分解的大数：{}", i);
            LOGGER.debug1(obj, "因式分解的结果为：{}", Arrays.toString(factors));
        }
        encodeIntoResponse(resp, factors);
    }

    private BigInteger extractFromRequest(HttpServletRequest request) {
        String factor = request.getParameter("factor");
        return new BigInteger(factor);
    }

    private void encodeIntoResponse(HttpServletResponse response, BigInteger[] factors) throws IOException {
        response.setHeader("content-type", "text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("因式分解的结果为：" + Arrays.toString(factors));
    }
}
