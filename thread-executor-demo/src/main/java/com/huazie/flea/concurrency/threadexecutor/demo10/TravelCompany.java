package com.huazie.flea.concurrency.threadexecutor.demo10;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TravelCompany {

    public TravelQuote solicitQuote(TravelInfo travelInfo) {
        return new TravelQuote(travelInfo);
    }
}
