package com.chaoxing.oa.entity.po.hetong;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "用户单位", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CustomerDepart implements Serializable {
	private static final long serialVersionUID = 1655213242440553685L;
	private Integer id;
	private Integer dId;
	private String customerName;
	private String province;
	private String area;
	private String xingzhi;
	private String remarks;
	private String startDate;
	private String lastDate;
	private String category;
	private String charger;
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String email;
	private String cemail;
	private String gemail;
	private String hemail;
	@Id
	@Column(name = "自动编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "单位编号")
	public Integer getdId() {
		return dId;
	}
	@Column(name = "用户名称")
	public String getCustomerName() {
		return customerName;
	}
	@Column(name = "省份")
	public String getProvince() {
		return province;
	}
	@Column(name = "大区")
	public String getArea() {
		return area;
	}
	@Column(name = "性质")
	public String getXingzhi() {
		return xingzhi;
	}
	@Column(name = "备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name = "开始合作年份")
	public String getStartDate() {
		return startDate;
	}
	@Column(name = "最后合作年份")
	public String getLastDate() {
		return lastDate;
	}
	@Column(name = "类别")
	public String getCategory() {
		return category;
	}
	@Column(name = "负责人")
	public String getCharger() {
		return charger;
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
	@Column(name="销售邮箱")
	public String getEmail() {
		return email;
	}
	@Column(name="细胞核邮箱")
	public String getCemail() {
		return cemail;
	}
	@Column(name="省级指导邮箱")
	public String getGemail() {
		return gemail;
	}
	@Column(name="行业指导邮箱")
	public String getHemail() {
		return hemail;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGemail(String gemail) {
		this.gemail = gemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public void setHemail(String hemail) {
		this.hemail = hemail;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setCharger(String charger) {
		this.charger = charger;
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
	
}
