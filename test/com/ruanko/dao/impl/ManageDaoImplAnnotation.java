package com.ruanko.dao.impl;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.ManagerDao;
import com.ruanku.dao.impl.ManagerDaoImpl;
import com.ruanku.model.Manager;


public class ManageDaoImplAnnotation {
	private ManagerDao dao = new ManagerDaoImpl();
	
	@Before
	public void before(){
		System.out.println("before");
	}

	@Test
	public void TestGetUser()
	{
		
		
		
		try {
			Manager manager = dao.getManager(1);
			
				System.out.println(manager.getPassword());
				System.out.println(manager.getName());
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Test
	public void TestLogin()
	{
		
		try {
			int id = dao.login("root", "root");
			
				System.out.println(id);
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestManagerExpend()
	{
		try {
			boolean flag = dao.managerExpend(100, 10);
//			Manager manager = dao.getManager(1);
			
				System.out.println(flag);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestManagerIncome()
	{
		try {
			boolean flag = dao.managerIncome(100, 10);
//			Manager manager = dao.getManager(1);
			
				System.out.println(flag);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
