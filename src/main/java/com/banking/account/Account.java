package com.banking.account;

import com.banking.common.CommonBase;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DepositNotAllowedException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Account {

    protected Customer customer;
    protected double balance;
    protected AccountType accountType;
    protected AccountStatus accountStatus;
    protected List<Transaction> transactions = new ArrayList<>();
    private String accountNo;


    public Account(Customer customer) {
        this.customer = customer;
        this.accountNo = CommonBase.generateAccountNumber();
        this.accountStatus = AccountStatus.ACTIVE;

    }
    public abstract void withdraw(double amount);

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

    public void deposit(double amount) {

        ensureAccountIsActive(getAccountStatus());
        validatePositiveAmount(amount);

        setBalance(getBalance() + amount);
        recordTransaction(new Transaction(amount, TransactionType.CREDIT, LocalDateTime.now(), customer.getCustomerId()));

    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsBetweenDates(LocalDateTime from, LocalDateTime to) {

        return transactions.stream().filter(t -> t.getTxnDateTime().isAfter(from) && t.getTxnDateTime().isBefore(to)).collect(Collectors.toList());

    }

    public List<Transaction> getCreditTransactions() {

        return transactions.stream().filter(t -> t.getType() == TransactionType.CREDIT).collect(Collectors.toList());
    }

    public List<Transaction> getDebitTransactions() {

        return transactions.stream().filter(t -> t.getType() == TransactionType.DEBIT).collect(Collectors.toList());
    }

    protected double getTotalWithdrawnOn(LocalDate date) {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.DEBIT)
                .filter(t -> t.getTxnDateTime().toLocalDate().equals(date))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    protected void ensureAccountIsActive(AccountStatus accountStatus) {
        if (accountStatus != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active");
        }
    }

    protected void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }
    }



    protected void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }


    public void activate() {
        accountStatus = AccountStatus.ACTIVE;
    }

    public void close() {
        if (accountStatus == AccountStatus.CLOSED) {
            throw new AccountClosedException("Account is already closed");
        }

        accountStatus = AccountStatus.CLOSED;
    }

    public void freeze() {
        if (accountStatus != AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Only an active account can be frozen"
            );
        }

        accountStatus = AccountStatus.FROZEN;
    }





}
