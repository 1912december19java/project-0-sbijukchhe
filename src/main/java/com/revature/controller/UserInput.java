package com.revature.controller;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.repository.UserRepositoryJdbc;
import com.revature.exception.InvalidPasswordException;
import com.revature.exception.InvalidUsernameExeption;
import com.revature.model.User;

public class UserInput {
	
	public UserInput() {
		
	}
	
	private static final Logger LOGGER = Logger.getLogger(UserInput.class);

	Scanner sc = new Scanner(System.in);
		
	User user = new User();
	UserRepositoryJdbc userRepositoryJdbc = new UserRepositoryJdbc();
	
	public void createAccount () {
		
		String createUserName;
		String createPassword;
		String fname;
		String lname;

		LOGGER.info("Follow prompts to create online account.");
		LOGGER.info("-----------------------------------------\n");
		
		LOGGER.info("Enter email address or username: ");
		createUserName = sc.nextLine();
		
		// validates username
		while(userRepositoryJdbc.isMatchUsername(createUserName)) {
			try {
				while(userRepositoryJdbc.isMatchUsername(createUserName)) {
				 
				throw new InvalidUsernameExeption ("Username already exists. Please Try Again..\n");
				}
			}
				catch(InvalidUsernameExeption e) {
					LOGGER.error(e);
					LOGGER.info("Please enter username: ");
					LOGGER.info("-------------------------");
					createUserName = sc.nextLine();
				}
			}  
		
		user.setUsername(createUserName);
		
		
		LOGGER.info("Enter password: ");
		createPassword = sc.nextLine();
		user.setPassword(createPassword);
		LOGGER.info("Enter first name: ");
		fname = sc.nextLine();
		user.setFname(fname);
		LOGGER.info("Enter last name: ");
		lname = sc.nextLine();
		user.setLname(lname);

		userRepositoryJdbc.createUserAccount(user);		
	}
	
	public void logIn() {			
		LOGGER.info("Please enter username: ");
		LOGGER.info("-------------------------");
		String userName = sc.nextLine();
		
		while(!userRepositoryJdbc.isMatchUsername(userName)) {
		try {
			while(!userRepositoryJdbc.isMatchUsername(userName)) {
			 
			throw new InvalidUsernameExeption ("You have entered invalid Username. Please Try Again..\n");
			}
		}
			catch(InvalidUsernameExeption e) {
				LOGGER.error(e);
				LOGGER.info("Please enter username: ");
				LOGGER.info("-------------------------");
				userName = sc.nextLine();
			}
		}  
	 					
		LOGGER.info("Please enter password:");
		LOGGER.info("-----------------------");
		String password = sc.nextLine();
		
		while(!userRepositoryJdbc.isMatchPassword(password, userName)) {
			try {
				while(!userRepositoryJdbc.isMatchPassword(password, userName)) {
				 
				throw new InvalidPasswordException ("You have entered invalid Password. Please Try Again..\n");
				}
			}
				catch(InvalidPasswordException e) {
					LOGGER.error(e);
					LOGGER.info("Please enter password: ");
					LOGGER.info("-------------------------");
					password = sc.nextLine();
				}
			}  
		
//		while(!userRepositoryJdbc.isMatchPassword(password, userName)) {
//			LOGGER.info("Try again: ");
//			password = sc.nextLine();
//		} 
		
		
		BankAccountMenu bank = new BankAccountMenu();
		bank.accountMenu(userName);
	}

}
