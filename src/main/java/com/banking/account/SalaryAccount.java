package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DailyWithdrawlLimitReachedException;
import com.banking.exception.InsufficientAmountException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalaryAccount extends Account implements Transferable {


    public SalaryAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.SALARY_ACCOUNT);
        setAccountStatus(AccountStatus.ACTIVE);

    }

    @Override
    public void withdraw(double amount) {

        double totalWithdrawnToday = getTotalWithdrawnOn(LocalDate.now());

        if (totalWithdrawnToday + amount > Constant.DAILY_WITHDRAWL_LIMIT_SALARY_ACCOUNT) {
            throw new DailyWithdrawlLimitReachedException("Withdrawal Limit Reached for Salary Account: Limit Per Day " + Constant.DAILY_WITHDRAWL_LIMIT_SALARY_ACCOUNT );
        }

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
