package com.ruanku.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
	
	@Override
	public String execute(){
		//获取session
		Map session = ActionContext.getContext().getSession();
		//移除session中的用户对象
		session.remove("user");
		//跳转到主页
		return "index";
	}
}
