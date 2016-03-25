package com.ruanku.mail.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthor extends Authenticator {
	private String username;
	private String password;
	public MyAuthor(String username,String password){
		this.username=username;
		this.password=password;
		
	}
	
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(username,password);
		}
	

}
