package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "社保明细", schema = "")
public class ShebaoMX implements Serializable {
	private static final long serialVersionUID = 5726590306650892571L;
	private Integer id;
	private String username;
	private String idCard;
	private String email;
	private String cellCoreEmail;
	private String firstLevel;
	private String secondLevel;
	private String thirdLevel;
	private String fourthLevel;
	private String rubaoDate;
	private BigDecimal radix;
	private String company;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="姓名")
	public String getUsername() {
		return username;
	}
	@Column(name="身份证号码")
	public String getIdCard() {
		return idCard;
	}
	@Column(name="邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
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
	@Column(name="入保时间")
	public String getRubaoDate() {
		return rubaoDate;
	}
	@Column(name="基数")
	public BigDecimal getRadix() {
		return radix;
	}
	@Column(name="公司名称")
	public String getCompany() {
		return company;
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
	@Column(name="公司工伤保险")
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name="公司生育保险")
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
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
	public void setRubaoDate(String rubaoDate) {
		this.rubaoDate = rubaoDate;
	}
	public void setRadix(BigDecimal radix) {
		this.radix = radix;
	}
	public void setCompany(String company) {
		this.company = company;
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
	
	
}
