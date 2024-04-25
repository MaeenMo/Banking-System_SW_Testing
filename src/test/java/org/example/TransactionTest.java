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
                             ", From: " + t1.getFromAccountId() + ", To: " + t1.getToAccountId() +
                             ", Amount: " + t1.getAmount()
                            ,t1.getTransactionDetails(t1.getTransactionType()));
    }

    @Test
    @DisplayName("Test Get Transactions Details For t2")
    public void testGetTransactionDetailsT2(){
        assertEquals("Transaction ID: " + t2.getTransactionId() + ", Date: " + t2.getTransactionDate() +
                        ", From: " + t2.getFromAccountId() +
                        ", Amount: " + t2.getAmount()
                ,t2.getTransactionDetails(t2.getTransactionType()));
    }
}