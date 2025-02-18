package com.dairyproject.exceptions;

public class UnmatchedPasswordException extends RuntimeException {
	public UnmatchedPasswordException(String message) {
		super(message);
	}
}
