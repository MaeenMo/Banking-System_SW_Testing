package org.example;

import org.junit.platform.runner.JUnitPlatform;

@SuppressWarnings("deprecation")
@RunWith(JUnitPlatform.class)
@SelectClasses({LoanTest.class, BankTest.class, TransactionTest.class, AccountTest.class})
public class AllTests {}
