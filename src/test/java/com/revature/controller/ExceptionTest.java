//package com.revature.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import com.revature.exception.*;
//
//
//public class ExceptionTest {
//
//	
//	BankAccountMenu bankAccountMenu;
//
//	 // This will run **before** EACH @Test
//    @Before                       
//    public void setUptestException(){
//        bankAccountMenu = new BankAccountMenu("un30");
//    } 
//
//           
//	 @Test(expected = NegativeAmountWithdrawException.class)
//	 public void testException() {
//	 bankAccountMenu.withdraw(-500.00, "un30");
//   }
//}
