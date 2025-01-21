package com.wendel.DesafioPicpay.services.exceptions;

public class RecordAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RecordAlreadyExistsException(String msg) {
		super(msg);
	}
}
