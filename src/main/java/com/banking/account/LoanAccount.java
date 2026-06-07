package com.banking.account;

import com.banking.customer.Customer;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DepositNotAllowedException;
import com.banking.exception.InvalidTenureException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDateTime;

public class LoanAccount extends Account {

    private double principalAmount;
    private double interestRate;
    private int tenureInMonths;

    public LoanAccount(Customer customer, double principalAmount, double interestRate, int tenureInMonths) {
        super(customer);
        setAccountType(AccountType.LOAN_ACCOUNT);
        setAccountStatus(AccountStatus.ACTIVE);
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.tenureInMonths = tenureInMonths;

    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    private void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTenureInMonths() {

        return tenureInMonths;
    }

    public void setTenureInMonths(int tenureInMonths) {

        if (tenureInMonths < 3) {
            throw new InvalidTenureException("Tenure must be at least 3 months");
        }
        this.tenureInMonths = tenureInMonths;
    }

    public void withdraw(double amount) {
        throw new WithdrawalNotAllowedException("Can't Withdraw From Loan Account");
    }

    @Override
    public void deposit(double amount) {

        if (getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Loan account is already closed");
        }
        if (amount <= 0) {
            throw new DepositNotAllowedException("Amount must be greater than 0");
        }
        if (amount > principalAmount) {
            throw new DepositNotAllowedException("Amount cannot be greater than outstanding loan amount");
        }

        principalAmount -= amount;
        transactions.add(new Transaction(amount, TransactionType.CREDIT, LocalDateTime.now(), customer.getCustomerId()));

        if (principalAmount == 0) {
            setAccountStatus(AccountStatus.CLOSED);
            tenureInMonths = 0;
            System.out.println("Loan account has been closed");
        }
    }

    public double calculateEMI() {
        double monthlyRate = interestRate / (12 * 100);

        int months = tenureInMonths * 12;

        double emi = principalAmount *
                monthlyRate *
                Math.pow(1 + monthlyRate, months)
                / (Math.pow(1 + monthlyRate, months) - 1);

        return emi;

    }

}
