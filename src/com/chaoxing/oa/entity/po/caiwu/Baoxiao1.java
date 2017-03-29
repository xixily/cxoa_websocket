package com.chaoxing.oa.entity.po.caiwu;
//package com.chaoxing.oa.entity.po.caiwu;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//
//@Entity
//@Table(name = "报销表", schema="")
//@DynamicInsert(true)
//@DynamicUpdate(true)
//public class Baoxiao implements Serializable{
//	private static final long serialVersionUID = 3551927366660915601L;
//	private Long id;
////	private Long piciNum;//
//	private Date jTime;//邮寄时间
//	private Integer uid;//报销人ID
//	private String username;//报销人
//	private String email;//报销人邮箱
//	private Integer cellCoreId;//细胞核ID
//	private String cellCoreEmail;//细胞核邮箱
//	private Integer number;//报销张数
//	private Float money;//金额
//	private Float huankuan;//还款金额
//	private String bank;//银行
//	private String account;//账号
//	private String explain;//说明
//	private Integer approid;//批准人ID
//	private String approver;//批准人
//	private String aproEmail;//批准人邮箱
//	private Date aproTime;//批准时间
//	private Integer checkerId;//财务审核人Id
//	private Integer reciverId;//收票人Id
//	private Date reciveTime;//收票时间
//	private Date baoxTime;//报销时间
//	private Float baoxMoney;//允许报销金额
//	private Float tuipiao;//退票金额
//	private String caiwuRemarks;//财务备注
//	private Integer status;//状态
//	private String kdno;//快递单号
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	public Long getId() {
//		return id;
//	}
////	@Column(name="批次号")
////	public Long getPiciNum() {
////		return piciNum;
////	}
//	@Column(name="邮寄时间",columnDefinition="timestamp NULL")
//	public Date getjTime() {
//		return jTime;
//	}
////	@Column(name="报销人ID",columnDefinition="int(11) NOT NULL ")
////	@Column(name="报销人ID",columnDefinition="int(11) constraint FK_USERNAME_BAOXIAO_BXID FOREIGN KEY REFERENCES username(id) NOT NULL ")//外键关联
////	@ManyToOne(targetEntity=UserName.class)
////	@JoinColumn(referencedColumnName="id",name="uid")
//	@Column(name="报销人ID")
//	public Integer getUid() {
//		return uid;
//	}
//	@Column(name="报销人")
//	public String getUsername() {
//		return username;
//	}
//	@Column(name="报销人邮箱")
//	public String getEmail() {
//		return email;
//	}
//	@Column(name="细胞核ID")
//	public Integer getCellCoreId() {
//		return cellCoreId;
//	}
//	@Column(name="细胞核邮箱")
//	public String getCellCoreEmail() {
//		return cellCoreEmail;
//	}
//	@Column(name="报销张数")
//	public Integer getNumber() {
//		return number;
//	}
//	@Column(name="申报金额")
//	public Float getMoney() {
//		return money;
//	}
//	@Column(name="还借款金额")
//	public Float getHuankuan() {
//		return huankuan;
//	}
//	@Column(name="银行")
//	public String getBank() {
//		return bank;
//	}
//	@Column(name="卡号")
//	public String getAccount() {
//		return account;
//	}
//	@Column(name="说明")
//	public String getExplain() {
//		return explain;
//	}
//	@Column(name="批准人id")
////	@ManyToOne(targetEntity=UserName.class)
////	@JoinColumn(name="approid",referencedColumnName="id")
////	@Column(name="批准人ID",columnDefinition="int(11) constraint FK_USERNAME_BAOXIAO_APPROID FOREIGN KEY REFERENCES username(id) NOT NULL ")//外键关联
//	public Integer getApproid() {
//		return approid;
//	}
//	@Column(name="批准人")
//	public String getApprover() {
//		return approver;
//	}
//	@Column(name="批准人邮箱")
//	public String getAproEmail() {
//		return aproEmail;
//	}
//	@Column(name="批准时间",columnDefinition="timestamp NULL")
//	public Date getAproTime() {
//		return aproTime;
//	}
//	@Column(name="财务审核人id")
//	public Integer getCheckerId() {
//		return checkerId;
//	}
//	@Column(name="财务收票人id")
//	public Integer getReciverId() {
//		return reciverId;
//	}
//	@Column(name="收票时间",columnDefinition="timestamp NULL")
//	public Date getReciveTime() {
//		return reciveTime;
//	}
//	@Column(name="报销时间",columnDefinition="timestamp NULL")
//	public Date getBaoxTime() {
//		return baoxTime;
//	}
//	@Column(name="报销金额")
//	public Float getBaoxMoney() {
//		return baoxMoney;
//	}
//	@Column(name="退票金额")
//	public Float getTuipiao() {
//		return tuipiao;
//	}
//	@Column(name="财务备注")
//	public String getCaiwuRemarks() {
//		return caiwuRemarks;
//	}
//	@Column
//	public Integer getStatus() {
//		return status;
//	}
//	@Column(name="快递单号")
//	public String getKdno() {
//		return kdno;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
////	public void setPiciNum(Long piciNum) {
////		this.piciNum = piciNum;
////	}
//	public void setjTime(Date jTime) {
//		this.jTime = jTime;
//	}
//	public void setUid(Integer uid) {
//		this.uid = uid;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public void setCellCoreId(Integer cellCoreId) {
//		this.cellCoreId = cellCoreId;
//	}
//	public void setCellCoreEmail(String cellCoreEmail) {
//		this.cellCoreEmail = cellCoreEmail;
//	}
//	public void setNumber(Integer number) {
//		this.number = number;
//	}
//	public void setMoney(Float money) {
//		this.money = money;
//	}
//	public void setHuankuan(Float huankuan) {
//		this.huankuan = huankuan;
//	}
//	public void setBank(String bank) {
//		this.bank = bank;
//	}
//	public void setAccount(String account) {
//		this.account = account;
//	}
//	public void setExplain(String explain) {
//		this.explain = explain;
//	}
//	public void setApproid(Integer approid) {
//		this.approid = approid;
//	}
//	public void setApprover(String approver) {
//		this.approver = approver;
//	}
//	public void setAproEmail(String aproEmail) {
//		this.aproEmail = aproEmail;
//	}
//	public void setAproTime(Date aproTime) {
//		this.aproTime = aproTime;
//	}
//	public void setCheckerId(Integer checkerId) {
//		this.checkerId = checkerId;
//	}
//	public void setReciverId(Integer reciverId) {
//		this.reciverId = reciverId;
//	}
//	public void setReciveTime(Date reciveTime) {
//		this.reciveTime = reciveTime;
//	}
//	public void setBaoxTime(Date baoxTime) {
//		this.baoxTime = baoxTime;
//	}
//	public void setBaoxMoney(Float baoxMoney) {
//		this.baoxMoney = baoxMoney;
//	}
//	public void setTuipiao(Float tuipiao) {
//		this.tuipiao = tuipiao;
//	}
//	public void setCaiwuRemarks(String caiwuRemarks) {
//		this.caiwuRemarks = caiwuRemarks;
//	}
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//	public void setKdno(String kdno) {
//		this.kdno = kdno;
//	}
//	
//}
