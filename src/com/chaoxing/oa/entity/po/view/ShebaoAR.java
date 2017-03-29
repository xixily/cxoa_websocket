package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "社保增减员表", schema = "")
public class ShebaoAR implements Serializable {
	private static final long serialVersionUID = -9154580607969483483L;
	private Integer id;
	private String username;
	private String firstLevel;
	private String secondLevel;
	private String thirdLevel;
	private String fourthLevel;
	private String position;
	private String signedDate;
	private String terminateDate;
	private String sex;
	private String nation;
	private String householdType;
	private String householdAddress;
	private String homeAddress;//家庭住址
	private String degree;
	private String postCode;
	private String insurance;
	private String insuranceCompany;
	private String company;
	private Long probationalSalary;
	private String telNum;
	private String lizhiReport;
	private String lizhiDate;
	private String rubaoDate;
	private BigDecimal radix;
	private String bank;
	private String account;
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
	private BigDecimal salary;
	private String ifSecret;
	private String idCard;
	private String dueSocialSecurity;
	private String hiredate;//入职时间
	private String ruzhiReport;//入职报表
	private String zhuanzhengReport;//转正报表
	private String zhuanzhengDate;//转正报表
	private String remarks;//备注
	private String bumentiaozhengReport;//部门调整报表
	private String s_remarks;//涨薪记录
	
	@Id
	@Column
	public Integer getId() {
		return id;
	}
	@Column
	public String getUsername() {
		return username;
	}
	@Column(name="一级")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name="二级")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name="三级")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name="四级")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name="职位")
	public String getPosition() {
		return position;
	}
	@Column(name="签定时间")
	public String getSignedDate() {
		return signedDate;
	}
	@Column(name="终止时间")
	public String getTerminateDate() {
		return terminateDate;
	}
	@Column(name="性别")
	public String getSex() {
		return sex;
	}
	@Column(name="民族")
	public String getNation() {
		return nation;
	}
	@Column(name="户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	@Column(name="户口地址")
	public String getHouseholdAddress() {
		return householdAddress;
	}
	@Column(name="家庭地址")
	public String getHomeAddress() {
		return homeAddress;
	}
	@Column(name="学历")
	public String getDegree() {
		return degree;
	}
	@Column(name="邮编")
	public String getPostCode() {
		return postCode;
	}
	@Column(name="保险")
	public String getInsurance() {
		return insurance;
	}
	@Column(name="保险公司")
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	@Column(name="公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name="转正钱工资")
	public Long getProbationalSalary() {
		return probationalSalary;
	}
	@Column(name="联系电话")
	public String getTelNum() {
		return telNum;
	}
	@Column(name="离职报表")
	public String getLizhiReport() {
		return lizhiReport;
	}
	@Column(name="离职时间")
	public String getLizhiDate() {
		return lizhiDate;
	}
	@Column(name="入保时间")
	public String getRubaoDate() {
		return rubaoDate;
	}
	@Column(name="基数")
	public BigDecimal getRadix() {
		return radix;
	}
	@Column(name="开户行")
	public String getBank() {
		return bank;
	}
	@Column(name="职工帐号")
	public String getAccount() {
		return account;
	}
	@Column(name="代扣养老保险")
	public Double getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	@Column(name="代扣医疗保险")
	public Double getSubMedicare() {
		return subMedicare;
	}
	@Column(name="代扣失业保险")
	public Double getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	@Column(name="代扣住房保险")
	public Double getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	@Column(name="公司养老保险")
	public Double getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	@Column(name="公司医疗保险")
	public Double getcMedicare() {
		return cMedicare;
	}
	@Column(name="公司工伤保险")
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name="公司住房保险")
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	@Column(name="公司失业保险")
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name="公司生育保险")
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	@Column(name="工资总额")
	public BigDecimal getSalary() {
		return salary;
	}
	@Column(name="工资保密")
	public String getIfSecret() {
		return ifSecret;
	}
	@Column(name="身份证号码")
	public String getIdCard() {
		return idCard;
	}
	@Column(name="计划入保时间")
	public String getDueSocialSecurity() {
		return dueSocialSecurity;
	}
	@Column(name="入职时间")
	public String getHiredate() {
		return hiredate;
	}
	@Column(name="入职报表")
	public String getRuzhiReport() {
		return ruzhiReport;
	}
	@Column(name="转正报表")
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	@Column(name="备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name="部门调整报表")
	public String getBumentiaozhengReport() {
		return bumentiaozhengReport;
	}
	@Column(name="转正时间")
	public String getZhuanzhengDate() {
		return zhuanzhengDate;
	}
	@Column(name="涨薪记录")
	public String getS_remarks() {
		return s_remarks;
	}
	public void setS_remarks(String s_remarks) {
		this.s_remarks = s_remarks;
	}
	public void setZhuanzhengDate(String zhuanzhengDate) {
		this.zhuanzhengDate = zhuanzhengDate;
	}
	public void setBumentiaozhengReport(String bumentiaozhengReport) {
		this.bumentiaozhengReport = bumentiaozhengReport;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setRuzhiReport(String ruzhiReport) {
		this.ruzhiReport = ruzhiReport;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setDueSocialSecurity(String dueSocialSecurity) {
		this.dueSocialSecurity = dueSocialSecurity;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}
	public void setTerminateDate(String terminateDate) {
		this.terminateDate = terminateDate;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setProbationalSalary(Long probationalSalary) {
		this.probationalSalary = probationalSalary;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public void setLizhiReport(String lizhiReport) {
		this.lizhiReport = lizhiReport;
	}
	public void setLizhiDate(String lizhiDate) {
		this.lizhiDate = lizhiDate;
	}
	public void setRubaoDate(String rubaoDate) {
		this.rubaoDate = rubaoDate;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setRadix(BigDecimal radix) {
		this.radix = radix;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

}
