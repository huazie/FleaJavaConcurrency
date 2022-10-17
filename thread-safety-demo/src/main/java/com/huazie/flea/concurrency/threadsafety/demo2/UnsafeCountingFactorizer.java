package com.huazie.flea.concurrency.threadsafety.demo2;

import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.fleaframework.algorithm.factorization.Factor;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * <p> 在没有同步的情况下统计已经处理请求数量的Servlet（非线程安全，不推荐使用）</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends HttpServlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BigInteger i = CommonUtils.extractFromRequest(req);
        BigInteger[] factors = Factor.factor(i);
        ++count;
        CommonUtils.encodeIntoResponse(resp, factors);
    }

}
