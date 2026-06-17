package com.banking.exception;

public class DailyWithdrawlLimitReachedException extends RuntimeException {


    public DailyWithdrawlLimitReachedException(String message) {
        super(message);
    }
}
