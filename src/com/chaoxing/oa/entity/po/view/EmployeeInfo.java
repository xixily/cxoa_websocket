package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "员工查看信息表")
public class EmployeeInfo implements Serializable {
	private static final long serialVersionUID = 3248473467577798009L;
	private String username;//用户姓名
	private int userId;
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
	private String registeredAddress;//户口地址
	private String Postcode;//邮编
	private String level;//级别
	private String currentAddress;//现居住地
	private String workPlace;//工作地点
	private String email;//邮箱
	private String relationship;//关系
	private double totalSalary;//工资总额
	private double selfInsurance;//保险自付额
	private String cellCore;//细胞核
	private String cellEmail;//细胞核
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	@Id
	@Column(name = "ID")
	public int getUserId() {
		return userId;
	}
	@Column(name = "职位")
	public String getPosition() {
		return position;
	}
	@Column(name = "性别")
	public String getSex() {
		return sex;
	}
	@Column(name = "身份证号")
	public String getIdentityCard() {
		return identityCard;
	}
	@Column(name = "出生年月")
	public String getBorthDay() {
		return borthDay;
	}
	@Column(name = "民族")
	public String getNation() {
		return nation;
	}
	@Column(name = "学历")
	public String getDegree() {
		return degree;
	}
	@Column(name = "毕业院校")
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	@Column(name = "专业")
	public String getMajor() {
		return major;
	}
	@Column(name = "联系电话")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@Column(name = "家庭地址")
	public String getHomeAddress() {
		return homeAddress;
	}
	@Column(name = "家庭电话")
	public String getHomeNumber() {
		return homeNumber;
	}
	@Column(name = "户口地址")
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	@Column(name = "邮编")
	public String getPostcode() {
		return Postcode;
	}
	@Column(name = "级别")
	public String getLevel() {
		return level;
	}
	@Column(name = "现居住地")
	public String getCurrentAddress() {
		return currentAddress;
	}
	@Column(name = "工作地点")
	public String getWorkPlace() {
		return workPlace;
	}
	@Column(name = "邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name = "关系")
	public String getRelationship() {
		return relationship;
	}
	@Column(name = "工资总额")
	public double getTotalSalary() {
		return totalSalary;
	}
	@Column(name = "保险自付金额")
	public double getSelfInsurance() {
		return selfInsurance;
	}
	@Column(name = "细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name = "细胞核邮箱")
	public String getCellEmail() {
		return cellEmail;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public void setPostcode(String postcode) {
		Postcode = postcode;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public void setSelfInsurance(double selfInsurance) {
		this.selfInsurance = selfInsurance;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellEmail(String cellEmail) {
		this.cellEmail = cellEmail;
	}
	
}
