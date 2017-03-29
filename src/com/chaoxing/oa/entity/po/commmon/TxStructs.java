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
@Table(name="报税架构", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TxStructs implements Serializable {
	private static final long serialVersionUID = 897129822262942391L;
	private Integer id;
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
