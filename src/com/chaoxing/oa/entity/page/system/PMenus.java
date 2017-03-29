package com.chaoxing.oa.entity.page.system;

import java.util.Set;
import java.util.TreeSet;

public class PMenus implements Comparable<PMenus>{
	private Integer menuId;
	private String menuName;
	private int menuLevel;
	private int _preMenuId;
	private String url;
	private String iconCls;
	private String sortCode;
	private Set<PMenus> children = new TreeSet<PMenus>();
	
	public Integer getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public int get_preMenuId() {
		return _preMenuId;
	}

	public String getUrl() {
		return url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Set<PMenus> getChildren() {
		return children;
	}
	
	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void set_preMenuId(int _preMenuId) {
		this._preMenuId = _preMenuId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setChildren(Set<PMenus> children) {
		this.children = children;
	}

	@Override
	public int compareTo(PMenus o) {
		return this.menuId.compareTo(o.getMenuId());
	}
}
