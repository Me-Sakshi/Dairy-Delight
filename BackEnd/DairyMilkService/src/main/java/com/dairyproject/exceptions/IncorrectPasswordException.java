package com.dairyproject.exceptions;

public class IncorrectPasswordException extends RuntimeException {
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
