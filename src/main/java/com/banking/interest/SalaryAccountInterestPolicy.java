package com.banking.interest;

import com.banking.account.Account;

public class SalaryAccountInterestPolicy implements InterestPolicy {


    @Override
    public double calculateInterest(Account account, double days) {

        return days+12;
    }
}
