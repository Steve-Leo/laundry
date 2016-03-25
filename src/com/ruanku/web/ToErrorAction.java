package com.ruanku.web;

import com.opensymphony.xwork2.ActionSupport;

public class ToErrorAction extends ActionSupport{
	
	/**
	 * 处理异常页面的跳转
	 */
	@Override
	public String execute(){
		return "error";
	}
}
