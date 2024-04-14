package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AccountTest {
    public static Account a1 = new Account("1","Joe",0,"12345");
    @BeforeAll
    public static void setUp() {
        System.out.println("\n---------------------------------");
        System.out.println("Account Class Test Started");
        System.out.println("---------------------------------\n");
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("\n---------------------------------");
        System.out.println("Account Class Test Ended");
        System.out.println("---------------------------------\n");
    }

    @DisplayName("Test Deposit to User Account")
    @ParameterizedTest
    @ValueSource(doubles = {100, 200, 300})
    public void testDeposit(double a) {
        double oldBalance = a1.getBalance();
        a1.deposit(a);
        assertEquals(oldBalance + a, a1.getBalance());
    }

    @Test
    @DisplayName("Test Withdraw With Sufficient Amount from User Account")
    public void testWithdrawWithSufficientAmount() {
        a1.deposit(200);
        a1.withdraw(100);
        assertEquals(100, a1.getBalance());
    }

    @Test
    @DisplayName("Test Withdraw Insufficient Amount from User Account")
    public void testWithdrawWithInSufficientAmount() {
        a1.withdraw(1000);
        assertFalse( a1.getBalance() == 0);
    }
}