package com.chaoxing.oa.entity.page.common;

public class POStructV {
	private Integer id;//部门id
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
	private Integer _parentId;//preId 父级id
	private Integer level;//级别
	private Integer total;//总人数
	private Integer onJob;//在职
	private String state = "closed";
	public Integer getId() {
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
	public Integer get_parentId() {
		return _parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public Integer getTotal() {
		return total;
	}
	public Integer getOnJob() {
		return onJob;
	}
	public String getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
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
		this._parentId = preId;
	}
	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public void setOnJob(Integer onJob) {
		this.onJob = onJob;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
