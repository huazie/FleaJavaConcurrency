package com.huazie.flea.concurrency.threadsafety.demo4;

import com.huazie.flea.concurrency.common.NotThreadSafe;
import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.frame.algorithm.factorization.Factor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p> 该Servlet在没有足够原子性保证的情况下对其最近计算结果进行缓存（非线程安全，不推荐使用） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends HttpServlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BigInteger i = CommonUtils.extractFromRequest(req);
        if (i.equals(lastNumber.get())) {
            CommonUtils.encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = Factor.factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            CommonUtils.encodeIntoResponse(resp, factors);
        }
    }
}