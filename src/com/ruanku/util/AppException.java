package com.ruanku.util;

public class AppException extends Exception{
	
	private int exceptionCode;
	private String message;
	
	public AppException(String message){
		this.message = message;
	}
	
	public AppException(String message,int exceptionCode){
		this.message = message;
		this.exceptionCode = exceptionCode;
	}

	public int getExceptionCode(){
		return exceptionCode;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getDetailMessage(){
		String detailMessage = "Detail message:" + exceptionCode + " " + message;
		return message;
	}
}
