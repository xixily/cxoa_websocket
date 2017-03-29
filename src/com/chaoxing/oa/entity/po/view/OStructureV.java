package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "架构图", schema = "")
public class OStructureV implements Serializable {
	private static final long serialVersionUID = -6107837677143669389L;
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
	private Integer level;//级别
	private Integer total;//总人数
	private Integer onJob;//在职
	
	@Id
	@Column
	public Integer getId() {
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
	@Column(name="三级")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name="四级")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	@Column(name="指导")
	public String getGuidance() {
		return guidance;
	}
	@Column(name="指导邮箱")
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	@Column(name="排序代码")
	public String getSortCode() {
		return sortCode;
	}
	@Column(name="报税架构")
	public String getTaxStructure() {
		return taxStructure;
	}
	@Column(name="preId")
	public Integer getPreId() {
		return preId;
	}
	@Column(name="级别")
	public Integer getLevel() {
		return level;
	}
	@Column(name="总人数")
	public Integer getTotal() {
		return total;
	}
	@Column(name="在职人数")
	public Integer getOnJob() {
		return onJob;
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
	
}
