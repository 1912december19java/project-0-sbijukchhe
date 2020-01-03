package com.revature.exception;

public class NegativeAmountWithdrawException extends RuntimeException {
	
	public NegativeAmountWithdrawException(String message) {
		super(message);
	}

}
