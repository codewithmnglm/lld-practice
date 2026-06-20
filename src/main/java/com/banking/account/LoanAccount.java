package com.banking.account;

import com.banking.constant.Constant;
import com.banking.customer.Customer;
import com.banking.emi.EmiSchedule;
import com.banking.emi.EmiStatus;
import com.banking.exception.AccountClosedException;
import com.banking.exception.DepositNotAllowedException;
import com.banking.exception.InvalidTenureException;
import com.banking.exception.WithdrawalNotAllowedException;
import com.banking.interest.NoInterestPolicy;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoanAccount extends Account {

    private double principalAmount;
    private double interestRate;
    private int tenureInMonths;
    private List<EmiSchedule> emiSchedules;

    public LoanAccount(Customer customer, double principalAmount, double interestRate, int tenureInMonths) {
        super(customer, new NoInterestPolicy());
        setAccountType(AccountType.LOAN_ACCOUNT);
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.tenureInMonths = tenureInMonths;
        generateEmiSchedules();

    }

    public static double calculateMonthlyInstalments(double principal,
                                                     double annualInterestRate,
                                                     int tenureInMonths) {

        double monthlyRate = annualInterestRate / (12 * 100);

        double emi = principal
                * monthlyRate
                * Math.pow(1 + monthlyRate, tenureInMonths)
                / (Math.pow(1 + monthlyRate, tenureInMonths) - 1);

        return emi;
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

        ensureAccountIsActive(getAccountStatus());
        validatePositiveAmount(amount);

        principalAmount -= amount;
        transactions.add(new Transaction(amount, TransactionType.CREDIT, LocalDateTime.now(), customer.getCustomerId()));

        if (principalAmount == 0) {
            setAccountStatus(AccountStatus.CLOSED);
            tenureInMonths = 0;
            System.out.println("Loan account has been closed");
        }
    }

    private List<EmiSchedule> generateEmiSchedules() {
        List<EmiSchedule> emiSchedules = new ArrayList<>();

        double monthlyRate = interestRate / (12 * 100);
        double outstandingPrincipal = principalAmount;
        LocalDate loanDisbursedDate = LocalDate.now();
        LocalDate twentiethDay = loanDisbursedDate.withDayOfMonth(20);

        double emi = calculateMonthlyInstalments(principalAmount, interestRate, tenureInMonths);

        // first due date rule — before 20th → next month's 5th, else → month-after-next's 5th
        LocalDate firstDueDate = loanDisbursedDate.isBefore(twentiethDay)
                ? loanDisbursedDate.plusMonths(1).withDayOfMonth(5)
                : loanDisbursedDate.plusMonths(2).withDayOfMonth(5);

        for (int i = 1; i <= tenureInMonths; i++) {
            double interestComponent = outstandingPrincipal * monthlyRate;
            double principalComponent = emi - interestComponent;
            outstandingPrincipal -= principalComponent;

            LocalDate dueDate = firstDueDate.plusMonths(i - 1);
            emiSchedules.add(new EmiSchedule(i, dueDate, emi,
                    principalComponent, interestComponent, Math.max(0, outstandingPrincipal)));
        }

        this.emiSchedules = emiSchedules;
        return emiSchedules;
    }

    public List<EmiSchedule> getEmiSchedules() {
        return emiSchedules;
    }

    public void markOverdueInstallments() {

        List<EmiSchedule> emiSchedules = getEmiSchedules();
        emiSchedules.stream().
                filter(e -> e.getDueDate().isBefore(LocalDate.now()) && e.getStatus() == EmiStatus.PENDING).
                forEach(e -> e.setStatus(EmiStatus.OVERDUE));

    }

    public void payEmi(int installmentNo) {
        List<EmiSchedule> emiSchedules = getEmiSchedules();
        EmiSchedule installment = emiSchedules.stream().
                filter(e -> e.getInstallmentNo() == installmentNo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Installment not found: " + installmentNo));

        if (installment.getStatus() == EmiStatus.PAID) {
            throw new IllegalStateException("Installment " + installmentNo + " is already paid");
        }
        deposit(installment.getEmiAmount());
        installment.setStatus(EmiStatus.PAID);
        boolean allPaid = emiSchedules.stream().allMatch(e -> e.getStatus() == EmiStatus.PAID);
        if (allPaid) {
            setAccountStatus(AccountStatus.CLOSED);
            System.out.println("All EMIs paid. Loan account closed.");
        }


    }

    public void forecloseLoan() {

        double forecloseLoanAmount = calculateForeclosureAmount();
        System.out.println("Total Foreclosure Amount: " + forecloseLoanAmount);
        deposit(forecloseLoanAmount);
        getEmiSchedules().stream()
                .filter(e -> e.getStatus() == EmiStatus.PENDING
                        || e.getStatus() == EmiStatus.OVERDUE)
                .forEach(e -> e.setStatus(EmiStatus.CANCELLED));
        setAccountStatus(AccountStatus.CLOSED);
        System.out.println("Loan account closed.");
    }


    private double calculateForeclosureAmount() {

        double outstandingPrincipal =
                getOutstandingPrincipalAmount();

        double foreclosurePenalty = getForeclosurePenalty(outstandingPrincipal);
        System.out.println("Foreclosure Penalty: " + foreclosurePenalty);
        return outstandingPrincipal + foreclosurePenalty;
    }

    private double getForeclosurePenalty(double outstandingPrincipal) {

        return outstandingPrincipal *
                (Constant.PREPAYMENT_PENALTY / 100.0);


    }

    private double getOutstandingPrincipalAmount() {
        double paidPrincipal = emiSchedules.stream()
                .filter(e -> e.getStatus() == EmiStatus.PAID)
                .mapToDouble(EmiSchedule::getPrincipalComponent)
                .sum();
        return getPrincipalAmount() - paidPrincipal;

    }


}
