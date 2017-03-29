package com.chaoxing.oa.entity.po.hetong;

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
@Table(name = "合同分项报价")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ItemPrice implements Serializable{
	private static final long serialVersionUID = 7691804956436796202L;
	private Integer id;
	private Integer ctid;
	private String name;
	private Float money;
	private Float amount;
	private String begain;
	private String end;
	private String luku;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "合同编号")
	public Integer getCtid() {
		return ctid;
	}
	@Column(name = "分项名称")
	public String getName() {
		return name;
	}
	@Column(name = "分项金额")
	public Float getMoney() {
		return money;
	}
	@Column(name = "数量")
	public Float getAmount() {
		return amount;
	}
	@Column(name = "开始时间")
	public String getBegain() {
		return begain;
	}
	@Column(name = "截止时间")
	public String getEnd() {
		return end;
	}
	@Column(name = "录库人")
	public String getLuku() {
		return luku;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCtid(Integer ctid) {
		this.ctid = ctid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public void setBegain(String begain) {
		this.begain = begain;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public void setLuku(String luku) {
		this.luku = luku;
	}
	
	
}
