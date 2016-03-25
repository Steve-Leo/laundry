package com.ruanku.model;

public class ClothesOfOrder {

	private int id;
	private int orderId;
	private int clothId;
	private int amount;
	private int washMode;
	private String clothName;
	private double price;
	private String pictureString;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getClothId() {
		return clothId;
	}
	public void setClothId(int clothId) {
		this.clothId = clothId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getWashMode() {
		return washMode;
	}
	public void setWashMode(int washMode) {
		this.washMode = washMode;
	}
	public String getClothName() {
		return clothName;
	}
	public void setClothName(String clothName) {
		this.clothName = clothName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPictureString() {
		return pictureString;
	}
	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
	}
	
	
}
