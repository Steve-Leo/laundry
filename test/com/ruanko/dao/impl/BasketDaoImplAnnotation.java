package com.ruanko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.BasketDao;
import com.ruanku.dao.impl.BasketDaoImpl;
import com.ruanku.model.Basket;
import com.ruanku.model.Clothes;

public class BasketDaoImplAnnotation {
	BasketDao Dao = new BasketDaoImpl();
	@Before
	public void before(){
		System.out.println("before");
	}
	
//	@Test
//	public void TestAddClothes(){

//		Clothes clothes = new Clothes();
//		clothes.setId(1);
//		clothes.setMode(1);
//		clothes.setComment("very good");
//		clothes.setName("T-shirt");
//		clothes.setPicture("dddd");
//		clothes.setPrice1(111);
//		clothes.setShopId(4);
//		clothes.setType(5);
//		try {
//			if(Dao.addClothes(clothes, 5, 10))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	} 
	
//	@Test
//	public void TestDelClothesByBasketId(){
//		try {
//			if (Dao.delClothesByBasketId(3)) {
//				System.out.println("sucess");
//			}else {
//				System.out.println("failed");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestGetAllClothesByUserid(){
//		List<Basket> list = new ArrayList<Basket>();
//		try {
//			list = Dao.getAllClothesByUserid(0);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Basket basket:list) {
//					System.out.println(basket.getId() +" "+ basket.getClothId()+ " " + basket.getWashMode());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestDelClothesByShopId() {
//		try {
//			if (Dao.delClothesByShopId(2)) {
//				System.out.println("sucess");
//			}else {
//				System.out.println("failed");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestchangeBasket() {
//		Basket basket = new Basket();
//		basket.setCount(5);
//		basket.setId(2);
//		try {
//			if (Dao.changeBasket(basket)) {
//				System.out.println("success");
//			}
//			else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestdelAllClothes() {
//		try {
//			if (Dao.delAllClothes(3)) {
//				System.out.println("success");
//			}else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestAddBasket() {
//		Basket basket = new Basket();
//		basket.setClothesType(1);
//		basket.setClothId(12);
//		basket.setCount(2);
//		basket.setShopId(2);
//		basket.setUserId(3);
//		basket.setWashMode(4);
//		try {
//			if (Dao.addBasket(basket)) {
//				System.out.println("success");
//			} else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
