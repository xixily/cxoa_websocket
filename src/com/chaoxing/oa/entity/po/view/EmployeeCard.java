package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "员工卡号")
public class EmployeeCard implements Serializable {
	private static final long serialVersionUID = 5266114739784733437L;
	private int employeeId;//职员编号
	private String accountBank;//开户行
	private String account;//职工帐号
	private String salaryCode;//工资编号
	@Id
	@Column(name = "职员编号")
	public int getEmployeeId() {
		return employeeId;
	}
	@Column(name="开户行")
	public String getAccountBank() {
		return accountBank;
	}
	@Column(name="职工帐号")
	public String getAccount() {
		return account;
	}
	@Column(name="工资编号")
	public String getSalaryCode() {
		return salaryCode;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
	}
}
