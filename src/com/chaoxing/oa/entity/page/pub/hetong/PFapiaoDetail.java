package com.chaoxing.oa.entity.page.pub.hetong;

import java.math.BigDecimal;

public class PFapiaoDetail {
	private String charger;
	private Integer autoCode;
	private String huikuanTime;
	private String pinming;
	private BigDecimal huikuan;
	
	public String getCharger() {
		return charger;
	}
	public Integer getAutoCode() {
		return autoCode;
	}
	public String getHuikuanTime() {
		return huikuanTime;
	}
	public String getPinming() {
		return pinming;
	}
	public BigDecimal getHuikuan() {
		return huikuan;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public void setAutoCode(Integer autoCode) {
		this.autoCode = autoCode;
	}
	public void setHuikuanTime(String huikuanTime) {
		this.huikuanTime = huikuanTime;
	}
	public void setPinming(String pinming) {
		this.pinming = pinming;
	}
	public void setHuikuan(BigDecimal huikuan) {
		this.huikuan = huikuan;
	}
	
	
}
