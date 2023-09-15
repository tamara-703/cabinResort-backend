package com.skillstorm.project2.exceptions;

public class UserAlreadyExistsException extends Exception {
	
	private String exception;

	public UserAlreadyExistsException(String exception) {
		super();
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	



}
