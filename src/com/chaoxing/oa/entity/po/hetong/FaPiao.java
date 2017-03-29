package com.chaoxing.oa.entity.po.hetong;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="发票情况")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FaPiao implements Serializable{
	private static final long serialVersionUID = 6234792052500321106L;
	private Integer id;
	private Date date;
	private String company;
	private String departMement;
	private String type;
	private String name;
	private BigDecimal money;
	private String remark;
	private Integer hetongNumber;
	private BigDecimal huiKuan;
	private String capitalMoney;
	private String Applicant;
	private Date remittanceDate;
	private Integer queryStatus; 
	private String fundType; 
	private String account;
	private String recorder;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "序号")
	public Integer getId() {
		return id;
	}
	@Column(name = "开票时间")
	public Date getDate() {
		return date;
	}
	@Column(name = "开票公司")
	public String getCompany() {
		return company;
	}
	@Column(name = "开票单位")
	public String getDepartMement() {
		return departMement;
	}
	@Column(name = "发票类型")
	public String getType() {
		return type;
	}
	@Column(name = "发票品名")
	public String getName() {
		return name;
	}
	@Column(name = "金额")
	public BigDecimal getMoney() {
		return money;
	}
	@Column(name = "备注")
	public String getRemark() {
		return remark;
	}
	@Column(name = "合同编号")
	public Integer getHetongNumber() {
		return hetongNumber;
	}
	@Column(name = "回款情况")
	public BigDecimal getHuiKuan() {
		return huiKuan;
	}
	@Column(name = "大写金额")
	public String getCapitalMoney() {
		return capitalMoney;
	}
	@Column(name = "申请人")
	public String getApplicant() {
		return Applicant;
	}
	@Column(name = "汇款时间")
	public Date getRemittanceDate() {
		return remittanceDate;
	}
	@Column(name = "查询情况")
	public Integer getQueryStatus() {
		return queryStatus;
	}
	@Column(name = "资金类型")
	public String getFundType() {
		return fundType;
	}
	@Column(name = "账户")
	public String getAccount() {
		return account;
	}
	@Column(name = "录库人")
	public String getRecorder() {
		return recorder;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setDepartMement(String departMement) {
		this.departMement = departMement;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setHetongNumber(Integer hetongNumber) {
		this.hetongNumber = hetongNumber;
	}
	public void setHuiKuan(BigDecimal huiKuan) {
		this.huiKuan = huiKuan;
	}
	public void setCapitalMoney(String capitalMoney) {
		this.capitalMoney = capitalMoney;
	}
	public void setApplicant(String applicant) {
		Applicant = applicant;
	}
	public void setRemittanceDate(Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}
	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	
}
