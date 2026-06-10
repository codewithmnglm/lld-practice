package com.banking.emi;

import java.time.LocalDate;

public class EmiSchedule {

    private int installmentNo;
    private LocalDate dueDate;
    private double emiAmount;
    private double principalComponent;
    private double interestComponent;
    private double remainingPrincipal;
    private EmiStatus status;

    public int getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(int installmentNo) {
        this.installmentNo = installmentNo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public double getPrincipalComponent() {
        return principalComponent;
    }

    public void setPrincipalComponent(double principalComponent) {
        this.principalComponent = principalComponent;
    }

    public double getInterestComponent() {
        return interestComponent;
    }

    public void setInterestComponent(double interestComponent) {
        this.interestComponent = interestComponent;
    }

    public double getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(double remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public EmiStatus getStatus() {
        return status;
    }

    public void setStatus(EmiStatus status) {
        this.status = status;
    }

    public EmiSchedule(int installmentNo, LocalDate dueDate, double emiAmount,
                   double principalComponent, double interestComponent,
                   double remainingPrincipal) {
    this.installmentNo = installmentNo;
    this.dueDate = dueDate;
    this.emiAmount = emiAmount;
    this.principalComponent = principalComponent;
    this.interestComponent = interestComponent;
    this.remainingPrincipal = remainingPrincipal;
    this.status = EmiStatus.PENDING;
}

}
