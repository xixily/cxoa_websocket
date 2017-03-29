package com.chaoxing.oa.entity.page.SF;

public class AddedService {
	private String name;//增值服务名，如 COD 等。 
	private String value;//增值服务扩展属性，参考增值服务传 值说明。 
	private String value1;//增值服务扩展属性 
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public String getValue1() {
		return value1;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
}
