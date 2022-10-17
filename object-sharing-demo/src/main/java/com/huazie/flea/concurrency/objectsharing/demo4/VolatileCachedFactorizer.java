package com.huazie.flea.concurrency.objectsharing.demo4;

import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.fleaframework.algorithm.factorization.Factor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * <p> 使用执行不可变容器对象的 volatile 类型引用以缓存最新的结果 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class VolatileCachedFactorizer extends HttpServlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BigInteger i = CommonUtils.extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (null == factors) {
            factors = Factor.factor(i);
            cache = new OneValueCache(i, factors);
        }
        CommonUtils.encodeIntoResponse(resp, factors);
    }
}
