package com.ruanku.model;

import java.util.List;

public class Shop {
	private int id;
	private String shopname;
	private String address;
	private String tel;
	private String comment;
	private String username;
	private String password;
	private int grade;
	private String picture;
	private int state;
	private int del;
	
	//新增字段
	private String province;
	private String city;
	private double longitude;
	private double latitude;
	private float money;
	private double distant;
	private List<Clothes> clothes;
	
	public List<Clothes> getClothes() {
		return clothes;
	}
	public void setClothes(List<Clothes> clothes) {
		this.clothes = clothes;
	}
	public Shop() {
		super();
	}
	public Shop(int id, String shopname, String address, String tel,
			String comment, int state, float money) {
		super();
		this.id = id;
		this.shopname = shopname;
		this.address = address;
		this.tel = tel;
		this.comment = comment;
		this.state = state;
		this.money = money;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getDistant() {
		return distant;
	}
	public void setDistant(double distant) {
		this.distant = distant;
	}
	
	
	
	
}
