package com.chaoxing.oa.entity.page.common;

public class POStruct {
	private Integer id;//部门id
	private Integer level;//级别
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
//	private Integer parentId;//
	private String state = "closed";
	private String total;//1024(在职)/2048(总人数)
	public int getId() {
		return id;
	}
	public int getLevel() {
		return level;
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
	public String getState() {
		return state;
	}
	public String getTotal() {
		return total;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLevel(int level) {
		this.level = level;
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
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
