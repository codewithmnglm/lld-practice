package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.constant.Constant;

public class SavingAccount extends Account {


    public SavingAccount(Customer customer) {
        super(customer);
        deposit(Constant.MIN_SAVING_ACCOUNT_BAL);
        setAccountType(String.valueOf(AccountType.SAVING_ACCOUNT));
        setAccountStatus(String.valueOf(AccountStatus.ACTIVE));
    }


    @Override
    public void withdraw(double amount) {

        if(getAccountStatus().equalsIgnoreCase(String.valueOf(AccountStatus.ACTIVE))) {

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
        }
        else {
           throw new AccountClosedException("Account Already Closed : Cannot Withdraw Money");
        }


       // System.out.println("Current balance is: " + balance-amount);

    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {

    }
}
