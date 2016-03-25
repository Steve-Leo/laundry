package com.ruanko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.ShopDao;
import com.ruanku.dao.impl.ShopDaoImpl;
import com.ruanku.model.Shop;


public class ShopDaoImplAnnotation {
	private ShopDao dao = new ShopDaoImpl();
	
	@Before
	public void before(){
		System.out.println("before");
	}

	
	
//	@Test
//	public void TestRegisterShop(){
//		Shop shop = new Shop();
//		shop.setAddress("aaaa");
//		shop.setCity("suzhou");
//		shop.setComment("good");		
//		shop.setDel(0);
//		shop.setGrade(0);
//		shop.setId(0);
//		shop.setLatitude(0.0f);
//		shop.setLongitude(0.0f);
//		shop.setMoney(100);
//		shop.setPassword("qqq");
//		shop.setProvince("jiangsu");
//		shop.setShopname("superxi");
//		shop.setPicture("ssssss");
//		shop.setState(0);
//		shop.setTel("110");
//		shop.setUsername("babala");
//		
//		
//		try {
//			if(dao.registerStore(shop))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	} 
	
//	@Test
//	public void TestUpdateShop() {
//		Shop shop = new Shop();
//		shop.setAddress("aaaa");
//		shop.setCity("suzhou");
//		shop.setComment("good");		
//		shop.setDel(0);
//		shop.setGrade(0);
//		shop.setId(1);
//		shop.setLatitude(0.0f);
//		shop.setLongitude(0.0f);
//		shop.setMoney(100);
//		shop.setPassword("qqq");
//		shop.setProvince("jiangsu");
//		shop.setShopname("superxi");
//		shop.setPicture("qqqqq");
//		shop.setState(0);
//		shop.setTel("110");
//		shop.setUsername("babala");
//		
//		try {
//			if(dao.updateStore(shop))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void TestIsUsernameExist() {
//		try {
//			if(dao.isUsernameExist("babala"))
//			
//				
//					System.out.println("sussess1");
//				else System.out.println("fail1");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestSetShopDel() {
//		try {
//			if(dao.setStoreDel(1, 1))
//				System.out.println("sussess1");
//				else System.out.println("fail1");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestSetShopDel() {
//		try {
//			if (dao.isStoreDel(0)) {
//				System.out.println("success");
//			}
//			else
//				System.out.println("failed");
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestgetAllShops(){
//		List<Shop> list = new ArrayList<Shop>();
//		try {
//			list = dao.getAllShops();
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Shop shop:list) {
//					System.out.println(shop.getId() + shop.getShopname());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestGetShopsByState(){
//		List<Shop> list = new ArrayList<Shop>();
//		try {
//			list = dao.getShopsBycondition(2);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Shop shop:list) {
//					System.out.println(shop.getId() + shop.getShopname());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void TestDelRechargeRecord(){
		try {
			if (dao.delShop(2)) {
				System.out.println("sucess");
			}else {
				System.out.println("failed");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
