package com.ruanko.dao.impl;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.UserDao;
import com.ruanku.dao.impl.UserDaoImpl;
import com.ruanku.model.User;

public class UserDaoImplAnnotation {

private UserDao dao = new UserDaoImpl();
	

	@Before
	public void before(){
		System.out.println("before");
	}

	
//	@Test
//	public void TestUpdateInfo()
//	{
//		
//		try {
//			if(dao.UpdateUserInfo(15, "曾盼", "深圳", "11111111111"))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//	public void TestSave()
//	{
//		User user = new User();
//		user.setAccount("曾");
//		user.setPassword("123456");
//		try {
//			if(dao.save(user))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
	
	
//	@Test
//	public void TestIsExists()
//	{
//		try {
//			if(dao.isExist("hellokitty"))
//			
//				
//					System.out.println("sussess1");
//				else System.out.println("fail1");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	@Test
//	public void TestGetUser()
//	{
//		
//		
//		
//		try {
//			User user = dao.getUser(1);
//			
//				System.out.println(user.getPassword());
//				System.out.println(user.getAccount());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	@Test
//	public void TestLogin()
//	{
//		
//		
//		
//		try {
//				System.out.println(dao.login("hellokitty", "123456"));
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestChangePassword()
//	{
//		try {
//				
//				System.out.println(dao.changePassword("hellokitty", "123456","123"));
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	@Test
//	public void TestChangePayPassword() {
//		try {
//			if (dao.changePayPassword(1, "qq", "qwqwq")) {
//				System.out.println("success");
//			}
//			else {
//				System.out.println("failed");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void TestGetAllUser() {
//		List<User> list = new ArrayList<User>();
//		try {
//			list = dao.getAllUser();
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (User user:list) {
//					System.out.println(user.getId() + user.getName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestGetUserWithDel() {
//		List<User> list = new ArrayList<User>();
//		try {
//			list = dao.getUserWithDel(0);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (User user:list) {
//					System.out.println(user.getId() + user.getName());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
}
