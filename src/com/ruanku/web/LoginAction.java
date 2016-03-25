package com.ruanku.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.model.User;
import com.ruanku.service.UserService;
import com.ruanku.util.AppException;

public class LoginAction extends ActionSupport {
	//成员变量
		private String account;//用户账号
		private String password;//用户密码
		
		private String message;//提示信息
		
		private UserService userService = new UserService();
		
		//execute 方法
		@Override
		public String execute(){
			/*
			 * 接受登录信息，调用逻辑层UserService的login方法，返回用户对象
			 */
			try {
				User user = userService.login(account, password);
				if(user != null){
					//登录成功，将用户对象保存到session
					//获取session
					Map session = ActionContext.getContext().getSession();
					//将用户对象保存到session中
					session.put("user",user);
					return "to_user_center";
				} else {
					//登录失败，跳转到登录界面
					message = "用户名或密码错误！";
					return "login";
				}
			} catch (AppException e){
				e.printStackTrace();
				//系统异常，跳转到异常页面
				return "error";
			}
		}

		//getter & setter
		

		public String getPassword() {
			return password;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
}
