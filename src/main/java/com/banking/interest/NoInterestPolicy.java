package com.banking.interest;

import com.banking.account.Account;

public class NoInterestPolicy implements InterestPolicy {


    @Override
    public double calculateInterest(Account account, double days) {
        return 0.0;
    }
}
