package com.ruanku.web;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.mail.bean.MailUserDao;
import com.ruanku.util.AppException;

public class ToVerifyAction extends ActionSupport {
	private String nameMd5;//用户账户的md5
	private String randMd5;//随机生成的数字的md5值
	
	/**
	 * 编写人：曾盼
	 * 通过strust中配置的拦截器，拦截获得用户账户的md5值和随机数的md5值
	 * 与数据库中的字段进行比对，成功则将usertemp表中的那条数据插入user表
	 * 并删除verify和user那行数据
	 */
	public String execute() {
		try {
			MailUserDao mailDao = new MailUserDao();
			String name = mailDao.getVerify(nameMd5, randMd5);
			if (name != null) {
				//user表增加数据
				mailDao.addUser(name);
				//usertemp删除那行记录
				mailDao.delUserTemp(name);
				//verify删除那行记录
				mailDao.delVerify(name);
				return "login";
			} else {
				return "error";
			}

		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}

	public String getNameMd5() {
		return nameMd5;
	}

	public void setNameMd5(String nameMd5) {
		this.nameMd5 = nameMd5;
	}

	public String getRandMd5() {
		return randMd5;
	}

	public void setRandMd5(String randMd5) {
		this.randMd5 = randMd5;
	}

}
