package com.ruanku.model;

import java.util.List;

public class OrderBusiUser {
	private String ordernum;
	private int userId;
	private int shopId;
	private int state;
	private String paytime;
	private float totalAmount;
	private int id;
	private String shopName;
	private int payMode;
	private List<ClothesOfOrder> orderItems;
	
	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}
	
	public List<ClothesOfOrder> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(List<ClothesOfOrder> orderItems) {
		this.orderItems = orderItems;
	}
	
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
