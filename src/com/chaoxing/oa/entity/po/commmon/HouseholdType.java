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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "户口性质", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HouseholdType implements Serializable {
	private static final long serialVersionUID = -435045961814033036L;
	private int id;
	private String householdType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "householdGenerator" , strategy = "native")
	public int getId() {
		return id;
	}
	@Column(name = "name")
	public String getHouseholdType() {
		return householdType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	
}
