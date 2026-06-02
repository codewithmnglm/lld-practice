package com.banking.account;

import com.banking.common.CommonBase;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DepositNotAllowedException;

public abstract class Account {

    private Customer customer;

    private String accountNo;
    protected double balance;
    protected String accountType;
    protected String accountStatus;


    public Account(Customer customer) {
        this.customer = customer;
        this.accountNo = CommonBase.generateAccountNumber();


    }

    public abstract void withdraw(double amount);

    public abstract void transferFunds(double amount, Account destinationAccount);


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void deposit(double amount) {

        if(getAccountStatus().equalsIgnoreCase(String.valueOf(AccountStatus.ACTIVE))) {

            if (amount <= 0) {
                throw new DepositNotAllowedException("Amount must be greater than 0");
            }
            this.balance += amount;

        }
        else throw new AccountClosedException("Account Already Closed");


    }


}
