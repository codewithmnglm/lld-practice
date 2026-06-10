package com.banking;

import com.banking.account.Account;
import com.banking.account.AccountType;
import com.banking.account.LoanAccount;
import com.banking.bank.Bank;
import com.banking.common.CommonBase;
import com.banking.customer.Customer;
import com.banking.emi.EmiSchedule;

import java.util.List;

public class test {

    public static void main(String[] args) {

        Customer customer = new Customer("Ramesh", "Sinha", "rameshsinha007@gmail.com", CommonBase.generateCustomerId());
        Customer customer1 = new Customer("Suresh", "Sinha", "sureshsinha007@gmail.com", CommonBase.generateCustomerId());

        Bank bank = new Bank();
        LoanAccount loanAccount = bank.openLoanAccount(customer, 500000.0, 8.5, 12);
        List<EmiSchedule> emi = loanAccount.getEmiSchedules();
        for (EmiSchedule emiSchedule : emi) {
            System.out.println("EMI Amount " + emiSchedule.getEmiAmount());
            System.out.println("Due Date " + emiSchedule.getDueDate());
            System.out.println("Interest Component " + emiSchedule.getInterestComponent());
            System.out.println("Principal Component " + emiSchedule.getPrincipalComponent());
            System.out.println("Remaining Principal " + emiSchedule.getRemainingPrincipal());
            System.out.println("Installment No " + emiSchedule.getInstallmentNo());
            System.out.println("EMI Status " + emiSchedule.getStatus());

            System.out.println("-------------------------");
        }
        loanAccount.payEmi(1);
        loanAccount.forecloseLoan();

        for (EmiSchedule emiSchedule : emi) {
            System.out.println("EMI Amount " + emiSchedule.getEmiAmount());
            System.out.println("Due Date " + emiSchedule.getDueDate());
            System.out.println("Interest Component " + emiSchedule.getInterestComponent());
            System.out.println("Principal Component " + emiSchedule.getPrincipalComponent());
            System.out.println("Remaining Principal " + emiSchedule.getRemainingPrincipal());
            System.out.println("Installment No " + emiSchedule.getInstallmentNo());
            System.out.println("EMI Status " + emiSchedule.getStatus());

            System.out.println("-------------------------");
        }

        /*Account salaryAccount = bank.openAccount(customer, AccountType.SALARY_ACCOUNT);
        Account savings = bank.openAccount(customer1, AccountType.SAVINGS_ACCOUNT);
        salaryAccount.deposit(2000.0);
       // salaryAccount.withdraw(500.0);

       System.out.println("Salary Account Balance Before " + salaryAccount.getBalance());
        System.out.println("Saving Account Balance Before " + savings.getBalance());

        salaryAccount.transferFunds(500,savings);


        System.out.println("Salary Account Balance After " + salaryAccount.getBalance());
        System.out.println("Saving Account Balance After " + savings.getBalance());*/

    }
}
