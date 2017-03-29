package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "社保汇总", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SheBaoSummary implements Serializable {
	private static final long serialVersionUID = -7229522571583483206L;
	private String company;
	private Float subEndowmentIinsurance;
	private Float subMedicare;
	private Float subUnemployedInsurance;
	private Float subHouseIinsurance;
	private Float cEndowmentIinsurance;
	private Float cMedicare;
	private Float cUnemployedInsurance;
	private Float cHouseIinsurance;
	private Float cInjuryInsurance;
	private Float cBirthIinsurance;
	@Id
	@Column(name = "公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name = "代扣养老保险总额")
	public Float getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	@Column(name = "代扣医疗保险总额")
	public Float getSubMedicare() {
		return subMedicare;
	}
	@Column(name = "代扣失业保险总额")
	public Float getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	@Column(name = "代扣住房保险总额")
	public Float getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	@Column(name = "公司养老保险总额")
	public Float getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	@Column(name = "公司医疗保险总额")
	public Float getcMedicare() {
		return cMedicare;
	}
	@Column(name = "公司失业保险总额")
	public Float getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name = "公司住房保险总额")
	public Float getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	@Column(name = "公司工伤保险总额")
	public Float getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name = "公司生育保险总额")
	public Float getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setSubEndowmentIinsurance(Float subEndowmentIinsurance) {
		this.subEndowmentIinsurance = subEndowmentIinsurance;
	}
	public void setSubMedicare(Float subMedicare) {
		this.subMedicare = subMedicare;
	}
	public void setSubUnemployedInsurance(Float subUnemployedInsurance) {
		this.subUnemployedInsurance = subUnemployedInsurance;
	}
	public void setSubHouseIinsurance(Float subHouseIinsurance) {
		this.subHouseIinsurance = subHouseIinsurance;
	}
	public void setcEndowmentIinsurance(Float cEndowmentIinsurance) {
		this.cEndowmentIinsurance = cEndowmentIinsurance;
	}
	public void setcMedicare(Float cMedicare) {
		this.cMedicare = cMedicare;
	}
	public void setcUnemployedInsurance(Float cUnemployedInsurance) {
		this.cUnemployedInsurance = cUnemployedInsurance;
	}
	public void setcHouseIinsurance(Float cHouseIinsurance) {
		this.cHouseIinsurance = cHouseIinsurance;
	}
	public void setcInjuryInsurance(Float cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcBirthIinsurance(Float cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	
}
