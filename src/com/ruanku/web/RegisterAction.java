package com.ruanku.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.dao.UserDao;
import com.ruanku.dao.impl.UserDaoImpl;
import com.ruanku.mail.bean.MailUserDao;
import com.ruanku.mail.bean.md5;
import com.ruanku.mail.bean.random;
import com.ruanku.mail.bean.sendMail;
import com.ruanku.util.AppException;

public class RegisterAction extends ActionSupport{
	
	private String account;//用户账号
	private String password;//用户密码
	private String address;//用户的收货地址
	private String telephone;//用户的电话
	private String email;//用户的邮箱
	private String name;//用户的姓名
	
	private String message;
	
	//private UserService userService = new UserService();
	
	/**
	 * 处理注册请求
	 */
	@Override
	public String execute(){
		/**
		 * 接收注册信息，调用逻辑层UserService的register()方法，处理注册业务逻辑
		 */
//		User user = new User();
//		user.setAccount(account);
//		user.setName(name);
//		user.setPassword(password);
//		user.setMail(email);
//		user.setTel(telephone);
//		user.setAddress(address);
		
		try {
			UserDao udao = new UserDaoImpl();
			MailUserDao dao = new MailUserDao();
			//如果用户登录账号在user表和usertemp表中存在该用户名则提示用户名已存在
			if((!udao.isExist(account))&&(!dao.findUserTemp(account))){
				//1.注册成功
				
				dao.addUserTemp(account, password, email, name, address, telephone);
				md5 md5new=new md5();
				random rand=new random();
				//产生用户名的md5值
				String nameMD5 = md5new.getMD5Str(account);
				//产生一个随机数的md5值
				String randMd5=md5new.getMD5Str(String.valueOf(rand.getInt()));
				//在verify表中，加入这个账号、账号的md5值和随机数的md5值
				dao.addVerify(account, nameMD5, randMd5);
				sendMail send=new sendMail();
				//发送验证邮箱，根据账号的md5值和随机数的md5值验证
				send.sendVerify(email,nameMD5,randMd5);
				return "login";
			}
			else {
				//2.注册失败
				message = "用户名重复！";
				return "register";
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			//3.系统异常
			return "error";
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
