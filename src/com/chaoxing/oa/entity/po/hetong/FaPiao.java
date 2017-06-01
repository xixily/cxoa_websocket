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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="发票情况")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FaPiao implements Serializable{
	private static final long serialVersionUID = 6234792052500321106L;
	private Integer id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;//开票时间
	private String company;//开票公司
	private String departMement;//开票单位(客户)
	private String type;//发票类型
	private String name;//发票品名
	private BigDecimal money;//发票金额
	private String remark;//备注
	private Integer hetongNumber;//合同编号
	private BigDecimal huiKuan;//回款情况
	private String capitalMoney;//大写金额
	private String Applicant;//申请人(舍弃) 负责人
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date remittanceDate;//汇款时间
	private Integer queryStatus; //查询情况
	private String fundType; //资金类型
	private String account;//账户
	private String recorder;//录库人
	//yang add
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date receivedpaymentsdate;// 回款日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date applicationDate;//申请时间
	private String caiwuMonth;//财务月份
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yujihuikuanDate;//开票预计回款时间
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
	@Column(name = "回款日期")
	public Date getReceivedpaymentsdate() {
		return receivedpaymentsdate;
	}
	public void setReceivedpaymentsdate(Date receivedpaymentsdate) {
		this.receivedpaymentsdate = receivedpaymentsdate;
	}
	@Column(name = "申请时间")
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	@Column(name = "财务月份")
	public String getCaiwuMonth() {
		return caiwuMonth;
	}
	public void setCaiwuMonth(String caiwuMonth) {
		this.caiwuMonth = caiwuMonth;
	}
	@Column(name = "开票预计回款时间")
	public Date getYujihuikuanDate() {
		return yujihuikuanDate;
	}
	public void setYujihuikuanDate(Date yujihuikuanDate) {
		this.yujihuikuanDate = yujihuikuanDate;
	}
	
	
	
}
