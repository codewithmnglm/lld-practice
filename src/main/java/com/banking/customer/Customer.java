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
    private AccountType accountType;
    private AccountStatus accountStatus;
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }


}
