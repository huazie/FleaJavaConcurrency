package com.huazie.flea.concurrency.threadexecutor.demo10;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TravelQuote {

    private final TravelInfo travelInfo;

    public TravelQuote() {
        this.travelInfo = null;
    }

    public TravelQuote(TravelInfo travelInfo) {
        this.travelInfo = travelInfo;
    }
}
