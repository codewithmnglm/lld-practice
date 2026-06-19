package com.banking.account;

import com.banking.customer.Customer;
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
        ensureAccountIsActive(this.accountStatus);
        validatePositiveAmount(amount);

        double withdrawnToday = getTotalWithdrawnOn(LocalDate.now());

        if (withdrawnToday + amount >
                Constant.DAILY_WITHDRAWL_LIMIT_SAVINGS_ACCOUNT) {
            throw new DailyWithdrawlLimitReachedException(
                    "Daily withdrawal limit reached"
            );
        }

        double availableBalance =
                getBalance() - Constant.MIN_SAVING_ACCOUNT_BAL;

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
        withdraw(amount);
        destinationAccount.deposit(amount);

    }


}

