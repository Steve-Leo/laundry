package com.ruanku.model;

import java.util.List;



public class OrderBusiShop extends Order{
	
	
	public OrderBusiShop() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<ClothesOfOrder> items;

	

	public List<ClothesOfOrder> getItems() {
		return items;
	}

	public void setItems(List<ClothesOfOrder> items) {
		this.items = items;
	}

	
}
