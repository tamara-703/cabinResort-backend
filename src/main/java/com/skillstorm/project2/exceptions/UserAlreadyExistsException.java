package com.skillstorm.project2.exceptions;

/*
 * custom exception to handle cases where a user trying to signup 
 * is using a username that already exists in the database
 */
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
