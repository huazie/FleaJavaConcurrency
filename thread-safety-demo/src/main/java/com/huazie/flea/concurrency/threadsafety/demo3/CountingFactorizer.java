package com.huazie.flea.concurrency.threadsafety.demo3;

import javax.annotation.concurrent.ThreadSafe;
import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.frame.algorithm.factorization.Factor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 使用 AtomicLong 类型的变量来统计已处理请求的数量 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class CountingFactorizer extends HttpServlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BigInteger i = CommonUtils.extractFromRequest(req);
        BigInteger[] factors = Factor.factor(i);
        count.incrementAndGet();
        CommonUtils.encodeIntoResponse(resp, factors);
    }
}
