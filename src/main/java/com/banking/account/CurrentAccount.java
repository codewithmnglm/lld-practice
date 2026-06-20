package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.TransferFailException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class CurrentAccount extends Account implements Transferable {


    public CurrentAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.CURRENT_ACCOUNT);
    }

    @Override
    public void withdraw(double amount) {

        //No daily limit in currentAccount

        ensureAccountIsActive(this.accountStatus);
        validatePositiveAmount(amount);

        double availableBalance = getBalance();

        if (amount > availableBalance) {
            throw new WithdrawalNotAllowedException(
                    "Insufficient available balance"
            );
        }

        setBalance(getBalance() - amount);

        recordTransaction(new Transaction(
                amount,
                TransactionType.DEBIT,
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
        withdraw(amount);//throws immediately if insufficient funds — deposit never runs
        try {
            destinationAccount.deposit(amount);
        } catch (Exception e) {
            this.deposit(amount); // compensating transaction — undo the withdrawal
            throw new TransferFailException("Transfer failed, rolled back", e);
        }

    }
}
