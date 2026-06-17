package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class CurrentAccount extends Account implements Transferable {


    public CurrentAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.CURRENT_ACCOUNT);
        setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public void withdraw(double amount) {

        //No daily limit in currentAccount

        if(getAccountStatus()==AccountStatus.ACTIVE) {
            double balance = getBalance();
            if (amount <= 0) {
                throw new WithdrawalNotAllowedException("Amount must be greater than 0");
            }
            if (amount > balance) {
                throw new WithdrawalNotAllowedException("Insufficient Balance");
            }
            setBalance(balance - amount);
            transactions.add(new Transaction(amount, TransactionType.DEBIT, LocalDateTime.now(), customer.getCustomerId()));

        }
        else  throw new AccountClosedException("Account Already Closed : Cannot Withdraw Money");

    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {
        withdraw(amount);
        destinationAccount.deposit(amount);

    }
}
