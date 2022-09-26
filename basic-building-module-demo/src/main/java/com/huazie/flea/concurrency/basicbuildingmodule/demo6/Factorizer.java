package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.fleaframework.algorithm.factorization.Factor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 因式分解 Servlet 添加结果缓存
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Factorizer extends HttpServlet {

    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        public BigInteger[] compute(BigInteger arg) {
            return Factor.factor(arg);
        }
    };

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            BigInteger i = CommonUtils.extractFromRequest(req);
            CommonUtils.encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            CommonUtils.encodeError(resp, "factorization interrupted");
        }
    }
}
