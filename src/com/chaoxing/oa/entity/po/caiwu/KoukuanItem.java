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
@Table(name = "扣借款表", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class KoukuanItem implements Serializable {
	private static final long serialVersionUID = -4603513198636471784L;
	private Long id;
	private Long bxid;
	private Integer order;
	private String item;
	private Float money;
	private String description;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column
	public Long getBxid() {
		return bxid;
	}
	@Column(name="order_")
	public Integer getOrder() {
		return order;
	}
	@Column
	public String getItem() {
		return item;
	}
	@Column
	public Float getMoney() {
		return money;
	}
	@Column
	public String getDescription() {
		return description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBxid(Long bxid) {
		this.bxid = bxid;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
