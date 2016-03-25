package com.ruanku.model;

/**
 * 数据分页显示
 * @author brady
 *
 */
public class PageBean {
	private int page;	//当前页
	private int rows;	//每页记录数
	public PageBean(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	public PageBean() {
		super();
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStart() {
		return (page - 1) * rows;
	}
}
