//package com.chaoxing.oa.entity.po.view.pub;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "用户合同发票")
//public class FaPiaoDetail {
//	private Integer id;
//	private String username;
//	private String level;
//	private String cellCore;
//	private String cemail;
//	private String guidance;
//	private String gemail;
//	private Integer cid;//用户单位列表ID
//	private Integer did;//用户单位编号
//	private String cname;
//	private String firstLevel;
//	private String secondLevel;
//	private String thirdLevel;
//	private String fourthLevel;
//	private String guidangCode;//归档编号
//	private String pingming;//品名
//	private Float contractMoney;//合同金额
//	private String fapiaoStatus;//发票情况
//	private Double year;//年代
//	private Double kaipiaoMoney;//开票总金额
//	private String luku;//录库人
//	private Integer hid;//合同编号
//	private Integer fid;
//	private Date kpdate;//开票时间
//	private String kpcompany;
//	private String kpdepartMement;
//	private String fptype;
//	private String fpname;
//	private Double fpmoney;
//	private String fpremark;
//	private Double huiKuan;
//	private String applicant;//申请人
//	private String remittanceDate;//汇款时间
//	private String recorder;//录库人
//	
//	@Column(name="uid")
//	public Integer getId() {
//		return id;
//	}
//	@Column(name="username")
//	public String getUsername() {
//		return username;
//	}
//	@Column(name="级别")
//	public String getLevel() {
//		return level;
//	}
//	@Column(name="细胞核")
//	public String getCellCore() {
//		return cellCore;
//	}
//	@Column(name="细胞核邮箱")
//	public String getCemail() {
//		return cemail;
//	}
//	@Column(name="指导")
//	public String getGuidance() {
//		return guidance;
//	}
//	@Column(name="指导邮箱")
//	public String getGemail() {
//		return gemail;
//	}
//	@Column(name="自动编号")
//	public Integer getCid() {
//		return cid;
//	}
//	@Column(name="单位编号")
//	public Integer getDid() {
//		return did;
//	}
//	@Column(name="用户名称")
//	public String getCname() {
//		return cname;
//	}
//	@Column(name="公司")
//	public String getFirstLevel() {
//		return firstLevel;
//	}
//	@Column(name="d部门")
//	public String getSecondLevel() {
//		return secondLevel;
//	}
//	@Column(name="d岗位")
//	public String getThirdLevel() {
//		return thirdLevel;
//	}
//	@Column(name="d小组")
//	public String getFourthLevel() {
//		return fourthLevel;
//	}
//	@Column(name="归档编号")
//	public String getGuidangCode() {
//		return guidangCode;
//	}
//	@Column(name="品名")
//	public String getPingming() {
//		return pingming;
//	}
//	@Column(name="合同金额")
//	public Float getContractMoney() {
//		return contractMoney;
//	}
//	@Column(name="发票情况")
//	public String getFapiaoStatus() {
//		return fapiaoStatus;
//	}
//	@Column(name="年代")
//	public Double getYear() {
//		return year;
//	}
//	@Column(name="开票总金额")
//	public Double getKaipiaoMoney() {
//		return kaipiaoMoney;
//	}
//	@Column(name="h录库人")
//	public String getLuku() {
//		return luku;
//	}
//	@Column(name="合同编号")
//	public Integer getHid() {
//		return hid;
//	}
//	@Id
//	@Column(name="序号")
//	public Integer getFid() {
//		return fid;
//	}
//	@Column(name="开票时间")
//	public Date getKpdate() {
//		return kpdate;
//	}
//	@Column(name="开票公司")
//	public String getKpcompany() {
//		return kpcompany;
//	}
//	@Column(name="开票单位")
//	public String getKpdepartMement() {
//		return kpdepartMement;
//	}
//	@Column(name="发票类型")
//	public String getFptype() {
//		return fptype;
//	}
//	@Column(name="发票品名")
//	public String getFpname() {
//		return fpname;
//	}
//	@Column(name="金额")
//	public Double getFpmoney() {
//		return fpmoney;
//	}
//	@Column(name="发票备注")
//	public String getFpremark() {
//		return fpremark;
//	}
//	@Column(name="回款情况")
//	public Double getHuiKuan() {
//		return huiKuan;
//	}
//	@Column(name="申请人")
//	public String getApplicant() {
//		return applicant;
//	}
//	@Column(name="汇款时间")
//	public String getRemittanceDate() {
//		return remittanceDate;
//	}
//	@Column(name="f录库人")
//	public String getRecorder() {
//		return recorder;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public void setLevel(String level) {
//		this.level = level;
//	}
//	public void setCellCore(String cellCore) {
//		this.cellCore = cellCore;
//	}
//	public void setCemail(String cemail) {
//		this.cemail = cemail;
//	}
//	public void setGuidance(String guidance) {
//		this.guidance = guidance;
//	}
//	public void setGemail(String gemail) {
//		this.gemail = gemail;
//	}
//	public void setCid(Integer cid) {
//		this.cid = cid;
//	}
//	public void setDid(Integer did) {
//		this.did = did;
//	}
//	public void setCname(String cname) {
//		this.cname = cname;
//	}
//	public void setFirstLevel(String firstLevel) {
//		this.firstLevel = firstLevel;
//	}
//	public void setSecondLevel(String secondLevel) {
//		this.secondLevel = secondLevel;
//	}
//	public void setThirdLevel(String thirdLevel) {
//		this.thirdLevel = thirdLevel;
//	}
//	public void setFourthLevel(String fourthLevel) {
//		this.fourthLevel = fourthLevel;
//	}
//	public void setGuidangCode(String guidangCode) {
//		this.guidangCode = guidangCode;
//	}
//	public void setPingming(String pingming) {
//		this.pingming = pingming;
//	}
//	public void setContractMoney(Float contractMoney) {
//		this.contractMoney = contractMoney;
//	}
//	public void setFapiaoStatus(String fapiaoStatus) {
//		this.fapiaoStatus = fapiaoStatus;
//	}
//	public void setYear(Double year) {
//		this.year = year;
//	}
//	public void setKaipiaoMoney(Double kaipiaoMoney) {
//		this.kaipiaoMoney = kaipiaoMoney;
//	}
//	public void setLuku(String luku) {
//		this.luku = luku;
//	}
//	public void setHid(Integer hid) {
//		this.hid = hid;
//	}
//	public void setFid(Integer fid) {
//		this.fid = fid;
//	}
//	public void setKpdate(Date kpdate) {
//		this.kpdate = kpdate;
//	}
//	public void setKpcompany(String kpcompany) {
//		this.kpcompany = kpcompany;
//	}
//	public void setKpdepartMement(String kpdepartMement) {
//		this.kpdepartMement = kpdepartMement;
//	}
//	public void setFptype(String fptype) {
//		this.fptype = fptype;
//	}
//	public void setFpname(String fpname) {
//		this.fpname = fpname;
//	}
//	public void setFpmoney(Double fpmoney) {
//		this.fpmoney = fpmoney;
//	}
//	public void setFpremark(String fpremark) {
//		this.fpremark = fpremark;
//	}
//	public void setHuiKuan(Double huiKuan) {
//		this.huiKuan = huiKuan;
//	}
//	public void setApplicant(String applicant) {
//		this.applicant = applicant;
//	}
//	public void setRemittanceDate(String remittanceDate) {
//		this.remittanceDate = remittanceDate;
//	}
//	public void setRecorder(String recorder) {
//		this.recorder = recorder;
//	}
//	
//	
//}
