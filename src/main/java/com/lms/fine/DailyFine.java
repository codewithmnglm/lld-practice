package com.lms.fine;

import com.lms.constant.Constant;

public class DailyFine extends Fine{
    @Override
    public double calculateFine(double days) {
        return Constant.DAILY_FINE * days;
    }
}
