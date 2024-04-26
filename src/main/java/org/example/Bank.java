package org.example;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

public class Bank {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static ArrayList<Loan> loans = new ArrayList<>();

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
    protected ArrayList<Transaction> transactionList = new ArrayList<>();
    protected ArrayList<Loan> takenLoans = new ArrayList<>();

    public Account(String accountId, String accountOwner, double initialBalance,String password) {
        this.accountId = accountId;
        this.accountOwner = accountOwner;
        this.balance = initialBalance;
        this.password=password;
    }

    public String processTransaction(Account toAccount,double amount, String TransactionType) {
        if (this.getBalance() >= amount && this != toAccount) {
            if (amount > 0) {
                balance -= amount;
                toAccount.balance += amount;
                Bank.transactions.add(new Transaction(this, toAccount, amount, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), TransactionType));
            } else {
                return "Amount Value Must be More Than 0";
            }
        } else {
            return "Couldn't Complete the Transfer. Insufficient Balance";
        }
        return null;
    }

    public String processTransaction(double amount, String transactionType) {
        if (transactionType.equals("D")){
            if (amount > 0) {
                balance += amount;
                Bank.transactions.add(new Transaction(this, amount, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), transactionType));
            } else {
                return "Amount Value Must be More Than 0";
            }
        }
        else if (transactionType.equals("DL")){
            if (amount > 0) {
                balance += amount;
                Bank.transactions.add(new Transaction(this, amount, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), transactionType));
            } else {
                return "Amount Value Must be More Than 0";
            }
        } else if (transactionType.equals("PL")){
            if (amount <= balance) {
                balance -= amount;
                Bank.transactions.add(new Transaction(this, amount, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), transactionType));
            } else {
                return "Couldn't Pay Loan. Insufficient Balance";
            }
        } else{
            if (amount <= balance) {
                balance -= amount;
                Bank.transactions.add(new Transaction(this, amount, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), transactionType));
            } else {
                return "Couldn't Complete the Withdraw. Insufficient Balance";
            }
        }
        return null;
    }

    public String takeLoan(String loanId, double loanAmount) {
        for (Loan l : Bank.loans) {
            if (l.getLoanId().equals(loanId)) {
                l.setLoanAccount(this);
                l.setLoanAmount(loanAmount);
                String msg = l.disburseLoan();
                if (msg == null)
                    takenLoans.add(l);
                return msg;
            }
        }
        return "Loan Not Found"; // unreachable
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
    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }
    public ArrayList<Loan> getTakenLoans() {
        return takenLoans;
    }
}



