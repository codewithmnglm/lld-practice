package com.banking.interest;

import com.banking.account.Account;

public class SavingsAccountInterestPolicy implements InterestPolicy {


    @Override
    public double calculateInterest(Account account, double days) {

        return days;
    }
}
