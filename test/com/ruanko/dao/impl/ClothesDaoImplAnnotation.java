package com.ruanko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.ClothesDao;
import com.ruanku.dao.impl.ClothesDaoImpl;
import com.ruanku.model.Clothes;

public class ClothesDaoImplAnnotation {
	private ClothesDao Dao= new ClothesDaoImpl();
	@Before
	public void before(){
		System.out.println("before");
	}
	
//	@Test
//	public void TestSaveClothes(){
//		Clothes clothes = new Clothes();
//		clothes.setComment("羽绒服4");
//		clothes.setMode(1);
//		clothes.setName("羽绒服4");
//		clothes.setPicture("dddd2ddd");
//		clothes.setPrice1(55);
//		clothes.setPrice2(66);
//		clothes.setShopId(3);
//		clothes.setType(4);
//		try {
//			if (Dao.saveClothes(clothes)) {
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
//	public void TestUpdateClothes() {
//		Clothes clothes = new Clothes();
//		clothes.setComment("羽绒服");
//		clothes.setMode(1);
//		clothes.setName("羽绒服");
//		clothes.setPicture("ddddddd");
//		clothes.setPrice1(55);
//		clothes.setPrice2(66);
//		clothes.setShopId(3);
//		clothes.setType(4);
//		clothes.setId(1);
//		try {
//			if (Dao.updateClothes(clothes)) {
//				System.out.println("success");
//			}
//			else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	@Test
//	public void TsetDeleteClothes(){
//		try {
//			if (Dao.deleteClothes(1)) {
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
//	public void TestIsClothesDel(){
//		try {
//			if (Dao.isClothesDel(1)) {
//				System.out.println("success");
//			}else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void TestFindAllClothes(){
//		List<Clothes> list = new ArrayList<Clothes>();
//		try {
//			list = Dao.findAllClothes();
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Clothes clothes:list) {
//					System.out.println(clothes.getId() + clothes.getName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestFindClothesbyShopId(){
//		List<Clothes> list = new ArrayList<Clothes>();
//		try {
//			list = Dao.findClothesbyShopId(3);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Clothes clothes : list) {
//					System.out.println(clothes.getId() + clothes.getName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestFindClothesById() {
//		try {
//			Clothes clothes = Dao.findClothesById(2);
//			if (clothes != null) {
//				System.out.println(clothes.getId() + clothes.getName());
//			}
//			else {
//				System.out.println("clothes is null");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestFindClothesbySearch() {
//		List<Clothes> list = new ArrayList<Clothes>();
//		try {
//			list = Dao.findClothesbySearch("羽绒服2");
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (Clothes clothes : list) {
//					System.out.println(clothes.getId() + clothes.getName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void TestFindClothesbySearchAndShopId() {
		List<Clothes> list = new ArrayList<Clothes>();
		try {
			list = Dao.findClothesbySearchAndShopId(1, "羽绒服");
			if (list.isEmpty()) {
				System.out.println("failed");
			}
			else {
				for (Clothes clothes : list) {
					System.out.println(clothes.getId() + clothes.getName());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
