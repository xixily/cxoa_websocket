package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "异动表", schema = "")
public class Yidong implements Serializable {
	private static final long serialVersionUID = 2835238684023263620L;
	private Integer id;
	private String username;
	private String firstLevel;
	private String secondLevel;
	private String thirdLevel;
	private String fourthLevel;
	private String position;
	private String company;//公司名称
	private String hiredate;//入职时间
	private String ruzhiReport;//入职报表
	private String zhuanzhengDate;//转正时间
	private String zhuanzhengReport;//转正报表
	private String lizhiDate;//离职时间
	private String lizhiReport;//离职报表
	private String bumentiaozhengReport;//部门调整报表
	private String s_remarks;//涨薪记录
	private String remarks;//备注
	private String ifSecret;//是否保密
	private Float totalSalary;//工资汇总
	private String level;
	private Float lishiSalary;
	
	@Id
	@Column(name="职员编号")
	public Integer getId() {
		return id;
	}
	@Column
	public String getUsername() {
		return username;
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
	@Column(name="职位")
	public String getPosition() {
		return position;
	}
	@Column(name="公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name="入职时间")
	public String getHiredate() {
		return hiredate;
	}
	@Column(name="入职报表")
	public String getRuzhiReport() {
		return ruzhiReport;
	}
	@Column(name="转正时间")
	public String getZhuanzhengDate() {
		return zhuanzhengDate;
	}
	@Column(name="转正报表")
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	@Column(name="离职时间")
	public String getLizhiDate() {
		return lizhiDate;
	}
	@Column(name="离职报表")
	public String getLizhiReport() {
		return lizhiReport;
	}
	@Column(name="部门调整报表")
	public String getBumentiaozhengReport() {
		return bumentiaozhengReport;
	}
	@Column(name="涨薪记录")
	public String getS_remarks() {
		return s_remarks;
	}
	@Column(name="备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name="工资保密")
	public String getIfSecret() {
		return ifSecret;
	}
	@Column
	public Float getTotalSalary() {
		return totalSalary;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	@Column(name="历史工资")
	public Float getLishiSalary() {
		return lishiSalary;
	}
	public void setLishiSalary(Float lishiSalary) {
		this.lishiSalary = lishiSalary;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public void setCompany(String company) {
		this.company = company;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setRuzhiReport(String ruzhiReport) {
		this.ruzhiReport = ruzhiReport;
	}
	public void setZhuanzhengDate(String zhuanzhengDate) {
		this.zhuanzhengDate = zhuanzhengDate;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public void setLizhiDate(String lizhiDate) {
		this.lizhiDate = lizhiDate;
	}
	public void setLizhiReport(String lizhiReport) {
		this.lizhiReport = lizhiReport;
	}
	public void setBumentiaozhengReport(String bumentiaozhengReport) {
		this.bumentiaozhengReport = bumentiaozhengReport;
	}
	public void setS_remarks(String s_remarks) {
		this.s_remarks = s_remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
	}
	public void setTotalSalary(Float totalSalary) {
		this.totalSalary = totalSalary;
	}
	
	
}
