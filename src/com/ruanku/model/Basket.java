package com.ruanku.model;

public class Basket {
	private int id;
	private int clothId;
	private int userId;
	private int clothesType;
	private int washMode;
	
	//新增属性
	private int count;
	private int shopId;
	private String shopName;
	private String clothName;
	private Double price;
	
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getClothName() {
		return clothName;
	}
	public void setClothName(String clothName) {
		this.clothName = clothName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClothId() {
		return clothId;
	}
	public void setClothId(int clothId) {
		this.clothId = clothId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getClothesType() {
		return clothesType;
	}
	public void setClothesType(int clothesType) {
		this.clothesType = clothesType;
	}
	public int getWashMode() {
		return washMode;
	}
	public void setWashMode(int washMode) {
		this.washMode = washMode;
	}
	
}
