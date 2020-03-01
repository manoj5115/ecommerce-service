package com.bleedev.momentum.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 2L;

	public BusinessException(String message) {
		super(message);
	}
}
