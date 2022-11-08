package com.huazie.flea.concurrency.taskcancel.demo2;

public class PrimeConsumerNewTest {

    public static void main(String[] args) throws InterruptedException {
        PrimeConsumer consumer = new PrimeConsumer();
        consumer.consumePrimes();
    }
}