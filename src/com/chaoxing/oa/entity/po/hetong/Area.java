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
@Table(name = "yy_area")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Area implements Serializable {
	
	
	private static final long serialVersionUID = 1652985477522863930L;
	private Integer area_id;
	private String area_name;
	private Integer area_order;
	private Integer parent_id;
	private String area_type;
	private Float area;
	private String quan;
	private String inputname;
	private Integer companyId;
	@Id
	@Column(name = "area_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	@Column(name = "area_name")
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	@Column(name = "area_order")
	public Integer getArea_order() {
		return area_order;
	}
	public void setArea_order(Integer area_order) {
		this.area_order = area_order;
	}
	@Column(name = "parent_id")
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	@Column(name = "area_type")
	public String getArea_type() {
		return area_type;
	}
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}
	@Column(name = "area")
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	@Column(name = "quan")
	public String getQuan() {
		return quan;
	}
	public void setQuan(String quan) {
		this.quan = quan;
	}
	@Column(name = "inputname")
	public String getInputname() {
		return inputname;
	}
	public void setInputname(String inputname) {
		this.inputname = inputname;
	}
	@Column(name = "companyId")
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
