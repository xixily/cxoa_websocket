package com.chaoxing.oa.entity.page.pub.caiwu;

public class PKoukuan {
	private Long id;
	private Long bxid;
	private Integer order;
	private String item;
	private Float money;
	private String description;
	
	public Long getId() {
		return id;
	}
	public Long getBxid() {
		return bxid;
	}
	public Integer getOrder() {
		return order;
	}
	public String getItem() {
		return item;
	}
	public Float getMoney() {
		return money;
	}
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
