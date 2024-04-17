package org.example;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class Bank {
    public Map<String, Account> accounts;
    public Map<String, Transaction> transactions;

    public Bank() {
        accounts = new HashMap<>();
        transactions = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addTransaction(Transaction transaction, Account fromAccount, Account toAccount) {
        transaction.processTransfer();
        transactions.put(transaction.getTransactionId(), transaction);
    }

    public Transaction getTransaction(String transactionID) {
        return transactions.get(transactionID);
    }
}
class Account {
    private String accountId;
    private String accountOwner;
    private double balance;
    private String password;
    protected ArrayList<Loan> takenLoans = new ArrayList<>();

    public Account(String accountId, String accountOwner, double initialBalance,String password) {
        this.accountId = accountId;
        this.accountOwner = accountOwner;
        this.balance = initialBalance;
        this.password=password;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void takeLoan(String loanId, double loanAmount, double intR, int p) {
        Loan l = new Loan(loanId, loanAmount, this, intR, p);
        takenLoans.add(l);
        l.disburseLoan();
    }

    public boolean payLoan(String loanId) {
        int found = -1;
        for (Loan i:takenLoans){
            if (i.getLoanId().equals(loanId)){
                found = takenLoans.indexOf(i);
                break;
            }
        }
        if (found == -1)
            return false;
        else{
            if (takenLoans.get(found).makePayment()){
                takenLoans.remove(found);
                return true;
            } else
                return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setBalance(double balance) {
        this.balance=balance;
    }

    public void setAccountId(String accountId) {
        this.accountId=accountId;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner=accountOwner;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
class Transaction {
    private String transactionId;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String transactionDate;

    public Transaction(String transactionId, Account fromAccount, Account toAccount, double amount, String transactionDate) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public void processTransfer() {
        if (fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transaction successful: " + amount + " transferred from " + fromAccount.getAccountId() + " to " + toAccount.getAccountId());
        } else {
            System.out.println("Transaction failed: Insufficient funds.");
        }
    }

    public String getTransactionDetails() {
        return "Transaction ID: " + transactionId + ", Date: " + transactionDate +
                ", From: " + fromAccount.getAccountId() + ", To: " + toAccount.getAccountId() + ", Amount: " + amount;
    }

    public String getTransactionId() {
        return this.transactionId;
    }
    public String getTransactionDate() {
        return this.transactionDate;
    }
    public String getTransactionFromAccountId() {
        return this.fromAccount.getAccountId();
    }
    public String getTransactionToAccountId() {
        return this.toAccount.getAccountId();
    }
    public double getTransactionAmount() {
        return this.amount;
    }
}

class Loan {
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

    public void disburseLoan() {
        loanAccount.deposit(loanAmount);
        startYear = Year.now().getValue();
        System.out.println("Loan disbursed: " + loanAmount + " to account: " + loanAccount.getAccountId());
    }

    public boolean makePayment() {
        if (Year.now().getValue() - startYear > period) {
            System.out.println("you exceeded loan payment date");
            return false;
        }
        loanAccount.withdraw(loanAmount + (loanAmount*(interestRate/100)));
        System.out.println("Loan repayment: " + loanAmount + " from account: " + loanAccount.getAccountId());
        return true;
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
}



