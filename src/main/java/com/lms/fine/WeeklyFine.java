package com.lms.fine;

import com.lms.constant.Constant;

public class WeeklyFine extends Fine {

    @Override
    public double calculateFine(double days) {
        int noOfWeeks= (int)days/7;
        double noOfDays= days%7;
        return noOfWeeks * Constant.WEEKLY_FINE + noOfDays*Constant.DAILY_FINE;
    }
}
