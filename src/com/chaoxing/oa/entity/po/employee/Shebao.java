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
@Table(name="社保公司缴费比例表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Shebao implements Serializable {
	private static final long serialVersionUID = 5460594747955944772L;
	private int sid ;
	private String company;
	private String shebaoType;
	private Integer typeCode;
	private Float radixMin;
	private Float radixMax;
	private Float cRadio;
	private Float radio;
	private Float cFixedValue;
	private Float fixedValue;
	private String householdType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "shebaotable",strategy = "native")
	public int getSid() {
		return sid;
	}
	@Column(name = "公司名称")
	public String getCompany() {
		return company;
	}
	@Column(name = "社保类型")
	public String getShebaoType() {
		return shebaoType;
	}
	@Column(name = "类型编号")
	public int getTypeCode() {
		return typeCode;
	}
	@Column(name = "基数下限")
	public Float getRadixMin() {
		return radixMin;
	}
	@Column(name = "基数上限")
	public Float getRadixMax() {
		return radixMax;
	}
	@Column(name = "单位比例")
	public Float getcRadio() {
		return cRadio;
	}
	@Column(name = "个人比例")
	public Float getRadio() {
		return radio;
	}
	@Column(name = "单位固定值")
	public Float getcFixedValue() {
		return cFixedValue;
	}
	@Column(name = "个人固定值")
	public Float getFixedValue() {
		return fixedValue;
	}
	@Column(name = "户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setShebaoType(String shebaoType) {
		this.shebaoType = shebaoType;
	}
	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
	public void setRadixMin(Float radixMin) {
		this.radixMin = radixMin;
	}
	public void setRadixMax(Float radixMax) {
		this.radixMax = radixMax;
	}
	public void setcRadio(Float cRadio) {
		this.cRadio = cRadio;
	}
	public void setRadio(Float radio) {
		this.radio = radio;
	}
	public void setcFixedValue(Float cFixedValue) {
		this.cFixedValue = cFixedValue;
	}
	public void setFixedValue(Float fixedValue) {
		this.fixedValue = fixedValue;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	
}
