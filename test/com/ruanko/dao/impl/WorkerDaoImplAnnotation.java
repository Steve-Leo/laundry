package com.ruanko.dao.impl;

import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.WorkerDao;
import com.ruanku.dao.impl.WorkerDaoImpl;
import com.ruanku.model.User;
import com.ruanku.model.Worker;
import com.ruanku.model.Worker;
import com.ruanku.util.AppException;


public class WorkerDaoImplAnnotation {
	private WorkerDao dao = new WorkerDaoImpl();
	
	@Before
	public void before(){
		System.out.println("before");
	}

	
	@Test
	public void TestSave()
	{
		
		Worker woker = new Worker();
		woker.setBirthday("2016-02-28");
		woker.setName("zengpan");
		woker.setOrigin("武汉");
		woker.setSex(1);
		woker.setShopId(1);
		woker.setTel("11111111111");
		
		try {
			if(dao.save(woker))
				System.out.println("sussess");
			else System.out.println("fail");
		} 
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Test
	public void TestFindByShopId()
	{
		List<Worker> list;
		try {
			list = dao.findByShopId(1);
			for(Worker woker:list)
				System.out.println(woker.getName());
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/*
	@Test
	public void TestUpdateWoker()
	{
		Woker woker = new Woker();
		woker.setBirthday("2016-02-28");
		woker.setName("zengpan");
		woker.setOrigin("武汉");
		woker.setSex(1);
		woker.setShopId(1);
		woker.setTel("11111111111");
		try {
			dao.updateWoker(woker);
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}*/
	
}
