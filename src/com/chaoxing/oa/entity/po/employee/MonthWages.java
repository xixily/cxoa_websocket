package com.chaoxing.oa.entity.po.employee;

import java.io.Serializable;

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
@Table(name = "当月工资表", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class MonthWages implements Serializable{
	private static final long serialVersionUID = 1732928410052197996L;
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
	private Float sangJiaDay;//丧假天数
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
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "monthWagesGenerator", strategy = "native")
	public Integer getId() {
		return id;
	}
	@Column(name = "职工编号")
	public Integer getEmployeeId() {
		return employeeId;
	}
	@Column(name = "姓名")
	public String getUsername() {
		return username;
	}
	@Column(name = "工资总额", columnDefinition = " double(10,2) default 0.00")
	public Double getSalary() {
		return salary;
	}
	@Column(name = "身份证号")
	public String getIdentityCard() {
		return identityCard;
	}
	@Column(name = "保密补贴", columnDefinition = " double(10,2) default 0.00")
	public Double getSecrecySubsidy() {
		return secrecySubsidy;
	}
	@Column(name = "通讯补贴", columnDefinition = " double(10,2) default 0.00")
	public Double getCommunicationSubsidy() {
		return communicationSubsidy;
	}
	@Column(name = "午餐补贴", columnDefinition = " double(10,2) default 0.00")
	public Double getLunchSubsidy() {
		return lunchSubsidy;
	}
	@Column(name = "工资分布表备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name = "公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name = "基数", columnDefinition = " double(10,2) default 0.00")
	public Double getRadix() {
		return radix;
	}
	@Column(name = "户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	@Column(name = "基本工资", columnDefinition = " double(10,2) default 0.00")
	public Double getBasicWage() {
		return basicWage;
	}
	@Column(name = "岗位工资", columnDefinition = " double(10,2) default 0.00")
	public Double getPostSalary() {
		return postSalary;
	}
	@Column(name = "绩效工资", columnDefinition = " double(10,2) default 0.00")
	public Double getPerformanceRelatedPay() {
		return performanceRelatedPay;
	}
	@Column(name = "开户行")
	public String getAccountBank() {
		return accountBank;
	}
	@Column(name = "帐号")
	public String getAccount() {
		return account;
	}
	@Column(name = "工资编号")
	public String getSalaryCode() {
		return salaryCode;
	}
	@Column(name = "代扣养老保险", columnDefinition = " double(10,2) default 0.00")
	public Double getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	@Column(name = "代扣医疗保险", columnDefinition = " double(10,2) default 0.00")
	public Double getSubMedicare() {
		return subMedicare;
	}
	@Column(name = "代扣失业保险", columnDefinition = " double(10,2) default 0.00")
	public Double getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	@Column(name = "代扣住房保险", columnDefinition = " double(10,2) default 0.00")
	public Double getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	@Column(name = "公司养老保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	@Column(name = "公司医疗保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcMedicare() {
		return cMedicare;
	}
	@Column(name = "公司失业保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name = "公司住房保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	@Column(name = "累计带薪病假", columnDefinition = " double(10,2) default 0.00")
	public Double getcSickPayTotal() {
		return cSickPayTotal;
	}
	@Column(name = "公司工伤保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name = "公司生育保险", columnDefinition = " double(10,2) default 0.00")
	public Double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	@Column(name = "年假累计", columnDefinition = " double(10,2) default 0.00")
	public Double getAnnualLleave() {
		return annualLleave;
	}
	@Column(name = "办公电话")
	public String getWorkPhone() {
		return workPhone;
	}
	@Column(name = "内部编号")
	public Integer getInternalNumber() {
		return internalNumber;
	}
	@Column(name = "病假累计", columnDefinition = " double(10,2) default 0.00")
	public Double getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	@Column(name = "入保时间")
	public String getRubaoTime() {
		return rubaoTime;
	}
	@Column(name = "部门ID")
	public Integer getDepartmentId() {
		return departmentId;
	}
	@Column(name = "公司")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name = "部门")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name = "岗位")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name = "小组")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name = "入职时间")
	public String getHiredate() {
		return hiredate;
	}
	@Column(name = "离职时间")
	public String getLeaveTime() {
		return leaveTime;
	}
	@Column(name = "转正时间")
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
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
	@Column(name = "出勤天数", columnDefinition = "float(10,2) default 21")
	public Float getChuqinDay() {
		return chuqinDay;
	}
	@Column(name = "转正差额天数", columnDefinition = "float(10,2) default 0.00")
	public Float getZhuanzhengChaeDay() {
		return zhuanzhengChaeDay;
	}
	@Column(name = "迟到应扣天数", columnDefinition = "float(10,2) default 0.00")
	public Float getChidaoYingkouDay() {
		return chidaoYingkouDay;
	}
	@Column(name = "事假小时数", columnDefinition = "float(10,2) default 0.00")
	public Float getShiJiaHour() {
		return shiJiaHour;
	}
	@Column(name = "病假小时数", columnDefinition = "float(10,2) default 0.00")
	public Float getBingJiaHour() {
		return bingJiaHour;
	}
	@Column(name = "旷工小时数", columnDefinition = "float(10,2) default 0.00")
	public Float getKuangGongHour() {
		return kuangGongHour;
	}
	@Column(name = "婚假天数", columnDefinition = "float(10,2) default 0.00")
	public Float getHunJiaDay() {
		return hunJiaDay;
	}
	@Column(name = "产假天数", columnDefinition = "float(10,2) default 0.00")
	public Float getChanJiaDay() {
		return chanJiaDay;
	}
	@Column(name = "丧假天数", columnDefinition = "float(10,2) default 0.00")
	public Float getSangJiaDay() {
		return sangJiaDay;
	}
	@Column(name = "年假天数", columnDefinition = "float(10,2) default 0.00")
	public Float getNianJiaDay() {
		return nianJiaDay;
	}
	@Column(name = "考勤备注")
	public String getKaoQinremarks() {
		return kaoQinremarks;
	}
	@Column(name = "考勤总额", columnDefinition = "float(10,2) default 0.00")
	public Float getKaoqinTotal() {
		return kaoqinTotal;
	}
	@Column(name = "代扣五险总额", columnDefinition = "float(10,2) default 0.00")
	public Float getSubTotal() {
		return subTotal;
	}
	@Column(name = "应发总额", columnDefinition = "float(10,2) default 0.00")
	public Float getYingfaTotal() {
		return yingfaTotal;
	}
	@Column(name = "实发总额", columnDefinition = "float(10,2) default 0.00")
	public Float getShifaTotal() {
		return shifaTotal;
	}
	@Column(name = "公司五险总额", columnDefinition = "float(10,2) default 0.00")
	public Float getcTotal() {
		return cTotal;
	}
	@Column(name = "个人所得税", columnDefinition = "float(10,4) default 0.0000")
	public Float getSelfTax() {
		return selfTax;
	}
	@Column(name = "罚款", columnDefinition = "float(10,2) default 0.00")
	public Float getFakuan() {
		return fakuan;
	}
	@Column(name = "奖金", columnDefinition = "float(10,2) default 0.00")
	public Float getJiangjin() {
		return jiangjin;
	}
	@Column(name = "补发工资", columnDefinition = "float(10,2) default 0.00")
	public Float getBufaSalary() {
		return bufaSalary;
	}
	@Column(name = "历史工资")
	public Double getLishiSalary() {
		return lishiSalary;
	}
	@Column(name="报税架构")
	public String getTaxStructure() {
		return taxStructure;
	}
	@Column(name="统计架构")
	public Integer getCountId() {
		return countId;
	}
	public void setLishiSalary(Double lishiSalary) {
		this.lishiSalary = lishiSalary;
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
	public void setSelfTax(Float selfTax) {
		this.selfTax = selfTax;
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
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
}
