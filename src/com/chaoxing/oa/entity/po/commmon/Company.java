package com.chaoxing.oa.entity.po.commmon;

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
@Table(name = "company", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Company implements Serializable {
	private static final long serialVersionUID = -8157506368747187395L;
	private int id; 
	private String cmopany;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@Column(name = "name")
	public String getCmopany() {
		return cmopany;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCmopany(String cmopany) {
		this.cmopany = cmopany;
	}
	
}
