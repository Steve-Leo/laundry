package com.ruanku.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.service.ShopService;
import com.ruanku.util.AppException;

public class MapAction extends ActionSupport{
	private JSONArray shopArray;
	ShopService shopService = new ShopService();

	public JSONArray getShopArray() {
		return shopArray;
	}

	public void setShopArray(JSONArray shopArray) {
		this.shopArray = shopArray;
	}
	
	public String getShopByLocation() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		try {
			shopArray = shopService.getShopByLocation(province, city);
		} catch (AppException e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}
}
