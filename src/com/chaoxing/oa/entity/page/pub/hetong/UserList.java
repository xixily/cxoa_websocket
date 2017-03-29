package com.chaoxing.oa.entity.page.pub.hetong;

import java.math.BigDecimal;

public class UserList {
	private Integer autoCode;//自动编号
	private Integer dwCode;//单位编号
	private Integer chargerId;//负责人ID
	private String dname;//单位名称
	private String charger;//负责人
	private String email;//邮箱
	private String cemail;//细胞核邮箱
	private BigDecimal thisYear;
	private BigDecimal lastYear;
	private BigDecimal yingshou;
	
	public UserList() {
		super();
	}
	
	public UserList(Integer autoCode) {
		super();
		this.autoCode = autoCode;
	}
	public Integer getAutoCode() {
		return autoCode;
	}
	public Integer getDwCode() {
		return dwCode;
	}
	public Integer getChargerId() {
		return chargerId;
	}
	public String getDname() {
		return dname;
	}
	public String getCharger() {
		return charger;
	}
	public String getEmail() {
		return email;
	}
	public String getCemail() {
		return cemail;
	}
	public BigDecimal getThisYear() {
		return thisYear;
	}
	public BigDecimal getLastYear() {
		return lastYear;
	}
	public BigDecimal getYingshou() {
		return yingshou;
	}
	public void setAutoCode(Integer autoCode) {
		this.autoCode = autoCode;
	}
	public void setDwCode(Integer dwCode) {
		this.dwCode = dwCode;
	}
	public void setChargerId(Integer chargerId) {
		this.chargerId = chargerId;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public void setThisYear(BigDecimal thisYear) {
		this.thisYear = thisYear;
	}
	public void setLastYear(BigDecimal lastYear) {
		this.lastYear = lastYear;
	}
	public void setYingshou(BigDecimal yingshou) {
		this.yingshou = yingshou;
	}
	
}
