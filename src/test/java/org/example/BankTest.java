package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    public static Account a1, a2, a3, a4, a5;
    public static Bank bank;
    public static Transaction t1, t2, t3, t4;

    @BeforeAll
    public static void setUp() {
        bank = new Bank();
        a1 = new Account("1", "Joe", 0, "12345");
        a2 = new Account("2", "Adam", 10, "67890");
        a3 = new Account("3", "Ben", 100, "13579");
        a4 = new Account("4", "Smith", 1000, "02468");
        a5 = new Account("5", "Tom", 10000, "87654");
        t1 = new Transaction(a2, a1, 3000,"13/03/2023", "T");
        t2 = new Transaction(a4, 1000,"14/04/2024","D");
        t3 = new Transaction(a3, a1, 90,"12/02/2022","T");
        t4 = new Transaction(a3, 7316,"18/05/2020","W");
        System.out.println("\n-----------------------");
        System.out.println("Bank Class Test Started");
        System.out.println("-----------------------\n");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("\n---------------------");
        System.out.println("Bank Class Test Ended");
        System.out.println("---------------------\n");
    }


    @Test
    @DisplayName("Test Create User Account")
    public void testCreateUserAccount() {
        Account newUser1 = new Account("100", "Ahmed", 500.0, "A50004@x");
        Account newUser2 = new Account("222", "Mohamed", 800.0, "Axc43bkjhs");
        assertNotNull(newUser1);
        assertNotNull(newUser2);
        assertEquals("100", newUser1.getAccountId());
        assertEquals("222", newUser2.getAccountId());
        assertEquals("Ahmed", newUser1.getAccountOwner());
        assertEquals("Mohamed", newUser2.getAccountOwner());
        assertEquals(500.0, newUser1.getBalance());
        assertEquals(800.0, newUser2.getBalance());

    }

    @Test
    @DisplayName("Check added Accounts")
    public void testAddedAccounts() {
        bank.addAccount(a1);
        bank.addAccount(a2);
        bank.addAccount(a3);
        bank.addAccount(a4);
        assertEquals(bank.getAccount("1").getAccountId(), "1");
        assertEquals(bank.getAccount("2").getAccountId(), "2");
        assertEquals(bank.getAccount("3").getAccountId(), "3");
        assertEquals(bank.getAccount("4").getAccountId(), "4");
        assertNotEquals(bank.getAccount("4").getAccountId(), "3");
    }
    @Test
    @DisplayName("test Get Accounts")
    public void testGetAccount(){
        assertEquals(a1, bank.getAccount("1"));
        assertEquals(a2, bank.getAccount("2"));
        assertEquals(a3, bank.getAccount("3"));
        assertEquals(a4, bank.getAccount("4"));
    }

    @Test
    @DisplayName("Check Transaction")
    public void testTransaction() {
        bank.transactions.add(t1);bank.transactions.add(t2);bank.transactions.add(t3);
        assertEquals(bank.getTransaction(t1.getTransactionId()), t1);
        assertEquals(bank.getTransaction(t2.getTransactionId()), t2);
        assertEquals(bank.getTransaction(t3.getTransactionId()), t3);
        assertFalse(bank.getTransaction(t4.getTransactionId()) == t4);
    }
}

