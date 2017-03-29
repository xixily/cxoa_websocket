package com.chaoxing.oa.entity.po.commmon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@Table(name="统计架构表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CountStructure {
	private Integer id;
	private String name;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
