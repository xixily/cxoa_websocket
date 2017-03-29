package com.chaoxing.oa.entity.page.system;

public class PMenus_ {
//	public class PMenus_ implements Comparable<PMenus_>{
	private Integer menuId;
	private String menuName;
	private Integer menuLevel;
	private Integer _preMenuId;
	private String url;
	private String iconCls;
	private String sortCode;
	private Integer _parentId;
	private String state;
	
	public Integer getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public String getUrl() {
		return url;
	}

	public String getIconCls() {
		return iconCls;
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

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer get_parentId() {
		return _parentId;
	}

	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}

	public Integer get_preMenuId() {
		return _preMenuId;
	}

	public void set_preMenuId(Integer _preMenuId) {
		this._preMenuId = _preMenuId;
		this._parentId = _preMenuId;
	}



//	@Override
//	public int compareTo(PMenus_ o) {
//		return this.menuId.compareTo(o.getMenuId());
//	}
}
