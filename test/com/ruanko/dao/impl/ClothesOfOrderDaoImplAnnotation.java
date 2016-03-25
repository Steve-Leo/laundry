package com.ruanko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.ClothesOfOrderDao;
import com.ruanku.dao.impl.ClothesOfOrderDaoImpl;
import com.ruanku.model.COOBusi;
import com.ruanku.model.ClothesOfOrder;

public class ClothesOfOrderDaoImplAnnotation {
	private ClothesOfOrderDao dao = new ClothesOfOrderDaoImpl();
	@Before
	public void before(){
		System.out.println("before");
	}
	
//	@Test
//	public void TestFindCOOBusi(){
//		List<COOBusi> list = new ArrayList<COOBusi>();
//		try {
//			list = dao.findCOOBusi(1);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (COOBusi cooBusi : list) {
//					System.out.println(cooBusi.getClothId()+" " + cooBusi.getClothName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	@Test
//	public void TestSave() {
//		ClothesOfOrder clothesOfOrder = new ClothesOfOrder();
//		clothesOfOrder.setAmount(44);
//		clothesOfOrder.setClothId(4);
//		clothesOfOrder.setClothName("ffddd");
//		clothesOfOrder.setOrdernum("23456789");
//		clothesOfOrder.setPrice(3333);
//		clothesOfOrder.setWashMode(4);
//		try {
//			if (dao.save(clothesOfOrder)) {
//				System.out.println("success");
//			}else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestFindClothesOfOrder() {
//		List<ClothesOfOrder> list = new ArrayList<ClothesOfOrder>();
//		try {
//			list = dao.findClothesOfOrder(1);
//			if (list.isEmpty()) {
//				System.out.println("Failed");
//			}
//			else {
//				for (ClothesOfOrder clothesOfOrder : list) {
//					System.out.println(clothesOfOrder.getClothId() + " " + clothesOfOrder.getClothName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
