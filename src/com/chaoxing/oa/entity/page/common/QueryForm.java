package com.chaoxing.oa.entity.page.common;


public class QueryForm {
	/**
	 * 分页项
	 */
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	/**
	 * 信息项
	 */
	private String ip;
//	private Integer ID;//ID
	private Integer id;//ID
	private String email;//邮箱
	private String username;//用户姓名
	private String password;//用户密码
	private Integer rights;//权限(判断是否为人事)
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
	private String Postcode;//邮编
	private String remarks;//备注
	private String contractNumber;//合同编号
	private String dueSocialSecurity;//终止时间
	private String socialSecurityHospital;//社保医院
	private String shebaoType;
	private String level;//级别
	private String recruitmentSources;//招聘来源
	private String contractRenewal;//合同续签
	private String originalNumber;//原编号
	private String secrecyAgreement;//保密协议
	private String reportForm;//报表
	private String panCard;//办理工资卡
	private String leaveTime;//离职时间
	private String workPlace;//工作地点
	private String ifSecret;//是否保密
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级（小组）
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private String wagesMonth;//工作日计算表月份
	private String accountBank;//职工账号
	private String account;//职工账号
	// 密码修改
	private String newpassword;//新密码
	/**
	 * 可配置项
	 */
	private String configurable;
	private String configurable_value;
	/**
	 * 前台请求下载文件的名字
	 */
	private String downLoadFileName;
	private String levelc;
	
	public QueryForm(String email) {
		super();
		this.email = email;
	}
	public QueryForm() {
		super();
	}
	public String getLevelc() {
		return levelc;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public String getIp() {
		return ip;
	}
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Integer getRights() {
		return rights;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public String getPosition() {
		return position;
	}
	public String getSex() {
		return sex;
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
	public String getDegree() {
		return degree;
	}
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public String getMajor() {
		return major;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public String getPastLeaveTime() {
		return pastLeaveTime;
	}
	public String getEarlyEntryDate() {
		return earlyEntryDate;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public String getInsurance() {
		return insurance;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public String getCompany() {
		return company;
	}
	public String getResume() {
		return resume;
	}
	public String getPhoto() {
		return photo;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
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
	public String getShebaoType() {
		return shebaoType;
	}
	public void setShebaoType(String shebaoType) {
		this.shebaoType = shebaoType;
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
	public String getLeaveTime() {
		return leaveTime;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public String getIfSecret() {
		return ifSecret;
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
	public String getConfigurable() {
		return configurable;
	}
	public String getConfigurable_value() {
		return configurable_value;
	}
	public String getDownLoadFileName() {
		return downLoadFileName;
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
	public String getAccountBank() {
		return accountBank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRights(Integer rights) {
		this.rights = rights;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	public void setPastLeaveTime(String pastLeaveTime) {
		this.pastLeaveTime = pastLeaveTime;
	}
	public void setEarlyEntryDate(String earlyEntryDate) {
		this.earlyEntryDate = earlyEntryDate;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
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
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
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
	public void setConfigurable(String configurable) {
		this.configurable = configurable;
	}
	public void setConfigurable_value(String configurable_value) {
		this.configurable_value = configurable_value;
	}
	public void setDownLoadFileName(String downLoadFileName) {
		this.downLoadFileName = downLoadFileName;
	}
	public void setLevelc(String levelc) {
		this.levelc = levelc;
	}
	public String getWagesMonth() {
		return wagesMonth;
	}
	public void setWagesMonth(String wagesMonth) {
		this.wagesMonth = wagesMonth;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	@Override
	public String toString() {
		return "QueryForm [page=" + page + ", rows=" + rows + ", sort=" + sort + ", order=" + order + ", ip=" + ip
				+ ", id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", rights="
				+ rights + ", departmentId=" + departmentId + ", position=" + position + ", sex=" + sex
				+ ", identityCard=" + identityCard + ", borthDay=" + borthDay + ", nation=" + nation + ", degree="
				+ degree + ", graduatedSchool=" + graduatedSchool + ", major=" + major + ", phoneNumber=" + phoneNumber
				+ ", homeAddress=" + homeAddress + ", homeNumber=" + homeNumber + ", hiredate=" + hiredate
				+ ", zhuanzhengTime=" + zhuanzhengTime + ", pastLeaveTime=" + pastLeaveTime + ", earlyEntryDate="
				+ earlyEntryDate + ", householdType=" + householdType + ", insurance=" + insurance
				+ ", insuranceCompany=" + insuranceCompany + ", company=" + company + ", resume=" + resume + ", photo="
				+ photo + ", degreeCertificate=" + degreeCertificate + ", identityCardCopy=" + identityCardCopy
				+ ", familyRegister=" + familyRegister + ", leavingCertificate=" + leavingCertificate + ", contract="
				+ contract + ", managementSystem=" + managementSystem + ", entryForm=" + entryForm + ", signedTime="
				+ signedTime + ", terminationTime=" + terminationTime + ", registeredAddress=" + registeredAddress
				+ ", Postcode=" + Postcode + ", remarks=" + remarks + ", contractNumber=" + contractNumber
				+ ", dueSocialSecurity=" + dueSocialSecurity + ", socialSecurityHospital=" + socialSecurityHospital
				+ ", shebaoType=" + shebaoType + ", level=" + level + ", recruitmentSources=" + recruitmentSources
				+ ", contractRenewal=" + contractRenewal + ", originalNumber=" + originalNumber + ", secrecyAgreement="
				+ secrecyAgreement + ", reportForm=" + reportForm + ", panCard=" + panCard + ", leaveTime=" + leaveTime
				+ ", workPlace=" + workPlace + ", ifSecret=" + ifSecret + ", firstLevel=" + firstLevel
				+ ", secondLevel=" + secondLevel + ", thirdLevel=" + thirdLevel + ", fourthLevel=" + fourthLevel
				+ ", ruzhiReport=" + ruzhiReport + ", lizhiReport=" + lizhiReport + ", zhuanzhengReport="
				+ zhuanzhengReport + ", wagesMonth=" + wagesMonth + ", configurable=" + configurable
				+ ", configurable_value=" + configurable_value + ", downLoadFileName=" + downLoadFileName + ", levelc="
				+ levelc + "]";
	}
	
	
}
