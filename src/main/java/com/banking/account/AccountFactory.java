package com.banking.account;

import com.banking.customer.Customer;

public class AccountFactory {

    public static Account createAccount(Customer customer, AccountType accountType) {
        switch (accountType) {
            case SAVINGS_ACCOUNT: return new SavingAccount(customer);
            case SALARY_ACCOUNT: return new SalaryAccount(customer);
            case LOAN_ACCOUNT:
                throw new IllegalArgumentException("Use createLoanAccount for loan accounts");
            default: throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }

    public static LoanAccount createLoanAccount(Customer customer, double principalAmount,
                                                double interestRate, int tenureInMonths) {
        return new LoanAccount(customer, principalAmount, interestRate, tenureInMonths);
    }
}
