package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DailyWithdrawlLimitReachedException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.constant.Constant;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SavingAccount extends Account implements Transferable {


    public SavingAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.SAVINGS_ACCOUNT);
        setAccountStatus(AccountStatus.ACTIVE);
        deposit(Constant.MIN_SAVING_ACCOUNT_BAL);
    }


    @Override
    public void withdraw(double amount) {

        double totalWithdrawnToday = transactions.stream()
                .filter(t -> t.getTxnDateTime().toLocalDate().equals(LocalDate.now()))
                .filter(t -> t.getType() == TransactionType.DEBIT)
                .mapToDouble(Transaction::getAmount)
                .sum();

        if (totalWithdrawnToday + amount > Constant.DAILY_WITHDRAWL_LIMIT_SAVINGS_ACCOUNT) {
            throw new DailyWithdrawlLimitReachedException("Withdrawal Limit Reached for Savings Account: Limit Per Day " + Constant.DAILY_WITHDRAWL_LIMIT_SAVINGS_ACCOUNT );
        }

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
