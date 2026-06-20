package com.banking.interest;

import com.banking.account.Account;

public class CurrentAccountInterestPolicy implements InterestPolicy {


    @Override
    public double calculateInterest(Account account, double days) {

        return days;
    }
}
