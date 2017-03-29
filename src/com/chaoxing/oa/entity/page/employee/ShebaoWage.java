package com.chaoxing.oa.entity.page.employee;

public class ShebaoWage {
	private Integer id;//职员工资分布表id
	private Double radix;//基数
	private String company;//公司名称
	private String householdType;//户口性质
	private String rubaoTime;//入保时间
	
	public Integer getId() {
		return id;
	}
	public Double getRadix() {
		return radix;
	}
	public String getCompany() {
		return company;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public String getRubaoTime() {
		return rubaoTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setRadix(Double radix) {
		this.radix = radix;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setRubaoTime(String rubaoTime) {
		this.rubaoTime = rubaoTime;
	}
	
	
	
}
