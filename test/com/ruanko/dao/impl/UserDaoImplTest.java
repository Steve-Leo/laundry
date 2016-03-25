package com.ruanko.dao.impl;

import junit.framework.TestCase;

import com.ruanku.dao.UserDao;
import com.ruanku.dao.impl.UserDaoImpl;
import com.ruanku.model.User;
 
public class UserDaoImplTest  extends TestCase{

	private UserDao dao = new UserDaoImpl();
	
	public void TestSave()
	{
		User user = new User();
		user.setAccount("zengpan");
		user.setPassword("123456");
		try {
			if(dao.save(user))
				System.out.println("sussess");
			else System.out.println("fail");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
