package org.example;

import java.time.Year;

public class Loan {
    private String loanId;
    private double loanAmount;
    private Account loanAccount;
    private int period;
    private int startYear;
    private double interestRate;

    public Loan(String loanId, double loanAmount, Account loanAccount, double intR, int p) {
        this.loanId = loanId;
        this.interestRate = intR;
        this.period = p;
        this.loanAmount = loanAmount;
        this.loanAccount = loanAccount;
    }

    public String getLoanId() {
        return loanId;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public boolean disburseLoan() {
        boolean isSuccess = loanAccount.processTransaction(loanAmount,"DL");//Change to disburse loan
        startYear = Year.now().getValue();
        return isSuccess;
    }

    public boolean makePayment() {
        if (Year.now().getValue() - startYear > period) {
            System.out.println("you exceeded loan payment date");
            return false;
        }
        return loanAccount.processTransaction(loanAmount + (loanAmount*(interestRate/100)),"PL");
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getPeriod() {
        return period;
    }

    public double getLoanAmount(){
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount){
        this.loanAmount=loanAmount;
    }
    public void setLoanAccount(Account loanAccount){
        this.loanAccount=loanAccount;
    }
}
