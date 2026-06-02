package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.WithdrawalNotAllowedException;

public class SalaryAccount extends Account {


    public SalaryAccount(Customer customer) {
        super(customer);
        setAccountType(String.valueOf(AccountType.SALARY_ACCOUNT));
        setAccountStatus(String.valueOf(AccountStatus.ACTIVE));

    }

    @Override
    public void withdraw(double amount) {

        if(getAccountStatus().equalsIgnoreCase(String.valueOf(AccountStatus.ACTIVE))) {


            double balance = getBalance();
            if (amount <= 0) {
                throw new WithdrawalNotAllowedException("Amount must be greater than 0");
            }
            if (amount > balance) {
                throw new WithdrawalNotAllowedException("Insufficient Balance");
            }
            setBalance(balance - amount);
        }
       else  throw new AccountClosedException("Account Already Closed : Cannot Withdraw Money");


    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {

    }
}
