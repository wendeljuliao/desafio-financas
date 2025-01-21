package com.wendel.DesafioPicpay.services.exceptions;

public class InsuficientAmountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsuficientAmountException(String msg) {
		super(msg);
	}
	
}
