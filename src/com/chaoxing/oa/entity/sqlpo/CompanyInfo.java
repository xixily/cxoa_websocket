package com.chaoxing.oa.entity.sqlpo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

//@Entity
//@Table(name = "公司代码")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CompanyInfo implements Serializable {
	private static final long serialVersionUID = 4475165160835401109L;
	private Integer id;
	private String name;
	private String address;
	private String telNumber;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "公司代码")
	public String getName() {
		return name;
	}
	@Column(name = "地址")
	public String getAddress() {
		return address;
	}
	@Column(name = "电话")
	public String getTelNumber() {
		return telNumber;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
}
