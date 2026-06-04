package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.constant.Constant;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class SavingAccount extends Account {


    public SavingAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.SAVINGS_ACCOUNT);
        setAccountStatus(AccountStatus.ACTIVE);
        deposit(Constant.MIN_SAVING_ACCOUNT_BAL);
    }


    @Override
    public void withdraw(double amount) {

        if(getAccountStatus()==AccountStatus.ACTIVE) {

            double balance = getBalance();
            System.out.println("Current balance is: " + balance);
            if (amount <= 0) {
                throw new WithdrawalNotAllowedException("Amount must be greater than 0");
            }
            double remainingBalance = balance - Constant.MIN_SAVING_ACCOUNT_BAL;
            if (amount > remainingBalance) {
                throw new WithdrawalNotAllowedException("Insufficient Balance");
            }
            setBalance(balance - amount);
            transactions.add(new Transaction(amount, TransactionType.DEBIT, LocalDateTime.now(), customer.getCustomerId()));

        }
        else {
           throw new AccountClosedException("Account Already Closed : Cannot Withdraw Money");
        }



    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {
        withdraw(amount);
        destinationAccount.deposit(amount);



    }
}
