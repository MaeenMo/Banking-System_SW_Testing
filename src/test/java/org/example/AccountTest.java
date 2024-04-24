package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTest {
    public static Account a1 = new Account("1","Joe",2000,"12345");
    public static Account a2 = new Account("2","Smith",5000,"12345");

    @BeforeAll
    public static void setUp() {
        System.out.println("\n--------------------------");
        System.out.println("Account Class Test Started");
        System.out.println("--------------------------\n");
        Bank.loans.add(new Loan("L2", 0, null, 15, 3));
        Bank.loans.add(new Loan("L3", 0, null, 20, 5));
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("\n------------------------");
        System.out.println("Account Class Test Ended");
        System.out.println("------------------------\n");
    }

    @Order(1)
    @DisplayName("Test Deposit to User Account")
    @ParameterizedTest
    @ValueSource(doubles = {100, 200, 300, 400})
    public void testDeposit(double a) {
        double oldBalance = a1.getBalance();
        a1.processTransaction(a, "D");
        assertEquals(oldBalance + a, a1.getBalance());
    }

    @Order(2)
    @Test
    @DisplayName("Test Withdraw With Sufficient Amount from User Account")
    public void testWithdrawWithSufficientAmount() {
        double oldBalance = a1.getBalance();
        a1.processTransaction(200, "W");
        assertEquals(oldBalance - 200, a1.getBalance());
    }

    @Order(3)
    @Test
    @DisplayName("Test Withdraw Insufficient Amount from User Account")
    public void testWithdrawWithInSufficientAmount() {
        double oldBalance = a1.getBalance();
        a1.processTransaction(30000, "W");
        assertFalse(oldBalance - 30000 == a1.getBalance());
    }

    @Order(4)
    @Test
    @DisplayName("Test Valid Transfer Money")
    public void testProcessTransferValid(){
        double oldBalanceA1 = a1.getBalance();
        double oldBalanceA2 = a2.getBalance();
        a1.processTransaction(a2, 1000, "T");
        assertEquals(oldBalanceA1 - 1000,a1.getBalance());
        assertEquals(oldBalanceA2 + 1000,a2.getBalance());

    }

    @Order(5)
    @Test
    @DisplayName("Test Fail Transfer Money")
    public void testProcessTransferFail(){
        double oldBalanceA1 = a1.getBalance();
        double oldBalanceA2 = a2.getBalance();
        a1.processTransaction(a2, 2000, "T");
        assertFalse(oldBalanceA1 - 1000 == a1.getBalance());
        assertFalse(oldBalanceA2 + 1000 == a2.getBalance());

    }

    @Order(6)
    @Test
    @DisplayName("Test take 5 years loan successfully")
    public void testTakeLoan5Years() {
        double oldBalance = a1.getBalance();
        a1.takeLoan("L3", 8000 );
        assertTrue( a1.takenLoans.getLast().getLoanId().equals("L3"));
        assertEquals(a1.getBalance(), 8000+oldBalance);
    }

    @Order(7)
    @Test
    @DisplayName("Test pay 5 years loan successfully")
    public void testPayLoan5Years() {
        double oldBalance = a1.getBalance();
        assertTrue(a1.payLoan("L3"));
        assertEquals(a1.getBalance(), oldBalance - (8000 + (8000*0.2)));
    }

    @Order(8)
    @Test
    @DisplayName("Test take 3 years loan successfully")
    public void testTakeLoan3Years() {
        double oldBalance = a1.getBalance();
        a1.takeLoan("L2", 10000);
        a1.takenLoans.getLast().setStartYear(2022);
        assertTrue(a1.takenLoans.getLast().getLoanId().equals("L2"));
        assertEquals(a1.getBalance(), 10000+oldBalance);
    }

    @Order(9)
    @Test
    @DisplayName("Test pay 3 years loan fail")
    public void testPayLoan3Years() {
        double oldBalance = a1.getBalance();
        assertFalse(a1.payLoan("L2"));
        assertFalse(a1.getBalance() == oldBalance - (10000 + (10000*0.1)));
    }
}