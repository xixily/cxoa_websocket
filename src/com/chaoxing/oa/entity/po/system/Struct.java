/*package com.chaoxing.oa.entity.po.system;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "结构", schema="")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Struct implements Serializable {
	private static final long serialVersionUID = -4967709880704637176L;
	private int id;
	private String name;
	private int level;
	private Struct preStruct;
	private String leader;
	private String email;
	private String sortCode;
	private String firstLevel;
	private String secondLevel;
	private String thirdLevel;
	private String taxStructure;
	private Set<Struct> children;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@Column(name="name",columnDefinition="varchar(32)")
	public String getName() {
		return name;
	}
	@Column(name="级别",columnDefinition="int default 1")
	public int getLevel() {
		return level;
	}
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Struct.class)
	@JoinColumn(name = "preStruct")
	public Struct getPreStruct() {
		return preStruct;
	}
	@Column(name= "leader",columnDefinition="varchar(32)")
	public String getLeader() {
		return leader;
	}
	@Column(name = "email",columnDefinition="varchar(60)")
	public String getEmail() {
		return email;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="preStruct")
	public Set<Struct> getChildren() {
		return children;
	}
	@Column(name="排序代码",columnDefinition="varchar(10) default 'ZZZZZZ'")
	public String getSortCode() {
		return sortCode;
	}
	@Column(name="一级",columnDefinition="varchar(32)")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name="二级",columnDefinition="varchar(32)")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name="三级",columnDefinition="varchar(32)")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name="报税架构",columnDefinition="varchar(32)")
	public String getTaxStructure() {
		return taxStructure;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setPreStruct(Struct preStruct) {
		this.preStruct = preStruct;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public void setChildren(Set<Struct> children) {
		this.children = children;
	}

}
*/