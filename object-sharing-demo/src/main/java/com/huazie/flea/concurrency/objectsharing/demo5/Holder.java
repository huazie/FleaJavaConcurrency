package com.huazie.flea.concurrency.objectsharing.demo5;

/**
 * <p> 由于未被正确发布，因此这个类在调用 assertSanity时将抛出 AssertionError </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }
}
