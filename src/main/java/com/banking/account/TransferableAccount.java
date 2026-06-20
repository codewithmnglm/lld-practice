package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.TransferFailException;

public abstract class TransferableAccount extends Account implements Transferable {


    public TransferableAccount(Customer customer) {
        super(customer);
    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {
        if (destinationAccount == null) {
            throw new IllegalArgumentException("Destination account must not be null");
        }
        if (destinationAccount == this) {
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }
        if (destinationAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Destination account is closed");
        }
        withdraw(amount);//throws immediately if insufficient funds — deposit never runs
        try {
            destinationAccount.deposit(amount);
        } catch (Exception e) {
            this.deposit(amount); // compensating transaction — undo the withdrawal
            throw new TransferFailException("Transfer failed, rolled back", e);
        }

    }



}
