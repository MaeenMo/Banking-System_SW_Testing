package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    public static Transaction t1= new Transaction(AccountTest.a2, AccountTest.a1, 3000,"14/04/2024", "T");
    public static Transaction t2= new Transaction(AccountTest.a1, 3000,"14/04/2024", "W");

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
    @DisplayName("Test Get Transactions Details For t1")
    public void testGetTransactionDetailsT1(){
        assertEquals("Transaction ID: " + t1.getTransactionId() + ", Date: " + t1.getTransactionDate() +
                             ", From: " + t1.getTransactionFromAccountId() + ", To: " + t1.getTransactionToAccountId() +
                             ", Amount: " + t1.getTransactionAmount()
                            ,t1.getTransactionDetails());
    }

    @Test
    @DisplayName("Test Get Transactions Details For t2")
    public void testGetTransactionDetailsT(){
        assertEquals("Transaction ID: " + t2.getTransactionId() + ", Date: " + t2.getTransactionDate() +
                        ", From: " + t2.getTransactionFromAccountId() + ", To: " + t2.getTransactionToAccountId() +
                        ", Amount: " + t2.getTransactionAmount()
                ,t2.getTransactionDetails());
    }
}