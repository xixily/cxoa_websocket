package com.chaoxing.oa.entity.po.view.pub.caiwu;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "报销视图")
public class BaoxiaoView implements Serializable{
	private static final long serialVersionUID = 653847779415412260L;
	private Long id;
	private Integer uid;//报销人ID
	private String username;//报销人
	private String email;//报销人邮箱
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
	private Float koujk;//扣借款
	private Float baoxMoney;//汇款金额
	private Float huikuan;//汇款金额
	private Date baoxTime;//汇款时间
	private Integer status;//状态
	private String kunhao;//捆号
	private Date createTime;//创建时间
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String cellCoreEmail;//细胞核邮箱
	private String guidanceEmail;//指导邮箱
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column(name="报销人id")
	public Integer getUid() {
		return uid;
	}
	@Column(name="报销人")
	public String getUsername() {
		return username;
	}
	@Column(name="邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name="申报金额")
	public Float getMoney() {
		return money;
	}
	@Column(name="还借款金额")
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
	@Column(name="退票金额")
	public Float getTuipiao() {
		return tuipiao;
	}
	@Column(name="财务备注")
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
	@Column(name="扣借款总额")
	public Float getKoujk() {
		return koujk;
	}
	@Column(name="报销金额")
	public Float getBaoxMoney() {
		return baoxMoney;
	}
	@Column(name="汇款金额")
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
	@Column(name="创建时间")
	public Date getCreateTime() {
		return createTime;
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
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	@Column(name="指导邮箱")
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setKoujk(Float koujk) {
		this.koujk = koujk;
	}
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
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}

}
