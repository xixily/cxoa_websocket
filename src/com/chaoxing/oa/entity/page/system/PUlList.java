package com.chaoxing.oa.entity.page.system;


public class PUlList implements Comparable<PUlList>{
	private String text;//li名字
	private String url;//tab页
	private String iconCls = "icon-man";//设置图标
	private int preMenuId;//前置menu
	private Integer domId;//映射menuid
	private String sortCode;
	/*private List<UlList> children;*/
//	private String state;//ul的开启状态
	public int getPreMenuId() {
		return preMenuId;
	}
	public int getDomId() {
		return domId;
	}
	public String getText() {
		return text;
	}
	public String getUrl() {
		return url;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public void setPreMenuId(int preMenuId) {
		this.preMenuId = preMenuId;
	}
	public void setDomId(int domId) {
		this.domId = domId;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	@Override
	public int compareTo(PUlList o) {
		if(this.sortCode!=null && o.getSortCode()!= null){
			return this.sortCode.compareTo(o.getSortCode());
		}else{
			return -1;
		}
//		return this.domId.compareTo(o.getDomId());
//		return this.text.compareTo(o.getText());
	}
	
}
