package com.banking.customer;

import com.banking.account.Account;
import com.banking.account.AccountStatus;
import com.banking.account.AccountType;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String customerId;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, String customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }


}
