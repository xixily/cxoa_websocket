package com.chaoxing.oa.entity.page.common;

import java.util.Set;

import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;

public class POStructs implements Comparable<POStructs>{
	private int departmentId;//部门ID
	private int id;//部门id
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String guidance;//指导
	private String guidanceEmail;//指导邮箱
	private String sortCode;//排序代码
	private String taxStructure;//报税架构
	private Integer preId;//preId 父级id
	private Integer parentId;//
	private Set<POStructs> children;
	private int level;//级别
	private String state = "closed";
	public int getDepartmentId() {
		return departmentId;
	}
	public int getId() {
		return id;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getCellCore() {
		return cellCore;
	}
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	public String getGuidance() {
		return guidance;
	}
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	public String getSortCode() {
		return sortCode;
	}
	public String getTaxStructure() {
		return taxStructure;
	}
	public Integer getPreId() {
		return preId;
	}
	public Set<POStructs> getChildren() {
		return children;
	}
	public int getLevel() {
		return level;
	}
	public String getState() {
		return state;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setId(int id) {
		this.id = id;
		this.departmentId = id;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setPreId(Integer preId) {
		this.preId = preId;
		this.parentId = preId;
	}
	public void setChildren(Set<POStructs> children) {
		this.children = children;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int compareTo(POStructs o) {
		if(this.sortCode!=null&&o.getSortCode()!=null){
			return this.sortCode.compareTo(o.getSortCode());
		}
		return -1;
	}
	
	
}
