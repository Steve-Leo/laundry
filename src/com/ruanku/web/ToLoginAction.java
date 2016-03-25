package com.ruanku.web;

import com.opensymphony.xwork2.ActionSupport;

public class ToLoginAction extends ActionSupport{
	
	/**
	 * 处理登录页面的跳转
	 */
	@Override
	public String execute(){
		return "login";
	}
}
