package com.ruanko.dao.impl;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.mail.bean.MailUserDao;
import com.ruanku.model.Manager;

public class MailUserDaoAnnotation {
	private MailUserDao dao = new MailUserDao();

	// public boolean addUserTemp(String account, String password, String   1
	// mail,String name, String address, String tel) throws AppException
	// public boolean delUserTemp(String account) throws AppException		1
	// public boolean addVerify(String name, String namemd5, String			1
	// randMD5)throws AppException
	// public boolean delVerify(String name) throws AppException			1
	// public String getVerify(String nameMd5, String randMd5) throws    	1
	// AppException
	// public boolean addUser(String account) throws AppException       	1
	// public String getUserName(String mail) throws AppException			1
	// public boolean resetPassword(String account, String password)throws	1
	// AppException
	// public boolean existUser(String account, String password)throws		1
	// AppException
	// public boolean addReset(String name, String nameMd5, String     		1
	// randMd5)throws AppException
	// public boolean delReset(String name) throws AppException				1
	// public String getLegalReset(String nameMd5, String randMd5)throws
	// AppException

	@Before
	public void before() {
		System.out.println("before");
	}
	
	@Test
	public void TestgetLegalReset() {

		try {
			String name = dao.getLegalReset("nameMd5","randMd5");
			
				System.out.println(name);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void TestdelReset() {

		try {
			boolean flag = dao.delReset("zengpan");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestexistUser() {

		try {
			boolean flag = dao.resetPassword("zengpan","456");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestresetPassword() {

		try {
			boolean flag = dao.resetPassword("zengpan","456");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestgetUserName() {

		try {
			String name = dao.getUserName("285542150@qq.com");
			
				System.out.println(name);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestdelVerify() {

		try {
			boolean flag = dao.delVerify("zengpan");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestgetVerify() {

		try {
			String name = dao.getVerify("nameMd5","randMd5");
			
				System.out.println(name);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestdelUserTemp() {

		try {
			boolean flag = dao.delUserTemp("zengpan");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestaddReset() {

		try {
			boolean flag = dao.addReset("zengpan","nameMd5","randMd5");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	
	
	
	@Test
	public void TestaddVerify() {

		try {
			boolean flag = dao.addVerify("zengpan", "namemd5",
					"randMD5");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail1");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void TestaddUserTemp() {

		try {
			boolean flag = dao.addUserTemp("zengpan", "123",
					"285542150@qq.com", "曾盼", "武汉", "130637011");
			if (flag)
				System.out.println("sussess");
			else
				System.out.println("fail1");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
