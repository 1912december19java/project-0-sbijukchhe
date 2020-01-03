package com.revature.repository;

import com.revature.model.BankAccount;

public interface BankAccountRepository {
	
	 public int createBankAccount(BankAccount account);
	 public void updateAccount(BankAccount account); 
	 public double getBalance(String username);
	
}
