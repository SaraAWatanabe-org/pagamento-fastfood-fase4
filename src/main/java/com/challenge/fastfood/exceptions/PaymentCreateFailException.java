package com.challenge.fastfood.exceptions;

public class PaymentCreateFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PaymentCreateFailException(String message) {
		super(message);
	}

	public PaymentCreateFailException(String message, Throwable cause) {
		super(message, cause);
	}

}
