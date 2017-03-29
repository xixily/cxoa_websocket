package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "细胞核查看员工信息")
public class GuidanceFindEmployye implements Serializable {
	private static final long serialVersionUID = 7162869851704286699L;
	private int ID;//ID
	private String username;//用户姓名
	private String position;//职位
	private int departmentId;//部门ID
	private String sex;//性别
	private String borthDay;//出生年月
	private String nation;//民族
	private String degree;//学历
	private String graduatedSchool;//毕业学校
	private String major;//专业
	private String phoneNumber;//电话号码
	private String level;//级别
	private String workPlace;//工作地点
	private String email;//邮箱
	private double totalSalary;//工资总额
	private double companyInsurance;//公司支付保险
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String leaveTime;//离职时间
	private String employeedDate;//入职时间
	@Id
	@Column(name="ID")
	public int getID() {
		return ID;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	@Column(name="职位")
	public String getPosition() {
		return position;
	}
	@Column(name="部门ID")
	public int getDepartmentId() {
		return departmentId;
	}
	@Column(name="性别")
	public String getSex() {
		return sex;
	}
	@Column(name="出生年月")
	public String getBorthDay() {
		return borthDay;
	}
	@Column(name="民族")
	public String getNation() {
		return nation;
	}
	@Column(name="学历")
	public String getDegree() {
		return degree;
	}
	@Column(name="毕业院校")
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	@Column(name="专业")
	public String getMajor() {
		return major;
	}
	@Column(name="联系电话")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	@Column(name="工作地点")
	public String getWorkPlace() {
		return workPlace;
	}
	@Column(name="邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name = "工资总额")
	public double getTotalSalary() {
		return totalSalary;
	}
	@Column(name = "公司支付保险")
	public double getCompanyInsurance() {
		return companyInsurance;
	}
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	@Column(name="离职时间")
	public String getLeaveTime() {
		return leaveTime;
	}
	@Column(name = "入职时间")
	public String getEmployeedDate() {
		return employeedDate;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public void setLevel(String level) {
		this.level = level;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public void setCompanyInsurance(double companyInsurance) {
		this.companyInsurance = companyInsurance;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setEmployeedDate(String employeedDate) {
		this.employeedDate = employeedDate;
	}
	
}
