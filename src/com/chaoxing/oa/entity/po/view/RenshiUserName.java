package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "人事username", schema = "")
public class RenshiUserName implements Serializable {
	private static final long serialVersionUID = -206738471078895098L;
	private String renshiRight;// 人事权限
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String guidance;//指导
	private String guidanceEmail;//指导邮箱
	private int id;//ID
	private String username;//用户姓名
	private String password;//用户密码
	private Integer departmentId;//部门ID
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
	private String signedTime;//签定时间
	private String terminationTime;//终止时间
	private String registeredAddress;//户口地址
	private String postcode;//邮编
	private String remarks;//备注
	private String contractNumber;//合同编号
	private String dueSocialSecurity;//终止时间
	private String socialSecurityHospital;//社保医院
	private String level;//级别
	private String recruitmentSources;//招聘来源
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
	private Integer roleId;//角色
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private String bumentiaozhengReport;//部门调整报表
//	private BigDecimal sickLleaveTotal;//病假累计
//	private BigDecimal annualLleave;//年假累计
	private Integer ifForeign;//外籍
	private Byte ifEngineering;//理工 
	private String txStruct;
	
	public RenshiUserName() {
		super();
	}
	
	public RenshiUserName(int id) {
		super();
		this.id = id;
	}

	public RenshiUserName(String renshiRight, String firstLevel, String secondLevel, String thirdLevel,
			String fourthLevel, String cellCore, String cellCoreEmail, String guidance, String guidanceEmail, int id,
			String username, String password, Integer departmentId, String position, String sex, String identityCard,
			String borthDay, String nation, String degree, String graduatedSchool, String major, String phoneNumber,
			String homeAddress, String homeNumber, String hiredate, String zhuanzhengTime, String pastLeaveTime,
			String earlyEntryDate, String householdType, String insurance, String insuranceCompany, String company,
			String resume, String photo, String identityCardCopy, String familyRegister, String leavingCertificate,
			String contract, String managementSystem, String entryForm, String signedTime, String terminationTime,
			String registeredAddress, String postcode, String remarks, String contractNumber, String dueSocialSecurity,
			String socialSecurityHospital, String level, String recruitmentSources, String contractRenewal,
			String originalNumber, String secrecyAgreement, String reportForm, String panCard, String leaveTime,
			String workPlace, String email, String ifSecret, String maritalStatus, Integer roleId, String ruzhiReport,
			String lizhiReport, String zhuanzhengReport, String bumentiaozhengReport) {
		super();
		this.renshiRight = renshiRight;
		this.firstLevel = firstLevel;
		this.secondLevel = secondLevel;
		this.thirdLevel = thirdLevel;
		this.fourthLevel = fourthLevel;
		this.cellCore = cellCore;
		this.cellCoreEmail = cellCoreEmail;
		this.guidance = guidance;
		this.guidanceEmail = guidanceEmail;
		this.id = id;
		this.username = username;
		this.password = password;
		this.departmentId = departmentId;
		this.position = position;
		this.sex = sex;
		this.identityCard = identityCard;
		this.borthDay = borthDay;
		this.nation = nation;
		this.degree = degree;
		this.graduatedSchool = graduatedSchool;
		this.major = major;
		this.phoneNumber = phoneNumber;
		this.homeAddress = homeAddress;
		this.homeNumber = homeNumber;
		this.hiredate = hiredate;
		this.zhuanzhengTime = zhuanzhengTime;
		this.pastLeaveTime = pastLeaveTime;
		this.earlyEntryDate = earlyEntryDate;
		this.householdType = householdType;
		this.insurance = insurance;
		this.insuranceCompany = insuranceCompany;
		this.company = company;
		this.resume = resume;
		this.photo = photo;
		this.identityCardCopy = identityCardCopy;
		this.familyRegister = familyRegister;
		this.leavingCertificate = leavingCertificate;
		this.contract = contract;
		this.managementSystem = managementSystem;
		this.entryForm = entryForm;
		this.signedTime = signedTime;
		this.terminationTime = terminationTime;
		this.registeredAddress = registeredAddress;
		this.postcode = postcode;
		this.remarks = remarks;
		this.contractNumber = contractNumber;
		this.dueSocialSecurity = dueSocialSecurity;
		this.socialSecurityHospital = socialSecurityHospital;
		this.level = level;
		this.recruitmentSources = recruitmentSources;
		this.contractRenewal = contractRenewal;
		this.originalNumber = originalNumber;
		this.secrecyAgreement = secrecyAgreement;
		this.reportForm = reportForm;
		this.panCard = panCard;
		this.leaveTime = leaveTime;
		this.workPlace = workPlace;
		this.email = email;
		this.ifSecret = ifSecret;
		this.maritalStatus = maritalStatus;
		this.roleId = roleId;
		this.ruzhiReport = ruzhiReport;
		this.lizhiReport = lizhiReport;
		this.zhuanzhengReport = zhuanzhengReport;
		this.bumentiaozhengReport = bumentiaozhengReport;
	}
	@Column(name="人事权限")
	public String getRenshiRight() {
		return renshiRight;
	}
	public void setRenshiRight(String renshiRight) {
		this.renshiRight = renshiRight;
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
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	@Column(name="指导")
	public String getGuidance() {
		return guidance;
	}
	@Column(name="指导邮箱")
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	@Column(name = "部门调整报表")
	public String getBumentiaozhengReport() {
		return bumentiaozhengReport;
	}
	public void setBumentiaozhengReport(String bumentiaozhengReport) {
		this.bumentiaozhengReport = bumentiaozhengReport;
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
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}
	
	@Id
	@Column(name="ID")
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
	@Column(name="密码")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="部门ID")
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
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
//	@Column(name="学历证件")
//	public String getDegreeCertificate() {
//		return degreeCertificate;
//	}
//	public void setDegreeCertificate(String degreeCertificate) {
//		this.degreeCertificate = degreeCertificate;
//	}
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
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	@Column(name="工资保密")
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
	@Column(name="角色")
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
	@Column(name = "学历证书")
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
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
	@Column(name="是否外籍")
	public Integer getIfForeign() {
		return ifForeign;
	}
	@Column(name="是否理工")
	public Byte getIfEngineering() {
		return ifEngineering;
	}
	public void setIfForeign(Integer ifForeign) {
		this.ifForeign = ifForeign;
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
