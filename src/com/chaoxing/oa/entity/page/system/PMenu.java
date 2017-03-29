package com.chaoxing.oa.entity.page.system;
import java.util.Set;
import java.util.TreeSet;

public class PMenu implements Comparable<PMenu>{
	private int userId;
	private int menuId;
	private String menuName;
	private Set<PUlList> uls = new TreeSet<PUlList>();//treeSet有序的
	private String sortCode;
	public int getUserId() {
		return userId;
	}
	public int getMenuId() {
		return menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Set<PUlList> getUls() {
		return uls;
	}
	public void setUls(Set<PUlList> uls) {
		this.uls = uls;
	}
	@Override
	public String toString() {
		return "MenuInfo [userId=" + userId + ", menuId=" + menuId + ", menuName=" + menuName + ", uls=" + uls + "]";
	}
	@Override
	public int compareTo(PMenu o) {
		if(this.sortCode!=null&&o.getSortCode()!=null){
			return this.sortCode.compareTo(o.getSortCode());
		}
		return 1;
	}
	
}
