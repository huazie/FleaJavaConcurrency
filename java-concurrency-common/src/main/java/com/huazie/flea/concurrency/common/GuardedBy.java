package com.huazie.flea.concurrency.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p> GuardedBy(lock) 表示只有在持有某个特定的锁时才能访问这个域或方法 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface GuardedBy {

    /**
     * <p> 在访问被标注的域或方法时需要持有的锁 </p>
     *
     * @return 在访问被标注的域或方法时需要持有的锁
     */
    String value() default "";
}
