package com.chaoxing.oa.entity.po.employee;

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
 * entity 人事权限表(权限范围)
 *
 */
@Entity
@Table(name="人事权限", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RenshiRights implements Serializable {
	private static final long serialVersionUID = 8832579790637183264L;
	private int id;
	private String firstLevel;// 一级
	private String secondLevel;// 二级
	private String renshiRight;// 人事权限
	private String userIds;//人事id

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "renshiRightsTableGenerator", strategy = "native")
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	@Column(name="一级")
	public String getFirstLevel() {
		return firstLevel;
	}

	@Column(name="二级")
	public String getSecondLevel() {
		return secondLevel;
	}

	@Column(name="人事权限")
	public String getRenshiRight() {
		return renshiRight;
	}
	@Column(name = "人事id")
	public String getUserIds() {
		return userIds;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}

	public void setRenshiRight(String renshiRight) {
		this.renshiRight = renshiRight;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public RenshiRights() {
		super();
	}

}
