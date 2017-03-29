package com.chaoxing.oa.entity.po.commmon;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "工作日计算表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WagesDate implements Serializable{
	private static final long serialVersionUID = 935541893215138237L;
	private String date;
	private int ruzhiDay;
	private int lizhiDay;
	@Id
	@Column(name = "日期")
	public String getDate() {
		return date;
	}
	@Column(name = "入职工作日")
	public int getRuzhiDay() {
		return ruzhiDay;
	}
	@Column(name = "离职工作日")
	public int getLizhiDay() {
		return lizhiDay;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRuzhiDay(int ruzhiDay) {
		this.ruzhiDay = ruzhiDay;
	}
	public void setLizhiDay(int lizhiDay) {
		this.lizhiDay = lizhiDay;
	}
	
}
