package com.lzw.work.dwf.action;

public class BaseAction {

	// 当前页
	protected int page;
	
	// 页面查询的行数
	protected int rows;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String toPage() {
		return "success";
	}

}
