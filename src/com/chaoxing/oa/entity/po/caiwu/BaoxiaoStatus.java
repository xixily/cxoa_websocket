package com.chaoxing.oa.entity.po.caiwu;

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
@Table(name = "报销状态", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BaoxiaoStatus implements Serializable{
	private static final long serialVersionUID = 3214935649186502540L;
	private Integer status;
	private String name;
	private Integer prestatus;
	private Integer grade;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getStatus() {
		return status;
	}
	
	@Column
	public String getName() {
		return name;
	}
	
	@Column
	public Integer getPrestatus() {
		return prestatus;
	}
	
	@Column
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrestatus(Integer prestatus) {
		this.prestatus = prestatus;
	}
	
	
}
