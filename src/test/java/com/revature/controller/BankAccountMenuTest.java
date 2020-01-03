package com.revature.controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.NegativeAmountWithdrawException;
import com.revature.model.BankAccount;


public class BankAccountMenuTest {
	
	BankAccountMenu bankAccountMenu;
	BankAccount acc;

	 // This will run **before** EACH @Test
//	@Before                       
//    public void setUptestDeposit(){
//
//        bankAccountMenu = new BankAccountMenu("un32");
//    } 

   
    @SuppressWarnings("deprecation")
	@Test
    public void testDeposit(){
    	 bankAccountMenu = new BankAccountMenu("un32");
    	bankAccountMenu.deposit(100.00, "un32");
        assertEquals(300,bankAccountMenu.getBalance(), 0.0); 
//        assertEquals(100,acc.getBalance(), 0.0); 
    }
    
//    public void testDepositException()throws NegativeAmountWithdrawException {
//    	assertThrows(NegativeAmountWithdrawException.class,(withdrawAmount, userName)-> bankAccountMenu.withdraw(-500.00,"un30"));
//    }
    
    @Test(expected = NegativeAmountWithdrawException.class)
	 public void testException() throws NegativeAmountWithdrawException {
    	 bankAccountMenu = new BankAccountMenu("un32");
    	 bankAccountMenu.withdraw(-500.00, "un32");
  }
}

