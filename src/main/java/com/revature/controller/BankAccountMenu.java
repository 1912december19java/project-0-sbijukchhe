package com.revature.controller;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.repository.BankAccountRepositoryJdbc;
import com.revature.exception.ExcessAmountWithdrawalException;
import com.revature.exception.InvalidUsernameExeption;
import com.revature.exception.NegativeAmountDepositException;
import com.revature.exception.NegativeAmountWithdrawException;
import com.revature.model.BankAccount;

public class BankAccountMenu  {
	
	private static final Logger LOGGER = Logger.getLogger(BankAccountMenu.class);
	
	double balance;
	String username;
	double firstDeposit;
	
	BankAccount account = new BankAccount();
	
	public BankAccountMenu() {
		super();
	}
	
	
	public BankAccountMenu(String username) {
		super();
		this.username = username;
	}

	public BankAccountMenu(double balance, String username) {
		super();
		this.balance = balance;
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	// method to deposit amount
	void deposit (double depositAmount, String userName) {
		BankAccountRepositoryJdbc bar = new BankAccountRepositoryJdbc();
		balance = bar.getBalance(userName);
		
		try {
			if(depositAmount != 0) {
				if(depositAmount > 0) {
					balance = balance+depositAmount;
					account.setBalance(balance);
					account.setUsername(userName);
					bar.updateAccount(account);
				} else {
					throw new NegativeAmountDepositException ("You attempted to deposit a negative amount.");
				}
			}
		}
		catch (NegativeAmountDepositException e) {
			LOGGER.info(e);
		}
	}
	
	
	// Method to withdraw
	void withdraw (double withdrawAmount, String userName) throws  NegativeAmountWithdrawException{
		BankAccountRepositoryJdbc bar = new BankAccountRepositoryJdbc();
		balance = bar.getBalance(userName);

		if(withdrawAmount != 0) {
			try {
				if(withdrawAmount < 0) {
					throw new NegativeAmountWithdrawException ("You attempted to withdraw a negative amount.");
				}

				else if (withdrawAmount > balance) {
					throw new ExcessAmountWithdrawalException("You attempted to withdraw more than your available balance");
				} 

				else {
					balance = balance - withdrawAmount;
					account.setBalance(balance);
					account.setUsername(userName);
					bar.updateAccount(account);
				}
			}

			catch(NegativeAmountWithdrawException | ExcessAmountWithdrawalException e) {
				System.out.println(e);
			}
		}
	}	
	
	
	void accountMenu(String userName) {
		BankAccountRepositoryJdbc bar = new BankAccountRepositoryJdbc();
		balance = bar.getBalance(userName);
		char choice;
		Scanner choiceIn = new Scanner(System.in);
		double depositAmount = 0;
		double withdrawAmount = 0;
		
		LOGGER.info("Welcome");
		LOGGER.info("********");
		
			
		do {
			
			LOGGER.info("\nPlease enter an option from the menu - A: View balance \t B: Deposit \t C: Withdraw \t D: Log out");
			LOGGER.info("------------------------------------   ------------------------------------------------------------\n");
						
			choice = Character.toUpperCase(choiceIn.next().charAt(0));
						
			switch(choice) {
			
			case 'A': 
				LOGGER.info("******** Your current Balance is: " + balance +" *********\n");
				break;
			
			case 'B': 
				
				LOGGER.info("Please enter deposit amount: ");
				LOGGER.info("--------------------------------\n");
				while(!(choiceIn.hasNextDouble())) {		
					LOGGER.info("You must enter a number. Try again.");
					LOGGER.info("*************************************\n");
					choiceIn.next();
				}
				depositAmount = choiceIn.nextDouble();
				LOGGER.info("******** Your deposit amount is: " + depositAmount + " *********\n");
				
				deposit(depositAmount, userName);
				
				break;
			
			case 'C':
				LOGGER.info("Please enter Withdrawal amount: ");
				LOGGER.info("---------------------------------\n");
				while(!(choiceIn.hasNextDouble())) {		
					LOGGER.info("You must enter a number. Try again.");
					LOGGER.info("*************************************\n");
					choiceIn.next();
				}
				
				withdrawAmount = choiceIn.nextDouble();
				LOGGER.info("******** Your withdrawal amount is: " + withdrawAmount + " *********\n");
				
				withdraw(withdrawAmount, userName);
				
				break;
				
			case 'D': 
				LOGGER.info("Thank you for using our services!");
				break;
			
			default: 
				LOGGER.info("You must enter a choice from the menu");
			}
			
		} while(choice != 'D');
		
	}
	
}



