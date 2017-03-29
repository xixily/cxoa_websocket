package com.chaoxing.oa.entity.po.employee;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MonthSalary implements Serializable{
	private static final long serialVersionUID = -7860670702121925014L;
	private Integer id;
	private Integer userId;
	private BigDecimal kaoqinTotal;//考勤总额--
	private BigDecimal zhuanzChae;//转正差额 -- 
	private BigDecimal subTotal;//代扣五险总额 --
	private BigDecimal yingfaTotal;//应发总额--
	private BigDecimal shifaTotal;//实发总额--
	private BigDecimal cTotal;//公司五险总额 --
	private BigDecimal selfTax;//个人所得税--
	private Double fakuan=0.0d;//罚款
	private Double jiangjin=0d;//奖金
	private Double bufaSalary=0d;//补发工资
	private Double qita=0d;//其他扣款
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getUserId() {
		return userId;
	}
	@Column(name="考勤总额")
	public BigDecimal getKaoqinTotal() {
		return kaoqinTotal;
	}
	@Column(name="转正差额")
	public BigDecimal getZhuanzChae() {
		return zhuanzChae;
	}
	@Column(name="代扣五险总额")
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	@Column(name="应发总额")
	public BigDecimal getYingfaTotal() {
		return yingfaTotal;
	}
	@Column(name="实发总额")
	public BigDecimal getShifaTotal() {
		return shifaTotal;
	}
	@Column(name="公司五险总额")
	public BigDecimal getcTotal() {
		return cTotal;
	}
	@Column(name="个人所得税")
	public BigDecimal getSelfTax() {
		return selfTax;
	}
	@Column(name="罚款")
	public Double getFakuan() {
		return fakuan;
	}
	@Column(name="奖金")
	public Double getJiangjin() {
		return jiangjin;
	}
	@Column(name="补发工资")
	public Double getBufaSalary() {
		return bufaSalary;
	}
	@Column(name="其他扣款")
	public Double getQita() {
		return qita;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setKaoqinTotal(BigDecimal kaoqinTotal) {
		this.kaoqinTotal = kaoqinTotal;
	}
	public void setZhuanzChae(BigDecimal zhuanzChae) {
		this.zhuanzChae = zhuanzChae;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public void setYingfaTotal(BigDecimal yingfaTotal) {
		this.yingfaTotal = yingfaTotal;
	}
	public void setShifaTotal(BigDecimal shifaTotal) {
		this.shifaTotal = shifaTotal;
	}
	public void setcTotal(BigDecimal cTotal) {
		this.cTotal = cTotal;
	}
	public void setSelfTax(BigDecimal selfTax) {
		this.selfTax = selfTax;
	}
	public void setFakuan(Double fakuan) {
		this.fakuan = fakuan;
	}
	public void setJiangjin(Double jiangjin) {
		this.jiangjin = jiangjin;
	}
	public void setBufaSalary(Double bufaSalary) {
		this.bufaSalary = bufaSalary;
	}
	public void setQita(Double qita) {
		this.qita = qita;
	}
	

}
