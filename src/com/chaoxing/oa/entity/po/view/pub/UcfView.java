package com.chaoxing.oa.entity.po.view.pub;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class UcfView implements Serializable {
	private static final long serialVersionUID = 2889274087696931011L;
	private Integer id;//u id 
	private String charger;//负责人
	private String level;//级别
	private String email;//邮箱
	private String cellCore;//细胞核
	private String cemail;//细胞核邮箱
	private String guidance;//指导
	private String gemail;//指导邮箱
	private Integer autoCode;//自动编号
	private Integer did;//单位编号
	private String cname;//用户名称
	private String firstLevel;//公司
	private String secondLevel;//firstLevel
	private String thridLevel;
	private String fourthLevel;
	private String hsignedTime;//登记时间
	private String hpinming;//品名
	private BigDecimal hhmoney;//合同金额
	private String hfpStatus;//发票情况
	private BigDecimal hyear;//年代
	private BigDecimal hkaipiao;//开票总金额
	private String hluku;//录库人
	private Integer hCode;//合同编号
	private Integer fid;//序号
	private Date fkpTime;//开票时间
	private String ftype;//类型
	private String fppinming;//发票品名
	private BigDecimal fmoney;//金额
	private String fremarks;//发票备注
	private BigDecimal huikuan;//回款情况
	private String applicant;//申请人
	private Date huikuanTime;
	private String fluku;
	
	@Column(name="uid")
	public Integer getId() {
		return id;
	}
	@Column(name="负责人")
	public String getCharger() {
		return charger;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	@Column(name="邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name="细胞和邮箱")
	public String getCemail() {
		return cemail;
	}
	@Column(name="指导")
	public String getGuidance() {
		return guidance;
	}
	@Column(name="指导邮箱")
	public String getGemail() {
		return gemail;
	}
	@Id
	@Column(name="自动编号")
	public Integer getAutoCode() {
		return autoCode;
	}
	@Column(name="单位编号")
	public Integer getDid() {
		return did;
	}
	@Column(name="用户名称")
	public String getCname() {
		return cname;
	}
	@Column(name="公司")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name="d部门")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name="d岗位")
	public String getThridLevel() {
		return thridLevel;
	}
	@Column(name="d小组")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name="登记时间")
	public String getHsignedTime() {
		return hsignedTime;
	}
	@Column(name="品名")
	public String getHpinming() {
		return hpinming;
	}
	@Column(name="合同金额")
	public BigDecimal getHhmoney() {
		return hhmoney;
	}
	@Column(name="发票情况")
	public String getHfpStatus() {
		return hfpStatus;
	}
	@Column(name="年代")
	public BigDecimal getHyear() {
		return hyear;
	}
	@Column(name="开票总金额")
	public BigDecimal getHkaipiao() {
		return hkaipiao;
	}
	@Column(name="h录库人")
	public String getHluku() {
		return hluku;
	}
	@Column(name="合同编号")
	public Integer gethCode() {
		return hCode;
	}
	@Column(name="序号")
	public Integer getFid() {
		return fid;
	}
	@Column(name="开票时间")
	public Date getFkpTime() {
		return fkpTime;
	}
	@Column(name="发票类型")
	public String getFtype() {
		return ftype;
	}
	@Column(name="发票品名")
	public String getFppinming() {
		return fppinming;
	}
	@Column(name="金额")
	public BigDecimal getFmoney() {
		return fmoney;
	}
	@Column(name="发票备注")
	public String getFremarks() {
		return fremarks;
	}
	@Column(name="回款情况")
	public BigDecimal getHuikuan() {
		return huikuan;
	}
	@Column(name="申请人")
	public String getApplicant() {
		return applicant;
	}
	@Column(name="汇款时间")
	public Date getHuikuanTime() {
		return huikuanTime;
	}
	@Column(name="f录库人")
	public String getFluku() {
		return fluku;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	public void setGemail(String gemail) {
		this.gemail = gemail;
	}
	public void setAutoCode(Integer autoCode) {
		this.autoCode = autoCode;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThridLevel(String thridLevel) {
		this.thridLevel = thridLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setHsignedTime(String hsignedTime) {
		this.hsignedTime = hsignedTime;
	}
	public void setHpinming(String hpinming) {
		this.hpinming = hpinming;
	}
	public void setHhmoney(BigDecimal hhmoney) {
		this.hhmoney = hhmoney;
	}
	public void setHfpStatus(String hfpStatus) {
		this.hfpStatus = hfpStatus;
	}
	public void setHyear(BigDecimal hyear) {
		this.hyear = hyear;
	}
	public void setHkaipiao(BigDecimal hkaipiao) {
		this.hkaipiao = hkaipiao;
	}
	public void setHluku(String hluku) {
		this.hluku = hluku;
	}
	public void sethCode(Integer hCode) {
		this.hCode = hCode;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public void setFkpTime(Date fkpTime) {
		this.fkpTime = fkpTime;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public void setFppinming(String fppinming) {
		this.fppinming = fppinming;
	}
	public void setFmoney(BigDecimal fmoney) {
		this.fmoney = fmoney;
	}
	public void setFremarks(String fremarks) {
		this.fremarks = fremarks;
	}
	public void setHuikuan(BigDecimal huikuan) {
		this.huikuan = huikuan;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public void setHuikuanTime(Date huikuanTime) {
		this.huikuanTime = huikuanTime;
	}
	public void setFluku(String fluku) {
		this.fluku = fluku;
	}
	
}
