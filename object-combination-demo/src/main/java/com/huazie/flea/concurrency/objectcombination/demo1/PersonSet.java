package com.huazie.flea.concurrency.objectcombination.demo1;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * <p> 通过封闭机制来确保线程安全 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

}
