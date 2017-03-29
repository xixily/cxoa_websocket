package com.chaoxing.oa.entity.page.employee;

import java.math.BigDecimal;

public class PYidong {
	private Integer id;
	private String username;
	private String fourthLevel;
	private String position;//职位
	private String hiredate;//入职时间
	private String zhuanzhengTime;//转正时间
	private BigDecimal sySalary;//试用工资
	private BigDecimal zzSalary;//转正工资
	private String company;
	private String remark;
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private String dReport;//部门调整报表
	private String tiaoxinRecord;
	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getPosition() {
		return position;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public BigDecimal getSySalary() {
		return sySalary;
	}
	public BigDecimal getZzSalary() {
		return zzSalary;
	}
	public String getCompany() {
		return company;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	public void setSySalary(BigDecimal sySalary) {
		this.sySalary = sySalary;
	}
	public void setZzSalary(BigDecimal zzSalary) {
		this.zzSalary = zzSalary;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRuzhiReport() {
		return ruzhiReport;
	}
	public String getLizhiReport() {
		return lizhiReport;
	}
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	public void setRuzhiReport(String ruzhiReport) {
		this.ruzhiReport = ruzhiReport;
	}
	public void setLizhiReport(String lizhiReport) {
		this.lizhiReport = lizhiReport;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public String getTiaoxinRecord() {
		return tiaoxinRecord;
	}
	public void setTiaoxinRecord(String tiaoxinRecord) {
		this.tiaoxinRecord = tiaoxinRecord;
	}
	public String getdReport() {
		return dReport;
	}
	public void setdReport(String dReport) {
		this.dReport = dReport;
	}
	
	
}
