package com.banking.interest;

import com.banking.account.Account;

public interface InterestPolicy {

    public double calculateInterest(Account account, double days);
}
