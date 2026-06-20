package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.TransferFailException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.interest.CurrentAccountInterestPolicy;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class CurrentAccount extends TransferableAccount {


    public CurrentAccount(Customer customer) {
        super(customer, new CurrentAccountInterestPolicy());
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


}
