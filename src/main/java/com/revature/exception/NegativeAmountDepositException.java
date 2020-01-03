package com.revature.exception;

public class NegativeAmountDepositException extends RuntimeException {

	public NegativeAmountDepositException(String message) {
		super(message);
	}
}
