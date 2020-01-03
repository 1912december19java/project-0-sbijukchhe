package com.revature.controller;
import java.util.Scanner;
import org.apache.log4j.Logger;


public class MainMenu {
	
	public MainMenu() {
				
	}
	
	UserInput input = new UserInput();
	
	private static final Logger LOGGER = Logger.getLogger(MainMenu.class);
	
	@SuppressWarnings("resource")
	public void mainMenu() {	
		int choice;
		Scanner options = new Scanner(System.in);
		
		LOGGER.info("Welcome");
		LOGGER.info("*********\n");
		LOGGER.info("1: Create account");
		LOGGER.info("2: Log in to existing account");
		LOGGER.info("3: Exit");
		LOGGER.info("\nPlease choose one of the menu options.");
		LOGGER.info("*****************************************");
		
		do {
			choice = options.nextInt();
						
			switch(choice) {
			
			case 1: 
				input.createAccount();
				LOGGER.info("Now press 2 to log in");
				break;
			
			case 2: 
				input.logIn();
				break;
				
			case 3:
				LOGGER.info("Have a nice day");
				break;
					
			default: 
				LOGGER.info("You must enter a choice from the menu");
			}
			
		} while(choice != 3);
		
		
	}

}
