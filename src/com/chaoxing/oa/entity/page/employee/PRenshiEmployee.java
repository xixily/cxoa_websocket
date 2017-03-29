package com.chaoxing.oa.entity.page.employee;

import java.io.Serializable;

public class PRenshiEmployee implements Serializable{
	private static final long serialVersionUID = 8022325028735486200L;
	private int id;//employeeId
	private String username;//用户姓名
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String position;//职位
	private String sex;//性别
	private String hiredate;//入职时间
	private String leaveTime;//离职时间
	private String workPlace;//工作地点
	private String phoneNumber;//电话号码
	private String degree;//学历
	private String maritalStatus;//婚姻状况
	private String insuranceCompany;//保险公司
	private String company;//公司名称
	private String earlyEntryDate;//早期入职时间
	private String zhuanzhengTime;//转正时间
	private String renshiRight;// 人事权限
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String guidance;//指导
	private String guidanceEmail;//指导邮箱
	private String identityCard;//身份号
	private String borthDay;//出生年月
	private String nation;//民族
	private String graduatedSchool;//毕业学校
	private String major;//专业
	private String homeAddress;//家庭住址
	private String homeNumber;//家庭电话
	private String pastLeaveTime;//过去离职时间
	private String householdType;//户口性质
	private String insurance;//保险
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
	private String Postcode;//邮编
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
	private String email;//邮箱
	private String ifSecret;//是否保密
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private String bumentiaozhengReport;//部门调整报表
	private Integer ifForeign;//外籍
	private Byte ifEngineering;//理工 
	private String txStruct;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
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
	public String getPosition() {
		return position;
	}
	public String getSex() {
		return sex;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getDegree() {
		return degree;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public void setPosition(String position) {
		this.position = position;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public String getCompany() {
		return company;
	}
	public String getEarlyEntryDate() {
		return earlyEntryDate;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setEarlyEntryDate(String earlyEntryDate) {
		this.earlyEntryDate = earlyEntryDate;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	public String getRenshiRight() {
		return renshiRight;
	}
	public String getCellCore() {
		return cellCore;
	}
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	public String getGuidance() {
		return guidance;
	}
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public String getBorthDay() {
		return borthDay;
	}
	public String getNation() {
		return nation;
	}
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public String getMajor() {
		return major;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public String getPastLeaveTime() {
		return pastLeaveTime;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public String getInsurance() {
		return insurance;
	}
	public String getResume() {
		return resume;
	}
	public String getPhoto() {
		return photo;
	}
	public String getIdentityCardCopy() {
		return identityCardCopy;
	}
	public String getFamilyRegister() {
		return familyRegister;
	}
	public String getLeavingCertificate() {
		return leavingCertificate;
	}
	public String getContract() {
		return contract;
	}
	public String getManagementSystem() {
		return managementSystem;
	}
	public String getEntryForm() {
		return entryForm;
	}
	public String getSignedTime() {
		return signedTime;
	}
	public String getTerminationTime() {
		return terminationTime;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public String getPostcode() {
		return Postcode;
	}
	public String getRemarks() {
		return remarks;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public String getDueSocialSecurity() {
		return dueSocialSecurity;
	}
	public String getSocialSecurityHospital() {
		return socialSecurityHospital;
	}
	public String getLevel() {
		return level;
	}
	public String getRecruitmentSources() {
		return recruitmentSources;
	}
	public String getContractRenewal() {
		return contractRenewal;
	}
	public String getOriginalNumber() {
		return originalNumber;
	}
	public String getSecrecyAgreement() {
		return secrecyAgreement;
	}
	public String getReportForm() {
		return reportForm;
	}
	public String getPanCard() {
		return panCard;
	}
	public String getEmail() {
		return email;
	}
	public String getIfSecret() {
		return ifSecret;
	}
	
	public String getRuzhiReport() {
		return ruzhiReport;
	}
	public String getLizhiReport() {
		return lizhiReport;
	}
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
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
	public void setRenshiRight(String renshiRight) {
		this.renshiRight = renshiRight;
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
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setBorthDay(String borthDay) {
		this.borthDay = borthDay;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public void setPastLeaveTime(String pastLeaveTime) {
		this.pastLeaveTime = pastLeaveTime;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setIdentityCardCopy(String identityCardCopy) {
		this.identityCardCopy = identityCardCopy;
	}
	public void setFamilyRegister(String familyRegister) {
		this.familyRegister = familyRegister;
	}
	public void setLeavingCertificate(String leavingCertificate) {
		this.leavingCertificate = leavingCertificate;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public void setManagementSystem(String managementSystem) {
		this.managementSystem = managementSystem;
	}
	public void setEntryForm(String entryForm) {
		this.entryForm = entryForm;
	}
	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}
	public void setTerminationTime(String terminationTime) {
		this.terminationTime = terminationTime;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public void setPostcode(String postcode) {
		Postcode = postcode;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public void setDueSocialSecurity(String dueSocialSecurity) {
		this.dueSocialSecurity = dueSocialSecurity;
	}
	public void setSocialSecurityHospital(String socialSecurityHospital) {
		this.socialSecurityHospital = socialSecurityHospital;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setRecruitmentSources(String recruitmentSources) {
		this.recruitmentSources = recruitmentSources;
	}
	public void setContractRenewal(String contractRenewal) {
		this.contractRenewal = contractRenewal;
	}
	public void setOriginalNumber(String originalNumber) {
		this.originalNumber = originalNumber;
	}
	public void setSecrecyAgreement(String secrecyAgreement) {
		this.secrecyAgreement = secrecyAgreement;
	}
	public void setReportForm(String reportForm) {
		this.reportForm = reportForm;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
	}
	public String getBumentiaozhengReport() {
		return bumentiaozhengReport;
	}
	public void setBumentiaozhengReport(String bumentiaozhengReport) {
		this.bumentiaozhengReport = bumentiaozhengReport;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	public Integer getIfForeign() {
		return ifForeign;
	}
	public void setIfForeign(Integer ifForeign) {
		this.ifForeign = ifForeign;
	}
	public Byte getIfEngineering() {
		return ifEngineering;
	}
	public void setIfEngineering(Byte ifEngineering) {
		this.ifEngineering = ifEngineering;
	}
	public String getTxStruct() {
		return txStruct;
	}
	public void setTxStruct(String txStruct) {
		this.txStruct = txStruct;
	}
	
}
