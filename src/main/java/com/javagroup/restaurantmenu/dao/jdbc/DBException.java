package com.javagroup.restaurantmenu.dao.jdbc;

public class DBException extends RuntimeException {
	private Exception originalException;
	private String errorMessage;
	
	public DBException(String errorMessage, Exception initialException){
		this.errorMessage = errorMessage;
		this.originalException = initialException;
	}

	public Exception getOriginalException() {
		return originalException;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
