package com.ruanku.model;

import java.util.List;

public class Order {

	private String ordernum;
	private int userId;
	private int shopId;
	private int state;
	private String receiver;
	private String address;
	private String tel;
	private String paytime;
	private String receivedTime;
	private String chargeTime;
	private int grade;
	private String comment;
	private String commentTime;
	private float totalAmount;
	private int payMode;
	private String shopName;
	
	private int id;
	private List<ClothesOfOrder> clothes;
	
	
	public List<ClothesOfOrder> getClothes() {
		return clothes;
	}
	public void setClothes(List<ClothesOfOrder> clothes) {
		this.clothes = clothes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPayMode() {
		return payMode;
	}
	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}
