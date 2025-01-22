package com.wendel.DesafioPicpay.services.exceptions;

public class ProcessingServiceErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcessingServiceErrorException(String msg) {
		super(msg);
	}
	
}
