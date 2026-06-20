package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.*;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalaryAccount extends TransferableAccount {


    public SalaryAccount(Customer customer) {
        super(customer);
        setAccountType(AccountType.SALARY_ACCOUNT);
    }

    @Override
    public void withdraw(double amount) {

        ensureAccountIsActive(this.accountStatus);
        validatePositiveAmount(amount);

        double totalWithdrawnToday = getTotalWithdrawnOn(LocalDate.now());

        if (totalWithdrawnToday + amount >
                Constant.DAILY_WITHDRAWL_LIMIT_SALARY_ACCOUNT) {
            throw new DailyWithdrawlLimitReachedException(
                    "Daily withdrawal limit reached"
            );
        }

        double availableBalance =
                getBalance() - Constant.MIN_SALARY_ACCOUNT_BAL;

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

}
