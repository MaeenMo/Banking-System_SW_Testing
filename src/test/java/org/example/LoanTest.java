package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanTest {
    static Loan loan;
    static Account account;

    @BeforeAll
    static void setUp() {
        System.out.println("\n-----------------------");
        System.out.println("Loan Class Test Started");
        System.out.println("-----------------------\n");
        account = new Account("1", "Joe", 0, "12345");
        loan = new Loan("1", 5000,account );
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("\n-------------------------------------");
        System.out.println("Loan Class Tested Successfully");
        System.out.println("-------------------------------------\n");
    }

    @Test
    @Order(1)
    void testApplyForLoan() {
        loan.disburseLoan();
        assertEquals(5000, loan.getLoanAmount());
    }

    @Test
    @Order(2)
    void testRepayLoan() {
        loan.makePayment(2000);
        assertEquals(3000, loan.getLoanAmount());
    }

    @Test
    @Order(3)
    void testRepayMoreThanLoanBalance() {
        loan.makePayment(4000);
        assertFalse(loan.getLoanAmount()==0);
    }
    @Test
    @Order(4)
    void testReturnLoanAmount() {
        assertEquals(3000, loan.getLoanAmount());
    }
}