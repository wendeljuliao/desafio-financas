package com.wendel.DesafioPicpay.services.exceptions;

public class LojistaMustNotTransferException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LojistaMustNotTransferException(String msg) {
		super(msg);
	}
	
}
