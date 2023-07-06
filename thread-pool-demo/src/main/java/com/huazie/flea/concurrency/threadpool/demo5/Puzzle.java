package com.huazie.flea.concurrency.threadpool.demo5;

import java.util.Set;

/**
 * “搬箱子” 之类谜题的抽象类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
