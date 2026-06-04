package com.banking.account;

import com.banking.customer.Customer;

public class AccountFactory {

    public static Account createAccount(Customer customer, AccountType accountType) {
        switch (accountType) {
            case SAVINGS_ACCOUNT: return new SavingAccount(customer);
            case SALARY_ACCOUNT: return new SalaryAccount(customer);
            default: throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }
}
