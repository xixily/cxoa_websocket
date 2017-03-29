package com.chaoxing.oa.entity.page.hetong;

public class PCompanyInfo {
	public final static String DEFAULT_ADDRESS = "北京海淀上地三街9号嘉华大厦C座1212";
	public final static String DACHENG_ADDRESS = "北京市海淀区上地佳园23号楼906室";
	public final static String DEFAULT_TEL = "010-62962266";
	private Integer id;
	private String name;
	private String address;
	private String telNumber;
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
}
