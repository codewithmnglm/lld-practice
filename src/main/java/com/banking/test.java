package com.banking;

import com.banking.account.Account;
import com.banking.account.SalaryAccount;
import com.banking.bank.Bank;
import com.banking.common.CommonBase;
import com.banking.constant.Constant;
import com.banking.customer.Customer;

public class test {

    public static void main(String[] args) {

        Customer customer = new Customer("Ramesh","Sinha","rameshsinha007@gmail.com", CommonBase.generateCustomerId());
        Bank bank= new Bank();
        Account salaryAccount = bank.openAccount(customer, Constant.SALARY_ACCOUNT);
        salaryAccount.deposit(2000.0);
        salaryAccount.withdraw(500.0);
        System.out.println(salaryAccount.getCustomer().getFirstName());

    }
}
