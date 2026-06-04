package com.banking.bank;

import com.banking.account.*;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, Customer> customers = new HashMap<>();
    private Map<Customer, List<Account>> accounts = new HashMap<>();

    public Account openAccount(Customer customer,AccountType accountType) {
        Account newAccount = AccountFactory.createAccount(customer,accountType);
        accounts.computeIfAbsent(customer, k -> new ArrayList<>()).add(newAccount);
        customer.addAccount(newAccount);
        customers.put(customer.getCustomerId(),customer);
        newAccount.setAccountStatus(AccountStatus.ACTIVE);
        return newAccount;

    }

    public void closeAccount(Customer customer,Account account) {

        if(account.getAccountStatus().equals(String.valueOf(AccountStatus.ACTIVE))){
            account.setAccountStatus(AccountStatus.CLOSED);
        }
        else throw new AccountClosedException("Account Already Closed");

    }
}
