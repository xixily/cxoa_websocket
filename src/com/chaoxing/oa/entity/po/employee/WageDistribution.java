package com.chaoxing.oa.entity.po.employee;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * entity 职员工资分布表
 * @author Administrator
 *
 */
@Entity
@Table(name = "职员工资分布表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WageDistribution implements Serializable{
	private static final long serialVersionUID = 9141347037396584312L;
	private Integer id;//职员工资分布表id
	private Integer employeeId;//职员编号
//	private UserName userName;
	private String username;//姓名
	private Double salary;//工资总额
	private String identityCard;//身份证号码
	private Double secrecySubsidy;//保密补贴
	private Double communicationSubsidy;//通讯补贴
	private Double lunchSubsidy;//午餐补贴
	private String remarks;//备注
	private String company;//公司名称
	private Double radix;//基数
	private String householdType;//户口性质
	private Double basicWage;//基本工资
	private Double postSalary;//岗位工资
	private Double performanceRelatedPay;//绩效工资
	private String accountBank;//开户行
	private String account;//职工帐号
	private String salaryCode;//工资编号
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
	private Double cSickPayTotal;//累计带薪病假
	private Double annualLleave;//年假累计
	private String workPhone;//办公电话
	private Integer IntegerernalNumber;//内部编号
	private Double sickLleaveTotal;//病假累计
	private String rubaoTime;//入保时间
	private Double lishiSalary;//历史工资
	private String tiaoxinRecord;//调薪报表
	private String taxStructure;//报税架构
	private Integer countId;//统计架构
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "wagesTableGenerator", strategy = "native")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="职员编号")
	public Integer getEmployeeId() {
		return employeeId;
	}
	@Column(name="姓名")
	public String getUsername() {
		return username;
	}
	@Column(name="工资总额")
	public Double getSalary() {
		return salary;
	}
	
	@Column(name="身份证号码")
	public String getIdentityCard() {
		return identityCard;
	}
	@Column(name="保密补贴")
	public Double getSecrecySubsidy() {
		return secrecySubsidy;
	}
	@Column(name="通讯补贴")
	public Double getCommunicationSubsidy() {
		return communicationSubsidy;
	}
	@Column(name="午餐补贴")
	public Double getLunchSubsidy() {
		return lunchSubsidy;
	}
	@Column(name="备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name="公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name="基数")
	public Double getRadix() {
		return radix;
	}
	@Column(name="户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	@Column(name="基本工资")
	public Double getBasicWage() {
		return basicWage;
	}
	@Column(name="岗位工资")
	public Double getPostSalary() {
		return postSalary;
	}
	@Column(name="绩效工资")
	public Double getPerformanceRelatedPay() {
		return performanceRelatedPay;
	}
	@Column(name="开户行")
	public String getAccountBank() {
		return accountBank;
	}
	@Column(name="职工帐号")
	public String getAccount() {
		return account;
	}
	@Column(name="工资编号")
	public String getSalaryCode() {
		return salaryCode;
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
	@Column(name="公司失业保险")
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name="公司住房保险")
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	@Column(name="累计带薪病假")
	public Double getcSickPayTotal() {
		return cSickPayTotal;
	}
	@Column(name="公司工伤保险")
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name="公司生育保险")
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	@Column(name="年假累计")
	public Double getAnnualLleave() {
		return annualLleave;
	}
	@Column(name="办公电话")
	public String getWorkPhone() {
		return workPhone;
	}
	@Column(name="内部编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "paymentable_codeGenerator", strategy = "native")
	public Integer getInternalNumber() {
		return IntegerernalNumber;
	}
	@Column(name="病假累计")
	public Double getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	@Column(name = "入保时间")
	public String getRubaoTime() {
		return rubaoTime;
	}
	@Column(name = "历史工资")
	public Double getLishiSalary() {
		return lishiSalary;
	}
	@Column(name = "调薪报表")
	public String getTiaoxinRecord() {
		return tiaoxinRecord;
	}
	@Column(name="报税架构")
	public String getTaxStructure() {
		return taxStructure;
	}
	@Column(name="统计架构")
	public Integer getCountId() {
		return countId;
	}
	public void setTiaoxinRecord(String tiaoxinRecord) {
		this.tiaoxinRecord = tiaoxinRecord;
	}
	public void setLishiSalary(Double lishiSalary) {
		this.lishiSalary = lishiSalary;
	}
	public void setRubaoTime(String rubaoTime) {
		this.rubaoTime = rubaoTime;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setSecrecySubsidy(Double secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}
	public void setCommunicationSubsidy(Double communicationSubsidy) {
		this.communicationSubsidy = communicationSubsidy;
	}
	public void setLunchSubsidy(Double lunchSubsidy) {
		this.lunchSubsidy = lunchSubsidy;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public void setBasicWage(Double basicWage) {
		this.basicWage = basicWage;
	}
	public void setPostSalary(Double postSalary) {
		this.postSalary = postSalary;
	}
	public void setPerformanceRelatedPay(Double performanceRelatedPay) {
		this.performanceRelatedPay = performanceRelatedPay;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
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
	public void setcSickPayTotal(Double cSickPayTotal) {
		this.cSickPayTotal = cSickPayTotal;
	}
	public void setcInjuryInsurance(Double cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcBirthIinsurance(Double cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	public void setAnnualLleave(Double annualLleave) {
		this.annualLleave = annualLleave;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public void setInternalNumber(Integer IntegerernalNumber) {
		this.IntegerernalNumber = IntegerernalNumber;
	}
	public void setSickLleaveTotal(Double sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	}