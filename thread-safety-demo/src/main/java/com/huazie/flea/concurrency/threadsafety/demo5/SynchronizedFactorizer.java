package com.huazie.flea.concurrency.threadsafety.demo5;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.frame.algorithm.factorization.Factor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * <p> 该 Servlet 可以正确地缓存最新的计算结果，但并发性却非常糟糕（不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class SynchronizedFactorizer extends HttpServlet {

    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BigInteger i = CommonUtils.extractFromRequest(req);
        if (i.equals(lastNumber)) {
            CommonUtils.encodeIntoResponse(resp, lastFactors);
        } else {
            BigInteger[] factors = Factor.factor(i);
            lastNumber = i;
            lastFactors = factors;
            CommonUtils.encodeIntoResponse(resp, factors);
        }
    }
}