package com.huazie.flea.concurrency.threadexecutor.demo10;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class QuoteTask implements Callable<TravelQuote> {

    private final TravelCompany company;

    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return new TravelQuote();
    }

    public TravelQuote getTimeoutQuote(CancellationException e) {
        return new TravelQuote();
    }
}
