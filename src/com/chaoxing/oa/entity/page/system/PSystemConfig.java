package com.chaoxing.oa.entity.page.system;

public class PSystemConfig {
//	private int id;
	private String name;
	private byte locked;
//	private String locked;
	private String configType;
	
//	public int getId() {
//		return id;
//	}
	public String getName() {
		return name;
	}
	public byte getLocked() {
		return locked;
	}
	public void setLocked(byte locked) {
		this.locked = locked;
	}
	public String getConfigType() {
		return configType;
	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public void setName(String name) {
		this.name = name;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	
}
