package com.banking.bank;

import com.banking.account.*;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.AccountException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, Customer> customers = new HashMap<>();
    private Map<Customer, List<Account>> accounts = new HashMap<>();

    public Account openAccount(Customer customer,AccountType accountType) {
        Account newAccount = AccountFactory.createAccount(customer,accountType);
        addAccount(customer, newAccount);
        return newAccount;

    }

    public LoanAccount openLoanAccount(Customer customer, double principalAmount,
                                       double interestRate, int tenureInMonths) {
        LoanAccount loanAccount = AccountFactory.createLoanAccount(
                customer,
                principalAmount,
                interestRate,
                tenureInMonths
        );
        addAccount(customer, loanAccount);
        return loanAccount;
    }

    private void addAccount(Customer customer, Account newAccount) {
        accounts.computeIfAbsent(customer, k -> new ArrayList<>()).add(newAccount);
        customer.addAccount(newAccount);
        customers.put(customer.getCustomerId(),customer);
        newAccount.setAccountStatus(AccountStatus.ACTIVE);
    }

    public void closeAccount(Customer customer,Account account) {

        if (!customer.getAccounts().contains(account)) {
            throw new AccountException("Account does not belong to this customer");
        }
        account.close();
    }
}
