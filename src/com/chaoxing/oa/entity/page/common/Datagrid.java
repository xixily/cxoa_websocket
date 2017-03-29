package com.chaoxing.oa.entity.page.common;

import java.util.List;

public class Datagrid {
	private long total;
	private List<Object> rows;
	private List<Object> footer;
	public long getTotal() {
		return total;
	}
	public List<Object> getRows() {
		return rows;
	}
	public List<Object> getFooter() {
		return footer;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	public void setFooter(List<Object> footer) {
		this.footer = footer;
	}
	
}
