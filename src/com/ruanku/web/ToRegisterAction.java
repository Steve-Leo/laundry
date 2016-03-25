package com.ruanku.web;

import com.opensymphony.xwork2.ActionSupport;

public class ToRegisterAction extends ActionSupport{
	
	/**
	 * 处理注册页面的跳转
	 */
	@Override
	public String execute(){
		return "register";
	}
}