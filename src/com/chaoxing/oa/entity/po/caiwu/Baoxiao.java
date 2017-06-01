package com.chaoxing.oa.entity.po.caiwu;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "报销表", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Baoxiao implements Serializable{
	private static final long serialVersionUID = -8310260669695764416L;
	private Long id;
	private Integer uid;//报销人ID
	private Float money;//申报金额
	private Float huankuan;//还借款金额
	private Integer number;//报销张数
	private String explain;//说明
	private String bank;//银行
	private String account;//账号
	private Integer approid;//批准人ID
	private String approver;//批准人
	private String aproEmail;//批准人邮箱
	private Date aproTime;//批准时间
	private String approRemark;//领导意见
	private Date jtime;//邮寄时间
	private String kdno;//快递单号
	private Integer reciverId;//收票人Id
	private Date reciveTime;//收票时间
	private String rcRemarks;//收票备注
	private Integer checkerId;//审核人Id
	private Integer cpid;//出票人id
	private Float tuipiao;//退票金额
	private String caiwuRemarks;//财务备注
//	private Float koujk;//扣借款
	private Float baoxMoney;//报销金额
	private Float huikuan;//汇款金额
	private Date baoxTime;//汇款时间
	private Integer status;//状态
	private String kunhao;//捆号
	private Date createTime;//创建时间
	private Long cpNumber;//汇款批次号
	private Date cpTime;//出票时间
	private Float tuikuan;//退款金额，补借款金额
	private Float kouchu;//扣除金额
	private Date updateTime;//最近更新时间
	private Integer specifyId;//指定批准人id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column(name="报销人id")
	public Integer getUid() {
		return uid;
	}
	@Column(name="申报金额", columnDefinition="DECIMAL(12,2) ")
	public Float getMoney() {
		return money;
	}
	@Column(name="还借款金额", columnDefinition="DECIMAL(12,2) ")
	public Float getHuankuan() {
		return huankuan;
	}
	@Column(name="报销张数")
	public Integer getNumber() {
		return number;
	}
	@Column(name="说明")
	public String getExplain() {
		return explain;
	}
	@Column(name="银行")
	public String getBank() {
		return bank;
	}
	@Column(name="卡号")
	public String getAccount() {
		return account;
	}
	@Column(name="批准人id")
	public Integer getApproid() {
		return approid;
	}
	@Column(name="批准人")
	public String getApprover() {
		return approver;
	}
	@Column(name="批准人邮箱")
	public String getAproEmail() {
		return aproEmail;
	}
	@Column(name="批准时间")
	public Date getAproTime() {
		return aproTime;
	}
	@Column(name="领导意见")
	public String getApproRemark() {
		return approRemark;
	}
	@Column(name="邮寄时间")
	public Date getJtime() {
		return jtime;
	}
	@Column(name="快递单号")
	public String getKdno() {
		return kdno;
	}
	@Column(name="收票人id")
	public Integer getReciverId() {
		return reciverId;
	}
	@Column(name="收票时间")
	public Date getReciveTime() {
		return reciveTime;
	}
	@Column(name="收票备注")
	public String getRcRemarks() {
		return rcRemarks;
	}
	@Column(name="审核人id")
	public Integer getCheckerId() {
		return checkerId;
	}
	@Column(name="出票人id")
	public Integer getCpid() {
		return cpid;
	}
	@Column(name="退票金额", columnDefinition="DECIMAL(12,2) ")
	public Float getTuipiao() {
		return tuipiao;
	}
	@Column(name="财务备注")
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
//	@Column(name="扣除借款金额")
//	public Float getKoujk() {
//		return koujk;
//	}
	@Column(name="报销金额", columnDefinition="DECIMAL(12,2) ")
	public Float getBaoxMoney() {
		return baoxMoney;
	}
	@Column(name="汇款金额", columnDefinition="DECIMAL(12,2) ")
	public Float getHuikuan() {
		return huikuan;
	}
	@Column(name="汇款时间")
	public Date getBaoxTime() {
		return baoxTime;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	@Column(name="捆号")
	public String getKunhao() {
		return kunhao;
	}
	@Column(name="创建时间", insertable=false)
	public Date getCreateTime() {
		return createTime;
	}
	@Column
	public Long getCpNumber() {
		return cpNumber;
	}
	@Column(name="出票时间")
	public Date getCpTime() {
		return cpTime;
	}
	@Column(name="补借款金额", columnDefinition="DECIMAL(12,2) ")
	public Float getTuikuan() {
		return tuikuan;
	}
	@Column(name = "扣除金额", columnDefinition="DECIMAL(12,2) ")
	public Float getKouchu() {
		return kouchu;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateTime", columnDefinition=" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",insertable = false, updatable = false)
//	@Column(insertable = false, updatable = false)
	@org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
	public Date getUpdateTime() {
		return updateTime;
	}
	@Column(name = "specifyId")
	public Integer getSpecifyId() {
		return specifyId;
	}
	public void setSpecifyId(Integer specifyId) {
		this.specifyId = specifyId;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setKouchu(Float kouchu) {
		this.kouchu = kouchu;
	}
	public void setTuikuan(Float tuikuan) {
		this.tuikuan = tuikuan;
	}
	public void setCpTime(Date cpTime) {
		this.cpTime = cpTime;
	}
	public void setCpNumber(Long cpNumber) {
		this.cpNumber = cpNumber;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public void setHuankuan(Float huankuan) {
		this.huankuan = huankuan;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setApproid(Integer approid) {
		this.approid = approid;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public void setAproEmail(String aproEmail) {
		this.aproEmail = aproEmail;
	}
	public void setAproTime(Date aproTime) {
		this.aproTime = aproTime;
	}
	public void setApproRemark(String approRemark) {
		this.approRemark = approRemark;
	}
	public void setJtime(Date jtime) {
		this.jtime = jtime;
	}
	public void setKdno(String kdno) {
		this.kdno = kdno;
	}
	public void setReciverId(Integer reciverId) {
		this.reciverId = reciverId;
	}
	public void setReciveTime(Date reciveTime) {
		this.reciveTime = reciveTime;
	}
	public void setRcRemarks(String rcRemarks) {
		this.rcRemarks = rcRemarks;
	}
	public void setCheckerId(Integer checkerId) {
		this.checkerId = checkerId;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public void setTuipiao(Float tuipiao) {
		this.tuipiao = tuipiao;
	}
	public void setCaiwuRemarks(String caiwuRemarks) {
		this.caiwuRemarks = caiwuRemarks;
	}
//	public void setKoujk(Float koujk) {
//		this.koujk = koujk;
//	}
	public void setBaoxMoney(Float baoxMoney) {
		this.baoxMoney = baoxMoney;
	}
	public void setHuikuan(Float huikuan) {
		this.huikuan = huikuan;
	}
	public void setBaoxTime(Date baoxTime) {
		this.baoxTime = baoxTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setKunhao(String kunhao) {
		this.kunhao = kunhao;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Baoxiao() {
		super();
	}
	public Baoxiao(Long cpNumber) {
		super();
		this.cpNumber = cpNumber;
	}

}
