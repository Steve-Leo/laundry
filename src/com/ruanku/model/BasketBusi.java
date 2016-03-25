package com.ruanku.model;

import java.util.List;

public class BasketBusi{

	private List<Basket> items;
	private int shopId;
	private String shopName;
	

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<Basket> getItems() {
		return items;
	}

	public void setItems(List<Basket> items) {
		this.items = items;
	}

	
}
