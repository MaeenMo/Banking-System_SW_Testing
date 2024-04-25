package org.example;

import java.util.Random;


public class Transaction {
    private String transactionId;
    private String transactionType;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String transactionDate;

    public Transaction(Account fromAccount, Account toAccount, double amount, String transactionDate, String transactionType) {
        String newTransactionID = String.valueOf(new Random().nextInt(1000) + 1);
        boolean found = true;
        while (found) {
            for (int i=0 ;i<Bank.transactions.size();i++) {
                if (Bank.transactions.get(i).equals(newTransactionID)) {
                    newTransactionID = String.valueOf(new Random().nextInt(1000) + 1);
                    i=0;
                }
            }
            found = false;
        }
        this.transactionId = newTransactionID;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        fromAccount.transactionList.add(this);
        toAccount.transactionList.add(this);
    }

    public Transaction(Account fromAccount, double amount, String transactionDate, String transactionType) {
        String newTransactionID = String.valueOf(new Random().nextInt(1000) + 1);
        boolean found = true;
        while (found) {
            for (int i=0 ;i<Bank.transactions.size();i++) {
                if (Bank.transactions.get(i).equals(newTransactionID)) {
                    newTransactionID = String.valueOf(new Random().nextInt(1000) + 1);
                    i=0;
                }
            }
            found = false;
        }
        this.transactionId = newTransactionID;
        this.fromAccount = fromAccount;
        this.toAccount = null;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        fromAccount.transactionList.add(this);
    }
    public String getTransactionDetails(String transactionType) {
        if (transactionType == "T")
            return "Transaction ID: " + transactionId + ", Date: " + transactionDate +
                    ", From: " + fromAccount.getAccountId() + ", To: " + toAccount.getAccountId() + ", Amount: " + amount;
        else
            return "Transaction ID: " + transactionId + ", Date: " + transactionDate +
                    ", From: " + fromAccount.getAccountId() + ", Amount: " + amount;
    }

    public String getTransactionId() {
        return this.transactionId;
    }
    public String getTransactionDate() {
        return this.transactionDate;
    }
    public String getFromAccountId() {
        return this.fromAccount.getAccountId();
    }
    public String getToAccountId() {
        return this.toAccount.getAccountId();
    }
    public double getAmount() {
        return this.amount;
    }
    public String getTransactionType() {
        return this.transactionType;
    }
}
