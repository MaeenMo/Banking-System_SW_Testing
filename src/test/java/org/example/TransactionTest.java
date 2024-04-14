package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    public static Account a1=new Account("1","Joe Smith",4000,"12345"), a2=new Account("2","Adams Ben",10000,"678910");;
    public static Transaction t1= new Transaction("t466178369xr16", a2, a1, 3000,"14/04/2024"), t2= new Transaction("t43161246rs796", a2, a1, 11000,"15/04/2024");

    @BeforeAll
    @DisplayName("Setting Up Objects To Start Transaction Class Test")
    public static void setUp() {
        System.out.println("\n---------------------------------");
        System.out.println("Transaction Class Test Started");
        System.out.println("---------------------------------\n");
    }

    @AfterAll
    @DisplayName("End of Transaction Class Test")
    public static void tearDown() {
        System.out.println("\n-------------------------------------");
        System.out.println("Transaction Class Test Ended");
        System.out.println("-------------------------------------\n");
    }

    @Test
    @DisplayName("Test Transfer Money")
    public void testProcessTransfer(){
        t1.processTransfer();
        assertEquals(7000,a1.getBalance());
        assertEquals(7000,a2.getBalance());
        t2.processTransfer();
        assertEquals(7000, a1.getBalance());
        assertEquals(7000, a2.getBalance());
        assertFalse(18000 == a1.getBalance());
        assertFalse(-4000 == a2.getBalance());

    }

    @Test
    @DisplayName("Test Get Transactions Details")
    public void testGetTransactionDetails(){
        assertEquals("Transaction ID: " + t1.getTransactionId() + ", Date: " + t1.getTransactionDate() +
                             ", From: " + t1.getTransactionFromAccountId() + ", To: " + t1.getTransactionToAccountId() +
                             ", Amount: " + t1.getTransactionAmount()
                            ,t1.getTransactionDetails());
    }

    @Test
    @DisplayName("Test Get Transactions Id")
    public void testGetTransactionId(){
        assertEquals("t466178369xr16",t1.getTransactionId());
        assertEquals("t43161246rs796",t2.getTransactionId());
    }

    @Test
    @DisplayName("Test Get Transaction Date")
    public void testGetTransactionDate(){
        assertEquals("14/04/2024",t1.getTransactionDate());
        assertEquals("15/04/2024",t2.getTransactionDate());
    }

    @Test
    @DisplayName("Test Get Transaction Sender Account ID")
    public void testGetTransactionFromAccountId() {
        assertEquals(a2.getAccountId(),t1.getTransactionFromAccountId());
        assertEquals(a2.getAccountId(),t2.getTransactionFromAccountId());
    }

    @Test
    @DisplayName("Test Get Transaction Receiver Account ID")
    public void testGetTransactionToAccountId() {
        assertEquals(a1.getAccountId(),t1.getTransactionToAccountId());
        assertEquals(a1.getAccountId(),t2.getTransactionToAccountId());
    }

    @Test
    @DisplayName("Test Get Transaction Amount")
    public void testGetTransactionAmount() {
        assertEquals(3000,t1.getTransactionAmount());
        assertEquals(11000,t2.getTransactionAmount());
    }
}