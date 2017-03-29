package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "当月考勤视图")
public class MonthKaoqinV implements Serializable {
	private static final long serialVersionUID = 4582459032068693908L;
	private Integer id;
	private Integer userId;
	private String username;
	private String firstLevel;//公司
	private String secondLevel;//部门
	private String thirdLevel;//岗位
	private String fourthLevel;//小组
	private String taxStructure;//报税架构
	private String identityCard;//身份号
	private BigDecimal chuqinDay;//出勤天数
	private Integer zhuanzhengChaeDay;//转正天数
	private BigDecimal chidaoYingkouDay;//迟到应扣天数
	private BigDecimal shiJiaHour;//事假时数
	private BigDecimal bingJiaHour;//病假时数
	private BigDecimal hunJiaDay;//婚假天数
	private BigDecimal chanJiaDay;//产假天数
	private BigDecimal sangJiaDay;//丧假天数
	private BigDecimal nianJiaDay;//年假天数
	private BigDecimal kuangGongHour;//旷工时数
	private String hiredate;//入职时间
	private String ruzhiReport;//入职报表
	private String leaveTime;//离职时间
	private String lizhiReport;//离职报表
	private String zhuanzhengTime;//转正时间
	private String zhuanzhengReport;//转正报表
	private String kaoQinremarks;//考勤备注
	private String level;//级别
	private BigDecimal sickLleaveTotal;//病假累计
	private BigDecimal annualLleave;//年假累计
	@Id
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getUserId() {
		return userId;
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
	@Column(name="报税架构")
	public String getTaxStructure() {
		return taxStructure;
	}
	@Column(name="身份证号")
	public String getIdentityCard() {
		return identityCard;
	}
	@Column(name="出勤天数")
	public BigDecimal getChuqinDay() {
		return chuqinDay;
	}
	@Column(name="转正天数")
	public Integer getZhuanzhengChaeDay() {
		return zhuanzhengChaeDay;
	}
	@Column(name="迟到应扣天数")
	public BigDecimal getChidaoYingkouDay() {
		return chidaoYingkouDay;
	}
	@Column(name="事假小时数")
	public BigDecimal getShiJiaHour() {
		return shiJiaHour;
	}
	@Column(name="病假小时数")
	public BigDecimal getBingJiaHour() {
		return bingJiaHour;
	}
	@Column(name="婚假天数")
	public BigDecimal getHunJiaDay() {
		return hunJiaDay;
	}
	@Column(name="产假天数")
	public BigDecimal getChanJiaDay() {
		return chanJiaDay;
	}
	@Column(name="丧假天数")
	public BigDecimal getSangJiaDay() {
		return sangJiaDay;
	}
	@Column(name="年假天数")
	public BigDecimal getNianJiaDay() {
		return nianJiaDay;
	}
	@Column(name="旷工小时数")
	public BigDecimal getKuangGongHour() {
		return kuangGongHour;
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
	public String getLeaveTime() {
		return leaveTime;
	}
	@Column(name="转正报表")
	public String getLizhiReport() {
		return lizhiReport;
	}
	@Column(name="离职时间")
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	@Column(name="离职报表")
	public String getZhuanzhengReport() {
		return zhuanzhengReport;
	}
	@Column(name="考勤备注")
	public String getKaoQinremarks() {
		return kaoQinremarks;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	@Column(name="年假累计")
	public BigDecimal getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	@Column(name="病假累计")
	public BigDecimal getAnnualLleave() {
		return annualLleave;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setChuqinDay(BigDecimal chuqinDay) {
		this.chuqinDay = chuqinDay;
	}
	public void setZhuanzhengChaeDay(Integer zhuanzhengChaeDay) {
		this.zhuanzhengChaeDay = zhuanzhengChaeDay;
	}
	public void setChidaoYingkouDay(BigDecimal chidaoYingkouDay) {
		this.chidaoYingkouDay = chidaoYingkouDay;
	}
	public void setShiJiaHour(BigDecimal shiJiaHour) {
		this.shiJiaHour = shiJiaHour;
	}
	public void setBingJiaHour(BigDecimal bingJiaHour) {
		this.bingJiaHour = bingJiaHour;
	}
	public void setHunJiaDay(BigDecimal hunJiaDay) {
		this.hunJiaDay = hunJiaDay;
	}
	public void setChanJiaDay(BigDecimal chanJiaDay) {
		this.chanJiaDay = chanJiaDay;
	}
	public void setSangJiaDay(BigDecimal sangJiaDay) {
		this.sangJiaDay = sangJiaDay;
	}
	public void setNianJiaDay(BigDecimal nianJiaDay) {
		this.nianJiaDay = nianJiaDay;
	}
	public void setKuangGongHour(BigDecimal kuangGongHour) {
		this.kuangGongHour = kuangGongHour;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setRuzhiReport(String ruzhiReport) {
		this.ruzhiReport = ruzhiReport;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setLizhiReport(String lizhiReport) {
		this.lizhiReport = lizhiReport;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	public void setZhuanzhengReport(String zhuanzhengReport) {
		this.zhuanzhengReport = zhuanzhengReport;
	}
	public void setKaoQinremarks(String kaoQinremarks) {
		this.kaoQinremarks = kaoQinremarks;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setSickLleaveTotal(BigDecimal sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	public void setAnnualLleave(BigDecimal annualLleave) {
		this.annualLleave = annualLleave;
	}
	
	
	
}
