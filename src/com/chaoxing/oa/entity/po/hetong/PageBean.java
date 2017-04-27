package com.chaoxing.oa.entity.po.hetong;

import java.util.List;
import java.util.Map;

/**
 * 分页帮助类
 * 
 */
public class PageBean<T> {

	private List<T> list; // 结果集：封装实体

	private List<Map<String, Object>> listMap;// 结果集：封装多表Map

	private int totalRows=0; // 总行数

	private int totalPages=0; // 总页数

	private int pageSize=10; // 每页显示行数

	private int currentPage; // 当前行数

	private boolean isFirstPage; // 是否第一页

	private boolean isLastPage; // 是否最后一页

	private boolean hasNextPage; // 是否有下一页

	private boolean hasPrePage; // 是否有上一页

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public List<Map<String, Object>> getListMap() {
		return listMap;
	}

	public void setListMap(List<Map<String, Object>> listMap) {
		this.listMap = listMap;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		this.totalPages = this.totalRows % this.pageSize == 0 ? this.totalRows / this.pageSize : this.totalRows / this.pageSize + 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFirstPage() {
		return this.currentPage == 1;
	}

	public boolean isLastPage() {
		return this.currentPage == this.totalPages;
	}

	public boolean isHasNextPage() {
		return this.currentPage != this.totalPages;
	}

	public boolean isHasPrePage() {
		return this.currentPage != 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= this.totalPages)
			this.currentPage = currentPage == 0 ? 1 : currentPage;
		else
			this.currentPage = this.totalPages;
	}

	public void init(List<T> pageList) {
		setList(pageList);
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPrePage = isHasPrePage();
		this.hasNextPage = isHasNextPage();
	}

	public void init(int paseSize, int totalRows, int currentPage) {
		setTotalRows(totalRows);
		setPageSize(paseSize);
		setTotalPages();
		setCurrentPage(currentPage);
	}

	public int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (this.currentPage - 1);
		if (offset < 0) {
			return 0;
		}
		return offset;
	}

}
