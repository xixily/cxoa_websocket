package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "员工工资汇总表")
public class EmployeeTotalWage implements Serializable {
	private static final long serialVersionUID = 7342726064541945668L;
	private int employeeId;//职员编号
	private double totalSalary;//工资总额
	private double selfInsurance;//保险自付金额
	private double companyInsurance;//公司支付保险
	@Id
	@Column(name = "职员编号")
	public int getEmployeeId() {
		return employeeId;
	}
	@Column(name = "工资总额")
	public double getTotalSalary() {
		return totalSalary;
	}
	@Column(name = "保险自付金额")
	public double getSelfInsurance() {
		return selfInsurance;
	}
	@Column(name = "公司支付保险")
	public double getCompanyInsurance() {
		return companyInsurance;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public void setSelfInsurance(double selfInsurance) {
		this.selfInsurance = selfInsurance;
	}
	public void setCompanyInsurance(double companyInsurance) {
		this.companyInsurance = companyInsurance;
	}
	
}
