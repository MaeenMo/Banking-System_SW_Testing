package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    public static Account a1 = new Account("1","Joe",2000,"12345");
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
        double oldBalance = a1.getBalance();
        a1.deposit(200);
        a1.withdraw(100);
        assertEquals(oldBalance+100, a1.getBalance());
    }

    @Test
    @DisplayName("Test Withdraw Insufficient Amount from User Account")
    public void testWithdrawWithInSufficientAmount() {
        a1.withdraw(1000);
        assertFalse( a1.getBalance() == 0);
    }

    @Test
    @DisplayName("Test take 5 years loan successfully")
    public void testTakeLoan5Years() {
        double oldBalance = a1.getBalance();
        a1.takeLoan("L1644CR49", 8000, 15, 3);
        assertTrue( a1.takenLoans.getLast().getLoanId().equals("L1644CR49"));
        assertEquals(a1.getBalance(), 8000+oldBalance);
    }

    @Test
    @DisplayName("Test pay 5 years loan successfully")
    public void testPayLoan5Years() {
        double oldBalance = a1.getBalance();
        assertTrue(a1.payLoan("L1644CR49"));
        assertEquals(a1.getBalance(), oldBalance - (8000 + (8000*0.15)));
    }

    @Test
    @DisplayName("Test take 3 years loan successfully")
    public void testTakeLoan3Years() {
        double oldBalance = a1.getBalance();
        a1.takeLoan("L764RH2155", 10000, 10, 1);
        a1.takenLoans.getLast().setStartYear(2022);
        assertTrue( a1.takenLoans.getLast().getLoanId().equals("L764RH2155"));
        assertEquals(a1.getBalance(), 10000+oldBalance);
    }

    @Test
    @DisplayName("Test pay 3 years loan fail")
    public void testPayLoan3Years() {
        double oldBalance = a1.getBalance();
        assertFalse(a1.payLoan("L764RH2155"));
        assertFalse(a1.getBalance() == oldBalance - (10000 + (10000*0.1)));
    }
}