package com.chaoxing.oa.entity.po.employee;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "username", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class UserName implements Serializable {
	private static final long serialVersionUID = -3664636485351959352L;
	private int id;//ID
	private String username;//用户姓名
	private String password;//用户密码
	private int roleId;//权限
	private int departmentId;//部门ID
	private String position;//职位
	private String sex;//性别
	private String identityCard;//身份号
	private String borthDay;//出生年月
	private String nation;//民族
	private String degree;//学历
	private String graduatedSchool;//毕业学校
	private String major;//专业
	private String phoneNumber;//电话号码
	private String homeAddress;//家庭住址
	private String homeNumber;//家庭电话
	private String hiredate;//入职时间
	private String zhuanzhengTime;//转正时间
	private String pastLeaveTime;//过去离职时间
	private String earlyEntryDate;//早期入职时间
	private String householdType;//户口性质
	private String insurance;//保险
	private String insuranceCompany;//保险公司
	private String company;//公司名称
	private String resume;//简历
	private String photo;//照片
	private String degreeCertificate;//学历证书
	private String identityCardCopy;//身份证复印件
	private String familyRegister;//户口本
	private String leavingCertificate;//离职证明
	private String contract;//合同
	private String managementSystem;//管理制度
	private String entryForm;//入职表
	private String noSignReason;//未签原因
	private String signedTime;//签定时间
	private String terminationTime;//终止时间
	private String registeredAddress;//户口地址
	private String postCode;//邮编
	private String remarks;//备注
	private String contractNumber;//合同编号
	private String dueSocialSecurity;//计划入保时间
	private String socialSecurityHospital;//社保医院
	private String level;//级别
	private String recruitmentSources;//招聘来源
	private String nowAddress;//现居地址
	private String contractRenewal;//合同续签
	private String originalNumber;//原编号
	private String secrecyAgreement;//保密协议
	private String reportForm;//报表
	private String panCard;//办理工资卡
	private String leaveTime;//离职时间
	private String workPlace;//工作地点
	private String email;//邮箱
	private String ifSecret;//是否保密
	private String maritalStatus;//婚姻状况
	private String zhuanruGongsiTime;//转入本公司时间
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private String bumentiaozhengReport;//部门调整报表
	private String baoxianTongzhidan;//保险通知单
	private String signedBy;//签字人
	private String chargedBy;//负责人
	private BigDecimal sickLleaveTotal;//病假累计
	private BigDecimal annualLleave;//年假累计
	private Integer ifForeign;//外籍
	private Byte ifEngineering;//理工 
	private String txStruct;
	
	@Id
	@Column(name="ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "usernameTableGenerator", strategy = "native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="密码", updatable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "角色", updatable = false)
	public int getRoleId() {
		return roleId;
	}
	@Column(name = "部门ID")
	public int getDepartmentId() {
		return departmentId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name="职位")
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	@Column(name="性别")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="身份证号")
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	@Column(name="出生年月")
	public String getBorthDay() {
		return borthDay;
	}
	public void setBorthDay(String borthDay) {
		this.borthDay = borthDay;
	}
	@Column(name="民族")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	@Column(name="学历")
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Column(name="毕业院校")
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	@Column(name="专业")
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Column(name="联系电话")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="家庭地址")
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	@Column(name="家庭电话")
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	@Column(name="入职时间")
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	@Column(name="转正时间")
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	@Column(name="过去离职日期")
	public String getPastLeaveTime() {
		return pastLeaveTime;
	}
	public void setPastLeaveTime(String pastLeaveTime) {
		this.pastLeaveTime = pastLeaveTime;
	}
	@Column(name="早期入职时间")
	public String getEarlyEntryDate() {
		return earlyEntryDate;
	}
	public void setEarlyEntryDate(String earlyEntryDate) {
		this.earlyEntryDate = earlyEntryDate;
	}
	@Column(name="户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	@Column(name="保险")
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	@Column(name="保险公司")
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	@Column(name="公司名称")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="简历")
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	@Column(name="照片")
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Column(name="学历证书")
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	@Column(name="身份证复印件")
	public String getIdentityCardCopy() {
		return identityCardCopy;
	}
	public void setIdentityCardCopy(String identityCardCopy) {
		this.identityCardCopy = identityCardCopy;
	}
	@Column(name="户口本")
	public String getFamilyRegister() {
		return familyRegister;
	}
	public void setFamilyRegister(String familyRegister) {
		this.familyRegister = familyRegister;
	}
	@Column(name="离职证明")
	public String getLeavingCertificate() {
		return leavingCertificate;
	}
	public void setLeavingCertificate(String leavingCertificate) {
		this.leavingCertificate = leavingCertificate;
	}
	@Column(name="合同")
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	@Column(name="管理制度")
	public String getManagementSystem() {
		return managementSystem;
	}
	public void setManagementSystem(String managementSystem) {
		this.managementSystem = managementSystem;
	}
	@Column(name="入职表")
	public String getEntryForm() {
		return entryForm;
	}
	@Column(name = "未签原因")
	public String getNoSignReason() {
		return noSignReason;
	}
	public void setNoSignReason(String noSignReason) {
		this.noSignReason = noSignReason;
	}
	public void setEntryForm(String entryForm) {
		this.entryForm = entryForm;
	}
	@Column(name="签定时间")
	public String getSignedTime() {
		return signedTime;
	}
	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}
	@Column(name="终止时间")
	public String getTerminationTime() {
		return terminationTime;
	}
	public void setTerminationTime(String terminationTime) {
		this.terminationTime = terminationTime;
	}
	@Column(name="户口地址")
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	@Column(name="邮编")
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Column(name="备注")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="合同编号")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name="计划入保时间")
	public String getDueSocialSecurity() {
		return dueSocialSecurity;
	}
	public void setDueSocialSecurity(String dueSocialSecurity) {
		this.dueSocialSecurity = dueSocialSecurity;
	}
	@Column(name="社保医院")
	public String getSocialSecurityHospital() {
		return socialSecurityHospital;
	}
	public void setSocialSecurityHospital(String socialSecurityHospital) {
		this.socialSecurityHospital = socialSecurityHospital;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="招聘来源")
	public String getRecruitmentSources() {
		return recruitmentSources;
	}
	public void setRecruitmentSources(String recruitmentSources) {
		this.recruitmentSources = recruitmentSources;
	}
	@Column(name="现居住地")
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	@Column(name="合同续签")
	public String getContractRenewal() {
		return contractRenewal;
	}
	public void setContractRenewal(String contractRenewal) {
		this.contractRenewal = contractRenewal;
	}
	@Column(name="原编号")
	public String getOriginalNumber() {
		return originalNumber;
	}
	public void setOriginalNumber(String originalNumber) {
		this.originalNumber = originalNumber;
	}
	@Column(name="保密协议")
	public String getSecrecyAgreement() {
		return secrecyAgreement;
	}
	public void setSecrecyAgreement(String secrecyAgreement) {
		this.secrecyAgreement = secrecyAgreement;
	}
	@Column(name="报表")
	public String getReportForm() {
		return reportForm;
	}
	public void setReportForm(String reportForm) {
		this.reportForm = reportForm;
	}
	@Column(name="办理工资卡")
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	@Column(name="离职时间")
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	@Column(name="工作地点")
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	@Column(name="邮箱")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="工资保密", updatable = false, columnDefinition = " varchar(10) DEFAULT 'off'")
	public String getIfSecret() {
		return ifSecret;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
	}
	@Column(name="婚姻状况")
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@Column(name = "转入本公司时间")
	public String getZhuanruGongsiTime() {
		return zhuanruGongsiTime;
	}
	@Column(name = "入职报表")
	public String getRuzhiReport() {
		return ruzhiReport;
	}
	@Column(name = "离职报表")
	public String getLizhiReport() {
		return lizhiReport;
	}
	@Column(name = "转正报表")
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	@Column(name = "部门调整报表")
	public String getBumentiaozhengReport() {
		return bumentiaozhengReport;
	}
	@Column(name = "保险通知单")
	public String getBaoxianTongzhidan() {
		return baoxianTongzhidan;
	}
	@Column(name = "签字人")
	public String getSignedBy() {
		return signedBy;
	}
	@Column(name = "负责人")
	public String getChargedBy() {
		return chargedBy;
	}
	@Column(name="病假累计", columnDefinition=" decimal(10,2) DEFAULT '0.00'")
	public BigDecimal getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	
	@Column(name="年假累计", columnDefinition=" decimal(10,2) DEFAULT '0.00'")
	public BigDecimal getAnnualLleave() {
		return annualLleave;
	}
	public void setSickLleaveTotal(BigDecimal sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	public void setAnnualLleave(BigDecimal annualLleave) {
		this.annualLleave = annualLleave;
	}
	public void setChargedBy(String chargedBy) {
		this.chargedBy = chargedBy;
	}
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}
	public void setZhuanruGongsiTime(String zhuanruGongsiTime) {
		this.zhuanruGongsiTime = zhuanruGongsiTime;
	}
	public void setRuzhiReport(String ruzhiReport) {
		this.ruzhiReport = ruzhiReport;
	}
	public void setLizhiReport(String lizhiReport) {
		this.lizhiReport = lizhiReport;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public void setBumentiaozhengReport(String bumentiaozhengReport) {
		this.bumentiaozhengReport = bumentiaozhengReport;
	}
	public void setBaoxianTongzhidan(String baoxianTongzhidan) {
		this.baoxianTongzhidan = baoxianTongzhidan;
	}
	@Column(name="是否外籍", columnDefinition=" int DEFAULT '0'")
	public Integer getIfForeign() {
		return ifForeign;
	}
	public void setIfForeign(Integer ifForeign) {
		this.ifForeign = ifForeign;
	}
	@Column(name="是否理工", columnDefinition=" int DEFAULT '0'")
	public Byte getIfEngineering() {
		return ifEngineering;
	}
	public void setIfEngineering(Byte ifEngineering) {
		this.ifEngineering = ifEngineering;
	}
	@Column(name="报税架构")
	public String getTxStruct() {
		return txStruct;
	}
	public void setTxStruct(String txStruct) {
		this.txStruct = txStruct;
	}
	

}
