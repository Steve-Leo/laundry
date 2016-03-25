package com.ruanku.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.mail.bean.MailUserDao;
import java.util.Random;

public class ToResetAction extends ActionSupport {
	
	private String nameMd5;
	private String randMd5;
	private String msg;

	/**
	 * 编写人：曾盼
	 * 作用：
	 * 用户忘记密码，重置密码
	 */
	@Override
	public String execute() throws Exception {

		MailUserDao dao = new MailUserDao();

		try {
			//获得一个合法的充值密码的用户的用户名
			String name = dao.getLegalReset(nameMd5, randMd5);

			if (name != null) {
				String str = getRandomString(10);
				msg = "你的密码已被重置为"+str;
				//重置密码
				dao.resetPassword(name, str);
				//删除reset表中name的那行记录
				dao.delReset(name);
				//到重置新密码界面
				return "newpwd";
			} else {
				msg = "错误";
				return "error";
			}

		} catch (Exception e) {

			return "error";
		}

	}

	/**
	 * 编写人：曾盼
	 * @param length 是返回随机密码的长度
	 * @return 返回一个随机字符串，长度为length
	 */
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
