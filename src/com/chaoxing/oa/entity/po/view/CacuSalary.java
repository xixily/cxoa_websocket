package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "工资计算视图")
public class CacuSalary implements Serializable {
	private static final long serialVersionUID = -1960620605575652094L;
	private Integer id;//职员工资分布表的ID
	private Integer userId;
	private String username;
	private BigDecimal salary;//工资总额
	private BigDecimal lishiSalary;//历史工资
	private BigDecimal chuqinDay;//出勤天数
	private Integer zhuanzhengChaeDay;//转正天数
	private String zhuanzhengReport;//转正报表
	private BigDecimal shiJiaHour;//事假时数
	private BigDecimal bingJiaHour;//病假时数
	private BigDecimal chidaoYingkouDay;//迟到应扣天数
	private BigDecimal kuangGongHour;//旷工时数
	private BigDecimal sickLleaveTotal;//病假累计
	private BigDecimal annualLleave;//年假累计
	private Double subEndowmentIinsurance;//代扣养老保险
	private Double subMedicare;//代扣医疗保险
	private Double subUnemployedInsurance;//代扣失业保险
	private Double subHouseIinsurance;//代扣住房保险
	private Double cEndowmentIinsurance;//公司养老保险
	private Double cMedicare;//公司医疗保险
	private Double cBirthIinsurance;//公司生育保险
	private Double cUnemployedInsurance;//公司失业保险
	private Double cInjuryInsurance;//公司工伤保险
	private Double cHouseIinsurance;//公司住房保险
	private Double secrecySubsidy;//保密补贴
	private Double communicationSubsidy;//通讯补贴
	private Double lunchSubsidy;//午餐补贴
	
	@Id
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getUserId() {
		return userId;
	}
	@Column
	public String getUsername() {
		return username;
	}
	@Column(name="工资总额")
	public BigDecimal getSalary() {
		return salary;
	}
	@Column(name="历史工资")
	public BigDecimal getLishiSalary() {
		return lishiSalary;
	}
	@Column(name="出勤天数")
	public BigDecimal getChuqinDay() {
		return chuqinDay;
	}
	@Column(name="转正天数")
	public Integer getZhuanzhengChaeDay() {
		return zhuanzhengChaeDay;
	}
	@Column(name="转正报表")
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	@Column(name="事假小时数")
	public BigDecimal getShiJiaHour() {
		return shiJiaHour;
	}
	@Column(name="病假小时数")
	public BigDecimal getBingJiaHour() {
		return bingJiaHour;
	}
	@Column(name="迟到应扣天数")
	public BigDecimal getChidaoYingkouDay() {
		return chidaoYingkouDay;
	}
	@Column(name="旷工小时数")
	public BigDecimal getKuangGongHour() {
		return kuangGongHour;
	}
	@Column(name="病假累计")
	public BigDecimal getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	@Column(name="年假累计")
	public BigDecimal getAnnualLleave() {
		return annualLleave;
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
	@Column(name="公司生育保险")
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	@Column(name="公司失业保险")
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name="公司工伤保险")
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name="公司住房保险")
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
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
	public void setSecrecySubsidy(Double secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}
	public void setCommunicationSubsidy(Double communicationSubsidy) {
		this.communicationSubsidy = communicationSubsidy;
	}
	public void setLunchSubsidy(Double lunchSubsidy) {
		this.lunchSubsidy = lunchSubsidy;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public void setLishiSalary(BigDecimal lishiSalary) {
		this.lishiSalary = lishiSalary;
	}
	public void setChuqinDay(BigDecimal chuqinDay) {
		this.chuqinDay = chuqinDay;
	}
	public void setZhuanzhengChaeDay(Integer zhuanzhengChaeDay) {
		this.zhuanzhengChaeDay = zhuanzhengChaeDay;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public void setShiJiaHour(BigDecimal shiJiaHour) {
		this.shiJiaHour = shiJiaHour;
	}
	public void setBingJiaHour(BigDecimal bingJiaHour) {
		this.bingJiaHour = bingJiaHour;
	}
	public void setChidaoYingkouDay(BigDecimal chidaoYingkouDay) {
		this.chidaoYingkouDay = chidaoYingkouDay;
	}
	public void setKuangGongHour(BigDecimal kuangGongHour) {
		this.kuangGongHour = kuangGongHour;
	}
	public void setSickLleaveTotal(BigDecimal sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	public void setAnnualLleave(BigDecimal annualLleave) {
		this.annualLleave = annualLleave;
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
	public void setcBirthIinsurance(Double cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	public void setcUnemployedInsurance(Double cUnemployedInsurance) {
		this.cUnemployedInsurance = cUnemployedInsurance;
	}
	public void setcInjuryInsurance(Double cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcHouseIinsurance(Double cHouseIinsurance) {
		this.cHouseIinsurance = cHouseIinsurance;
	}
	
}
