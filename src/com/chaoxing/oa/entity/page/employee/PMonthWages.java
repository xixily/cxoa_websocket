package com.chaoxing.oa.entity.page.employee;

public class PMonthWages {
	private Integer id;//当月工资id
	private Integer employeeId;//职员编号
	private String username;//姓名
	private Double salary;//工资总额
	private String identityCard;//身份证号码
	private Double secrecySubsidy;//保密补贴
	private Double communicationSubsidy;//通讯补贴
	private Double lunchSubsidy;//午餐补贴
	private String remarks;//备注
	private String company;//公司名称
	private Double radix;//基数
	private String householdType;//户口性质
	private Double basicWage;//基本工资
	private Double postSalary;//岗位工资
	private Double performanceRelatedPay;//绩效工资
	private String accountBank;//开户行
	private String account;//职工帐号
	private String salaryCode;//工资编号
	private Double subEndowmentIinsurance;//代扣养老保险
	private Double subMedicare;//代扣医疗保险
	private Double subUnemployedInsurance;//代扣失业保险
	private Double subHouseIinsurance;//代扣住房保险
	private Double cEndowmentIinsurance;//公司养老保险
	private Double cMedicare;//公司医疗保险
	private Double cUnemployedInsurance;//公司失业保险
	private Double cHouseIinsurance;//公司住房保险
	private Double cSickPayTotal;//累计带薪病假
	private Double cInjuryInsurance;//公司工伤保险
	private Double cBirthIinsurance;//公司生育保险
	private Double annualLleave;//年假累计
	private String workPhone;//办公电话
	private Integer internalNumber;//内部编号
	private Double sickLleaveTotal;//病假累计
	private String rubaoTime;//入保时间
	private Integer departmentId;//小组ID（部门ID）
	private String firstLevel;//公司
	private String secondLevel;//部门
	private String thirdLevel;//岗位
	private String fourthLevel;//小组
	private String hiredate;//入职时间
	private String leaveTime;//离职时间
	private String zhuanzhengTime;//转正时间
	private String ruzhiReport;//入职报表
	private String lizhiReport;//离职报表
	private String zhuanzhengReport;//转正报表
	private Float chuqinDay;//出勤天数
	private Float zhuanzhengChaeDay;//转正差额天数
	private Float chidaoYingkouDay;//迟到应扣天数
	private Float shiJiaHour;//事假时数
	private Float bingJiaHour;//病假时数
	private Float kuangGongHour;//旷工时数
	private Float hunJiaDay;//婚假天数
	private Float chanJiaDay;//产假天数
	private Float sangJiaDay;//丧家天数
	private Float nianJiaDay;//年假天数
	private String kaoQinremarks;//考勤备注
	private Float kaoqinTotal;//考勤总额
	private Float subTotal;//代扣五险总额
	private Float yingfaTotal;//应发总额
	private Float shifaTotal;//实发总额
	private Float cTotal;//公司五险总额
	private Float selfTax;//个人所得税
	private Float fakuan;//罚款
	private Float jiangjin;//奖金
	private Float bufaSalary;//补发工资
	private Double lishiSalary;//历史工资
	private String taxStructure;//报税架构
	private Integer countId;//统计架构
	public Integer getId() {
		return id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getUsername() {
		return username;
	}
	public Double getSalary() {
		return salary;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public Double getSecrecySubsidy() {
		return secrecySubsidy;
	}
	public Double getCommunicationSubsidy() {
		return communicationSubsidy;
	}
	public Double getLunchSubsidy() {
		return lunchSubsidy;
	}
	public String getRemarks() {
		return remarks;
	}
	public String getCompany() {
		return company;
	}
	public Double getRadix() {
		return radix;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public Double getBasicWage() {
		return basicWage;
	}
	public Double getPostSalary() {
		return postSalary;
	}
	public Double getPerformanceRelatedPay() {
		return performanceRelatedPay;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public String getAccount() {
		return account;
	}
	public String getSalaryCode() {
		return salaryCode;
	}
	public Double getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	public Double getSubMedicare() {
		return subMedicare;
	}
	public Double getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	public Double getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	public Double getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	public Double getcMedicare() {
		return cMedicare;
	}
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	public Double getcSickPayTotal() {
		return cSickPayTotal;
	}
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	public Double getAnnualLleave() {
		return annualLleave;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public Integer getInternalNumber() {
		return internalNumber;
	}
	public Double getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	public String getRubaoTime() {
		return rubaoTime;
	}
	public Integer getDepartmentId() {
		return departmentId;
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
	public String getHiredate() {
		return hiredate;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
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
	public Float getChuqinDay() {
		return chuqinDay;
	}
	public Float getZhuanzhengChaeDay() {
		return zhuanzhengChaeDay;
	}
	public Float getChidaoYingkouDay() {
		return chidaoYingkouDay;
	}
	public Float getShiJiaHour() {
		return shiJiaHour;
	}
	public Float getBingJiaHour() {
		return bingJiaHour;
	}
	public Float getKuangGongHour() {
		return kuangGongHour;
	}
	public Float getHunJiaDay() {
		return hunJiaDay;
	}
	public Float getChanJiaDay() {
		return chanJiaDay;
	}
	public Float getSangJiaDay() {
		return sangJiaDay;
	}
	public Float getNianJiaDay() {
		return nianJiaDay;
	}
	public String getKaoQinremarks() {
		return kaoQinremarks;
	}
	public Double getLishiSalary() {
		return lishiSalary;
	}
	public String getTaxStructure() {
		return taxStructure;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setLishiSalary(Double lishiSalary) {
		this.lishiSalary = lishiSalary;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setSecrecySubsidy(Double secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}
	public void setCommunicationSubsidy(Double communicationSubsidy) {
		this.communicationSubsidy = communicationSubsidy;
	}
	public void setLunchSubsidy(Double lunchSubsidy) {
		this.lunchSubsidy = lunchSubsidy;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setRadix(Double radix) {
		this.radix = radix;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setBasicWage(Double basicWage) {
		this.basicWage = basicWage;
	}
	public void setPostSalary(Double postSalary) {
		this.postSalary = postSalary;
	}
	public void setPerformanceRelatedPay(Double performanceRelatedPay) {
		this.performanceRelatedPay = performanceRelatedPay;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
	}
	public void setSubEndowmentIinsurance(Double subEndowmentIinsurance) {
		this.subEndowmentIinsurance = subEndowmentIinsurance;
	}
	public void setSubMedicare(Double subMedicare) {
		this.subMedicare = subMedicare;
	}
	public void setSubUnemployedInsurance(Double subUnemployedInsurance) {
		this.subUnemployedInsurance = subUnemployedInsurance;
	}
	public void setSubHouseIinsurance(Double subHouseIinsurance) {
		this.subHouseIinsurance = subHouseIinsurance;
	}
	public void setcEndowmentIinsurance(Double cEndowmentIinsurance) {
		this.cEndowmentIinsurance = cEndowmentIinsurance;
	}
	public void setcMedicare(Double cMedicare) {
		this.cMedicare = cMedicare;
	}
	public void setcUnemployedInsurance(Double cUnemployedInsurance) {
		this.cUnemployedInsurance = cUnemployedInsurance;
	}
	public void setcHouseIinsurance(Double cHouseIinsurance) {
		this.cHouseIinsurance = cHouseIinsurance;
	}
	public void setcSickPayTotal(Double cSickPayTotal) {
		this.cSickPayTotal = cSickPayTotal;
	}
	public void setcInjuryInsurance(Double cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcBirthIinsurance(Double cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	public void setAnnualLleave(Double annualLleave) {
		this.annualLleave = annualLleave;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public void setInternalNumber(Integer internalNumber) {
		this.internalNumber = internalNumber;
	}
	public void setSickLleaveTotal(Double sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	public void setRubaoTime(String rubaoTime) {
		this.rubaoTime = rubaoTime;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
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
	public void setChuqinDay(Float chuqinDay) {
		this.chuqinDay = chuqinDay;
	}
	public void setZhuanzhengChaeDay(Float zhuanzhengChaeDay) {
		this.zhuanzhengChaeDay = zhuanzhengChaeDay;
	}
	public void setChidaoYingkouDay(Float chidaoYingkouDay) {
		this.chidaoYingkouDay = chidaoYingkouDay;
	}
	public void setShiJiaHour(Float shiJiaHour) {
		this.shiJiaHour = shiJiaHour;
	}
	public void setBingJiaHour(Float bingJiaHour) {
		this.bingJiaHour = bingJiaHour;
	}
	public void setKuangGongHour(Float kuangGongHour) {
		this.kuangGongHour = kuangGongHour;
	}
	public void setHunJiaDay(Float hunJiaDay) {
		this.hunJiaDay = hunJiaDay;
	}
	public void setChanJiaDay(Float chanJiaDay) {
		this.chanJiaDay = chanJiaDay;
	}
	public void setSangJiaDay(Float sangJiaDay) {
		this.sangJiaDay = sangJiaDay;
	}
	public void setNianJiaDay(Float nianJiaDay) {
		this.nianJiaDay = nianJiaDay;
	}
	public void setKaoQinremarks(String kaoQinremarks) {
		this.kaoQinremarks = kaoQinremarks;
	}
	public Float getKaoqinTotal() {
		return kaoqinTotal;
	}
	public Float getSubTotal() {
		return subTotal;
	}
	public Float getYingfaTotal() {
		return yingfaTotal;
	}
	public Float getShifaTotal() {
		return shifaTotal;
	}
	public Float getcTotal() {
		return cTotal;
	}
	public void setKaoqinTotal(Float kaoqinTotal) {
		this.kaoqinTotal = kaoqinTotal;
	}
	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}
	public void setYingfaTotal(Float yingfaTotal) {
		this.yingfaTotal = yingfaTotal;
	}
	public void setShifaTotal(Float shifaTotal) {
		this.shifaTotal = shifaTotal;
	}
	public void setcTotal(Float cTotal) {
		this.cTotal = cTotal;
	}
	public Float getSelfTax() {
		return selfTax;
	}
	public void setSelfTax(Float selfTax) {
		this.selfTax = selfTax;
	}
	public Float getFakuan() {
		return fakuan;
	}
	public Float getJiangjin() {
		return jiangjin;
	}
	public Float getBufaSalary() {
		return bufaSalary;
	}
	public void setFakuan(Float fakuan) {
		this.fakuan = fakuan;
	}
	public void setJiangjin(Float jiangjin) {
		this.jiangjin = jiangjin;
	}
	public void setBufaSalary(Float bufaSalary) {
		this.bufaSalary = bufaSalary;
	}
	public Integer getCountId() {
		return countId;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	
}
