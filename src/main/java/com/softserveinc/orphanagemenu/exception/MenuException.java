package com.softserveinc.orphanagemenu.exception;

public class MenuException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String infoMessage;
	public MenuException(){
		
	}
	public MenuException(String message){
		this.infoMessage = message;
	}
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
		
	

}
