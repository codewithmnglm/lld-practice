package com.banking.account;


import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.ODLimitReachedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class OverDraftAccount extends Account implements Transferable {


    private double overDraftAmount;

    public OverDraftAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.OD_ACCOUNT);
        setOverDraftAmount(Constant.OD_LIMIT);
    }

    public double getOverDraftAmount() {
        return overDraftAmount;
    }

    private void setOverDraftAmount(double overDraftAmount) {
        this.overDraftAmount = overDraftAmount;
    }

    @Override
    public void withdraw(double amount) {
        ensureAccountIsActive(this.accountStatus);
        validatePositiveAmount(amount);

        double newBalance = getBalance() - amount;

        if (newBalance < 0) {
            double availableOverDraftAmount = getOverDraftAmount();
            if (availableOverDraftAmount < -newBalance) {
                throw new ODLimitReachedException(
                        "OD Limit Reached : Further Withdrawal Not allowed : Current Limit " + availableOverDraftAmount);
            }
            setOverDraftAmount(availableOverDraftAmount + newBalance);
        }

        setBalance(newBalance);
        recordTransaction(new Transaction(
                amount,
                TransactionType.DEBIT,
                LocalDateTime.now(),
                customer.getCustomerId()
        ));
  }

    @Override
    public void deposit(double amount) {
        ensureAccountIsActive(getAccountStatus());
        validatePositiveAmount(amount);

        double newBalance = getBalance() + amount;
        setBalance(newBalance);

        if (newBalance < 0) {
            setOverDraftAmount(getOverDraftAmount() + amount);
        } else if (getOverDraftAmount() < Constant.OD_LIMIT) {
            setOverDraftAmount(Constant.OD_LIMIT);
        }
        recordTransaction(new Transaction(
                amount,
                TransactionType.CREDIT,
                LocalDateTime.now(),
                customer.getCustomerId()
        ));
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

        withdraw(amount);
        destinationAccount.deposit(amount);

    }

    public double getUsedOverdraft() {
        return Math.max(0, -getBalance());
    }

    public double getAvailableOverdraft() {
        return getOverDraftAmount() - getUsedOverdraft();
    }
}
