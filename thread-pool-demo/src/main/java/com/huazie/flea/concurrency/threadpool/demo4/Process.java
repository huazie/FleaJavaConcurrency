package com.huazie.flea.concurrency.threadpool.demo4;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class Process {

    /**
     * 串行循环
     *
     * @param elements 待处理的数据列表
     */
    public static void processSequentially(List<Element> elements) {
        for (Element e : elements)
            process(e);
    }

    /**
     * 并行循环
     *
     * @param exec     线程池对象
     * @param elements 待处理的数据列表
     */
    public static void processInParallel(Executor exec, List<Element> elements) {
        for (final Element e : elements)
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    process(e);
                }
            });
    }

    private static void process(Element e) {
        // 处理单个数据
    }

    /**
     * 串行递归
     *
     * @param nodes   树节点集合
     * @param results 结果集合
     * @param <T>     树中元素的类型
     */
    public static <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    /**
     * 并行递归
     *
     * @param exec    线程池对象
     * @param nodes   树节点集合
     * @param results 结果集合
     * @param <T>     树中元素的类型
     */
    public static <T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results) {
        for (final Node<T> n : nodes) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(exec, n.getChildren(), results);
        }
    }

    /**
     * 等待通过并行方式计算的结果
     *
     * @param nodes 树节点集合
     * @param <T> 树中元素的类型
     * @return 计算结果集合
     */
    public static <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedDeque<>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }
}
