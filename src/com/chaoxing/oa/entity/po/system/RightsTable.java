package com.chaoxing.oa.entity.po.system;

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

/**
 * entity 权限表(人事权限等级)
 *
 */
@Entity
@Table(name="权限表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RightsTable implements Serializable{
	private static final long serialVersionUID = -7627755427424371892L;
	private int id;
	private String name;//姓名
	private int userId;//姓名ID
	private String renshiRight;//人事权限
	private String financialRight;//财务权限
	private String agreementRight;//合同权限
	private String fixedassets;//固定资产权限
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "rightsTableGenerator", strategy = "native")
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	@Column(name = "姓名")
	public String getName() {
		return name;
	}
	@Column(name="userId")
	public int getUserId() {
		return userId;
	}
	@Column(name = "人事权限")
	public String getRenshiRight() {
		return renshiRight;
	}
	@Column(name = "财务权限")
	public String getFinancialRight() {
		return financialRight;
	}
	@Column(name = "合同权限")
	public String getAgreementRight() {
		return agreementRight;
	}
	@Column(name = "固定资产权限")
	public String getFixedassets() {
		return fixedassets;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRenshiRight(String renshiRight) {
		this.renshiRight = renshiRight;
	}
	public void setFinancialRight(String financialRight) {
		this.financialRight = financialRight;
	}
	public void setAgreementRight(String agreementRight) {
		this.agreementRight = agreementRight;
	}
	public void setFixedassets(String fixedassets) {
		this.fixedassets = fixedassets;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public RightsTable() {
		super();
	}
	
	}