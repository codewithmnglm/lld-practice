package com.banking.bank;

import com.banking.account.Account;
import com.banking.account.AccountStatus;
import com.banking.account.SalaryAccount;
import com.banking.account.SavingAccount;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, Customer> customers = new HashMap<>();
    private Map<Customer, List<Account>> accounts = new HashMap<>();

    public Account openAccount(Customer customer,String accountType) {
        Account newAccount = null;
        switch(accountType){
            case "SavingAccount": newAccount = new SavingAccount(customer);
            break;
            case "SalaryAccount": newAccount = new SalaryAccount(customer);
            break;
        }
        accounts.computeIfAbsent(customer, k -> new ArrayList<>()).add(newAccount);
        customer.addAccount(newAccount);
        customers.put(customer.getCustomerId(),customer);
        return newAccount;

    }

    public void closeAccount(Customer customer,Account account) {

        if(account.getAccountStatus().equals(String.valueOf(AccountStatus.ACTIVE))){
            account.setAccountStatus(String.valueOf(AccountStatus.CLOSED));
        }
        else throw new AccountClosedException("Account Already Closed");

    }
}
