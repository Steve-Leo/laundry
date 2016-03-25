package com.ruanku.mail.bean;

public class User {	
	private String account;
	private String password;
	private String tel;
	private String mail;
	private String address;	
	private int del;	
	private String name;
	
	
	
	public User() {
		this.del = 0;
	}
	public User(String account, String password, String tel, String mail,
			String address, int del, String name) {
		this.account = account;
		this.password = password;
		this.tel = tel;
		this.mail = mail;
		this.address = address;
		this.del = 0;
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
