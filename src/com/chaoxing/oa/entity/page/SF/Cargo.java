package com.chaoxing.oa.entity.page.SF;

public class Cargo {
	//Cargo（货物） 属性
	private String name;//货物名称(必填，生成电子运单必填)
	private Integer count;//货物数量（条件，跨境必填）
	private String unit;//货物单位（条件，跨境必填）
	private Float weight;//订单货物单位重量（条件，跨境必填）
	private String amount;//货物单价（条件，跨境必填）
	private String currency;//货物单价的币别： 
	private String source_area;//原产地国别（条件，跨境必填）
	private String product_record_no;//货物产品国检备案编号
	private String good_prepard_no;//商品海关备案号 
	public String getName() {
		return name;
	}
	public Integer getCount() {
		return count;
	}
	public String getUnit() {
		return unit;
	}
	public Float getWeight() {
		return weight;
	}
	public String getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getSource_area() {
		return source_area;
	}
	public String getProduct_record_no() {
		return product_record_no;
	}
	public String getGood_prepard_no() {
		return good_prepard_no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setSource_area(String source_area) {
		this.source_area = source_area;
	}
	public void setProduct_record_no(String product_record_no) {
		this.product_record_no = product_record_no;
	}
	public void setGood_prepard_no(String good_prepard_no) {
		this.good_prepard_no = good_prepard_no;
	}
	
}
