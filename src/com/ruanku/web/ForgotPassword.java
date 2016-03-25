package com.ruanku.web;

import javax.servlet.ServletException;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.mail.bean.MailUserDao;
import com.ruanku.mail.bean.md5;
import com.ruanku.mail.bean.random;
import com.ruanku.mail.bean.sendMail;

public class ForgotPassword extends ActionSupport {

	private String mail;//用户的邮箱
	private String msg;//提示信息

	/**
	 * 用户忘记密码后，点击忘记密码时，填入邮箱
	 * 根据用户填入的邮箱，找到对应的用户
	 */
	@Override
	public String execute() throws Exception {

		md5 md5new;
		random rand;
		sendMail send;

		try {
			MailUserDao dao = new MailUserDao();
			String name = dao.getUserName(mail);

			if (name != null) {
				md5new = new md5();
				String nameMd5 = md5new.getMD5Str(name);
				rand = new random();
				String randMd5 = md5new
						.getMD5Str(String.valueOf(rand.getInt()));
				if(dao.findReset(name))
				{
					msg = "邮件早已发送到你的邮箱,请进入邮箱确认重置密码";
				}
				else
				{
				dao.addReset(name, nameMd5, randMd5);
				send = new sendMail();
				//若找到该用户则 在reset表中增加要重置的用户信息
				send.sendReset(mail, nameMd5, randMd5);
				msg = "邮件已发送到你的邮箱,请确认重置密码";
				}
				
			} else {
				msg = "不存在此邮箱用户";
				
			}
			return "remain";
		} catch (Exception e) {
			throw new ServletException(e.fillInStackTrace());
			
		}
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
