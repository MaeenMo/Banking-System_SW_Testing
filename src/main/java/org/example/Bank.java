package org.example;
import java.time.Year;
import java.util.*;

public class Bank {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public static Account getAccount(String accountId) {
        for (Account a:accounts){
            if (a.getAccountId().equals(accountId)){
                return a;
            }
        }
        return null;
    }

    public Transaction getTransaction(String transactionID) {
        for (Transaction t:transactions){
            if (t.getTransactionId().equals(transactionID)){
                return t;
            }
        }
        return null;
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

    public boolean processTransaction(Account toAccount,double amount, String transactionDate, String TransactionType) {
        if (this.getBalance() >= amount) {
            if (amount > 0) {
                Transaction t = new Transaction(this, toAccount, amount, transactionDate, TransactionType);
                balance -= amount;
                toAccount.balance += amount;
                Bank.transactions.add(t);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean processTransaction(double amount, String transactionDate, String TransactionType) {
        if (TransactionType.equals("D")){
            if (amount > 0) {
                balance += amount;
                Bank.transactions.add(new Transaction(this, amount, transactionDate, TransactionType));
            } else {
                return false;
            }
        }else{
            if (amount <= balance) {
                balance -= amount;
                Bank.transactions.add(new Transaction(this, amount, transactionDate, TransactionType));
            } else {
                return false;
            }
        }
        return true;
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
    public String getPassword(){
        return password;
    }
}
class Transaction {
    private String transactionId;
    private String TransactionType;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String transactionDate;

    public Transaction(Account fromAccount, Account toAccount, double amount, String transactionDate, String TransactionType) {
        this.transactionId = TransactionType + new Random().ints(10);
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.TransactionType = TransactionType;
    }

    public Transaction(Account fromAccount, double amount, String transactionDate, String TransactionType) {
        this.transactionId = TransactionType + new Random().ints(10);
        this.fromAccount = fromAccount;
        this.toAccount = null;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.TransactionType = TransactionType;
    }
//
//    public void processTransfer() {
//        if (fromAccount.getBalance() >= amount) {
//            fromAccount.withdraw(amount);
//            toAccount.deposit(amount);
//            System.out.println("Transaction successful: " + amount + " transferred from " + fromAccount.getAccountId() + " to " + toAccount.getAccountId());
//        } else {
//            System.out.println("Transaction failed: Insufficient funds.");
//        }
//    }
//
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
        loanAccount.processTransaction(loanAmount, "17/04/2024", "D");
        startYear = Year.now().getValue();
        System.out.println("Loan disbursed: " + loanAmount + " to account: " + loanAccount.getAccountId());
    }

    public boolean makePayment() {
        if (Year.now().getValue() - startYear > period) {
            System.out.println("you exceeded loan payment date");
            return false;
        }
        loanAccount.processTransaction(loanAmount + (loanAmount*(interestRate/100)),
                "17/04/2024", "W");
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



