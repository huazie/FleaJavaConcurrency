package com.huazie.flea.concurrency.taskcancel.demo1;

public class PrimeConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        PrimeConsumer consumer = new PrimeConsumer();
        consumer.consumePrimes();
    }
}