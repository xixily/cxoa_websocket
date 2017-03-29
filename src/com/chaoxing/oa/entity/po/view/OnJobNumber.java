package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "在职人数")
public class OnJobNumber implements Serializable {
	private static final long serialVersionUID = -1259639201854748242L;
	private int departmentId;
	private int onJobNumber;
	@Id
	@Column(name = "部门ID")
	public int getDepartmentId() {
		return departmentId;
	}
	@Column(name = "在职人数")
	public int getOnJobNumber() {
		return onJobNumber;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setOnJobNumber(int onJobNumber) {
		this.onJobNumber = onJobNumber;
	}
	
}
