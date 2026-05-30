package com.lms.fine;

public abstract class Fine {

    public abstract double calculateFine(double days);

    public double getTotalFine(double overdueDays) {
        if (overdueDays <= 0) return 0;
        return calculateFine(overdueDays);
    }
}
