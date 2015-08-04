package com.softserveinc.orphanagemenu.exception;

public class MenuException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private Exception exception;

	public MenuException(){
		
	}
	public MenuException(String message){
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	

}
