package com.huazie.flea.concurrency.objectsharing.demo3;

import javax.annotation.concurrent.Immutable;
import java.util.HashSet;
import java.util.Set;

/**
 * <p> 在可变对象基础上构建的不可变类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Tom");
        stooges.add("Jerry");
        stooges.add("Huazie");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
