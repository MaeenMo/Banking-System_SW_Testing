package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanTest {
    static Loan loan;
    static Loan loanFail;
    static Account account;

    @BeforeAll
    static void setUp() {
        System.out.println("\n-----------------------");
        System.out.println("Loan Class Test Started");
        System.out.println("-----------------------\n");
        account = new Account("1", "Joe", 1000, "12345");
        loan = new Loan("1", 5000,account, 15, 5);
        loanFail = new Loan("2", 10000,account,10,3 );
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("\n-------------------------------------");
        System.out.println("Loan Class Tested Successfully");
        System.out.println("-------------------------------------\n");
    }

    @Test
    @Order(1)
    void testDisburseSuccessLoan() {
        double oldBalance = account.getBalance();
        loan.disburseLoan();
        assertEquals(account.getBalance(), loan.getLoanAmount()+oldBalance);
    }

    @Test
    @Order(2)
    void testPaySuccessLoan() {
        double oldBalance = account.getBalance();
        loan.makePayment();
        assertEquals(account.getBalance(), oldBalance - (loan.getLoanAmount() + (loan.getLoanAmount()*(loan.getInterestRate()/100))));
    }

    @Test
    @Order(3)
    void testDisburseFailLoan() {
        double oldBalance = account.getBalance();
        loanFail.disburseLoan();
        loanFail.setStartYear(2020);
        assertEquals(account.getBalance(), loanFail.getLoanAmount()+oldBalance);
    }
    @Test
    @Order(4)
    void testPayFailLoan() {
        double oldBalance = account.getBalance();
        loanFail.makePayment();
        assertFalse(account.getBalance() == oldBalance-loanFail.getLoanAmount());
    }
}