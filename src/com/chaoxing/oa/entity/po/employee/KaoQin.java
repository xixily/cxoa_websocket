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
@Table(name = "考勤表")
@DynamicUpdate(true)
@DynamicInsert(true)
public class KaoQin implements Serializable {
	private static final long serialVersionUID = 5561946651214317397L;
	private Integer id;
	private Integer userId;
	private String username;
	private Integer departmentId;//小组ID（部门ID）
	private String firstLevel;//公司
	private String secondLevel;//部门
	private String thirdLevel;//岗位
	private String fourthLevel;//小组
	private String identityCard;//身份号
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
	private String level;//级别
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "kaoqintable_generrator", strategy="native")
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getUserId() {
		return userId;
	}
	@Column(name = "部门ID")
	public Integer getDepartmentId() {
		return departmentId;
	}
	@Column
	public String getUsername() {
		return username;
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
	@Column(name = "身份证号")
	public String getIdentityCard() {
		return identityCard;
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
	@Column(name = "出勤天数", columnDefinition = "float default 21")
	public Float getChuqinDay() {
		return chuqinDay;
	}
	@Column(name = "转正差额天数", columnDefinition = "float default 0")
	public Float getZhuanzhengChaeDay() {
		return zhuanzhengChaeDay;
	}
	@Column(name = "迟到应扣天数", columnDefinition = "float default 0")
	public Float getChidaoYingkouDay() {
		return chidaoYingkouDay;
	}
	@Column(name = "事假小时数", columnDefinition = "float default 0")
	public Float getShiJiaHour() {
		return shiJiaHour;
	}
	@Column(name = "病假小时数", columnDefinition = "float default 0")
	public Float getBingJiaHour() {
		return bingJiaHour;
	}
	@Column(name = "旷工小时数", columnDefinition = "float default 0")
	public Float getKuangGongHour() {
		return kuangGongHour;
	}
	@Column(name = "婚假天数", columnDefinition = "float default 0")
	public Float getHunJiaDay() {
		return hunJiaDay;
	}
	@Column(name = "产假天数", columnDefinition = "float default 0")
	public Float getChanJiaDay() {
		return chanJiaDay;
	}
	@Column(name = "丧假天数", columnDefinition = "float default 0")
	public Float getSangJiaDay() {
		return sangJiaDay;
	}
	@Column(name = "年假天数", columnDefinition = "float default 0")
	public Float getNianJiaDay() {
		return nianJiaDay;
	}
	@Column(name = "考勤备注", columnDefinition = "float default 0")
	public String getKaoQinremarks() {
		return kaoQinremarks;
	}
	@Column(name="级别")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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
	
}
