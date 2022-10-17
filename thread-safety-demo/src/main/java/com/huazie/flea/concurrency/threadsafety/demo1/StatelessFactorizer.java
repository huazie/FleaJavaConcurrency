package com.huazie.flea.concurrency.threadsafety.demo1;

import com.huazie.flea.concurrency.common.util.CommonUtils;
import com.huazie.fleaframework.algorithm.factorization.Factor;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * <p> 无状态的Servlet </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class StatelessFactorizer extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BigInteger i = CommonUtils.extractFromRequest(req);
        BigInteger[] factors = Factor.factor(i);
        CommonUtils.encodeIntoResponse(resp, factors);
    }

}
