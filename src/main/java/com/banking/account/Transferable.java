package com.banking.account;

public interface Transferable {

    void transferFunds(double amount, Account destinationAccount);
}
