package com.chaoxing.oa.entity.page.employee;

public class PgridWage {
	private Integer id;//职员工资分布表id
//	private Double salary;//工资总额
	private String identityCard;//身份证号码
	private String company;//公司名称
	private String accountBank;//开户行
	private String account;//职工帐号
	private String householdType;//户口性质
	private String rubaoTime;//计划入保时间
	private String taxStructure;//报税架构
	private Integer countId;//统计架构
	
	public Integer getId() {
		return id;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public String getCompany() {
		return company;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public String getAccount() {
		return account;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public String getRubaoTime() {
		return rubaoTime;
	}
	public String getTaxStructure() {
		return taxStructure;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setRubaoTime(String rubaoTime) {
		this.rubaoTime = rubaoTime;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public Integer getCountId() {
		return countId;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	
	
}
