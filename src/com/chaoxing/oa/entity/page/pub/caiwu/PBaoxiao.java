package com.chaoxing.oa.entity.page.pub.caiwu;


public class PBaoxiao {
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
	private String aproTime;//批准时间
	private String approRemark;//领导意见
	private String jtime;//邮寄时间
	private String kdno;//快递单号
	private Integer reciverId;//收票人Id
	private String reciveTime;//收票时间
	private String rcRemarks;//收票备注
	private Integer checkerId;//审核人Id
	private Integer cpid;//出票人id
	private Float tuipiao;//退票金额
	private String caiwuRemarks;//财务备注
	private Float koujk;//扣借款
	private Float baoxMoney;//汇款金额
	private Float huikuan;//汇款金额
	private String baoxTime;//汇款时间
	private Integer status;//状态
	private String kunhao;//捆号
	private String createTime;//创建时间
	//架构信息
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String cellCoreEmail;//细胞核邮箱
	private String guidanceEmail;//指导邮箱
	
	public Long getId() {
		return id;
	}
	public Integer getUid() {
		return uid;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Float getMoney() {
		return money;
	}
	public Float getHuankuan() {
		return huankuan;
	}
	public Integer getNumber() {
		return number;
	}
	public String getExplain() {
		return explain;
	}
	public String getBank() {
		return bank;
	}
	public String getAccount() {
		return account;
	}
	public Integer getApproid() {
		return approid;
	}
	public String getApprover() {
		return approver;
	}
	public String getAproEmail() {
		return aproEmail;
	}
	public String getAproTime() {
		return aproTime;
	}
	public String getApproRemark() {
		return approRemark;
	}
	public String getJtime() {
		return jtime;
	}
	public String getKdno() {
		return kdno;
	}
	public Integer getReciverId() {
		return reciverId;
	}
	public String getReciveTime() {
		return reciveTime;
	}
	public String getRcRemarks() {
		return rcRemarks;
	}
	public Integer getCheckerId() {
		return checkerId;
	}
	public Integer getCpid() {
		return cpid;
	}
	public Float getTuipiao() {
		return tuipiao;
	}
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
	public Float getKoujk() {
		return koujk;
	}
	public Float getBaoxMoney() {
		return baoxMoney;
	}
	public String getBaoxTime() {
		return baoxTime;
	}
	public Integer getStatus() {
		return status;
	}
	public String getKunhao() {
		return kunhao;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
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
	public void setAproTime(String aproTime) {
		this.aproTime = aproTime;
	}
	public void setApproRemark(String approRemark) {
		this.approRemark = approRemark;
	}
	public void setJtime(String jtime) {
		this.jtime = jtime;
	}
	public void setKdno(String kdno) {
		this.kdno = kdno;
	}
	public void setReciverId(Integer reciverId) {
		this.reciverId = reciverId;
	}
	public void setReciveTime(String reciveTime) {
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
	public void setBaoxTime(String baoxTime) {
		this.baoxTime = baoxTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setKunhao(String kunhao) {
		this.kunhao = kunhao;
	}
	public void setCreateTime(String createTime) {
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
	public Float getHuikuan() {
		return huikuan;
	}
	public void setHuikuan(Float huikuan) {
		this.huikuan = huikuan;
	}
	
}
