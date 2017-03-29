package com.chaoxing.oa.entity.page.employee;

/**
 * @author dengxf
 * 社保详细信息，对于只更新社保字段设置的实体
 */
public class PshebaoDetail {
	private Integer id;//职员工资分布表id
	private Integer employeeId;//职员编号
	private String username;//姓名
	private String identityCard;//身份证号码
	private String company;//公司名称
	private Double radix;//基数
	private String householdType;//户口性质
	private Double subEndowmentIinsurance;//代扣养老保险
	private Double subMedicare;//代扣医疗保险
	private Double subUnemployedInsurance;//代扣失业保险
	private Double subHouseIinsurance;//代扣住房保险
	private Double cEndowmentIinsurance;//公司养老保险
	private Double cMedicare;//公司医疗保险
	private Double cUnemployedInsurance;//公司失业保险
	private Double cHouseIinsurance;//公司住房保险
	private Double cInjuryInsurance;//公司工伤保险
	private Double cBirthIinsurance;//公司生育保险
	private String rubaoTime;//入保时间
	public Integer getId() {
		return id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getCompany() {
		return company;
	}
	public Double getRadix() {
		return radix;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public Double getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	public Double getSubMedicare() {
		return subMedicare;
	}
	public Double getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	public Double getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	public Double getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	public Double getcMedicare() {
		return cMedicare;
	}
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	public String getRubaoTime() {
		return rubaoTime;
	}
	public String getUsername() {
		return username;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setRadix(Double radix) {
		this.radix = radix;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setSubEndowmentIinsurance(Double subEndowmentIinsurance) {
		this.subEndowmentIinsurance = subEndowmentIinsurance;
	}
	public void setSubMedicare(Double subMedicare) {
		this.subMedicare = subMedicare;
	}
	public void setSubUnemployedInsurance(Double subUnemployedInsurance) {
		this.subUnemployedInsurance = subUnemployedInsurance;
	}
	public void setSubHouseIinsurance(Double subHouseIinsurance) {
		this.subHouseIinsurance = subHouseIinsurance;
	}
	public void setcEndowmentIinsurance(Double cEndowmentIinsurance) {
		this.cEndowmentIinsurance = cEndowmentIinsurance;
	}
	public void setcMedicare(Double cMedicare) {
		this.cMedicare = cMedicare;
	}
	public void setcUnemployedInsurance(Double cUnemployedInsurance) {
		this.cUnemployedInsurance = cUnemployedInsurance;
	}
	public void setcHouseIinsurance(Double cHouseIinsurance) {
		this.cHouseIinsurance = cHouseIinsurance;
	}
	public void setcInjuryInsurance(Double cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcBirthIinsurance(Double cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	public void setRubaoTime(String rubaoTime) {
		this.rubaoTime = rubaoTime;
	}
	
}
