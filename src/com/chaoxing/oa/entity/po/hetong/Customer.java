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
@Table(name = "用户列表")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Customer implements Serializable {
	private static final long serialVersionUID = 8947487369582440586L;
	private Integer id;
	private String customerName;
	private String oldName;
	private String agentCompany;
	private String province;
	private String city;
	private String xingzhi;
	private String remarks;
	@Id
	@Column(name = "自动编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "用户名称")
	public String getCustomerName() {
		return customerName;
	}
	@Column(name = "曾用名称")
	public String getOldName() {
		return oldName;
	}
	@Column(name = "代理公司")
	public String getAgentCompany() {
		return agentCompany;
	}
	@Column(name = "省份")
	public String getProvince() {
		return province;
	}
	@Column(name = "城市")
	public String getCity() {
		return city;
	}
	@Column(name = "性质")
	public String getXingzhi() {
		return xingzhi;
	}
	@Column(name = "备注")
	public String getRemarks() {
		return remarks;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
