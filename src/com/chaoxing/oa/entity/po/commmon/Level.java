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
@Table(name = "level", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Level implements Serializable {
	private static final long serialVersionUID = -6020261078135635466L;
	private int id;
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
