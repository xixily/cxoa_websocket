/*package com.chaoxing.oa.entity.page.common;

import java.util.Set;



public class Pstruct implements Comparable<Pstruct>{
	private int id;
	private String name;
	private int level;
	private Pstruct preStruct;
	private String leader;
	private String email;
	private String sortCode;
	private String firstLevel;
	private String secondLevel;
	private String thirdLevel;
	private Set<Pstruct> children;
	private String state = "closed";
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public Pstruct getPreStruct() {
		return preStruct;
	}

	public String getLeader() {
		return leader;
	}

	public String getEmail() {
		return email;
	}

	public String getSortCode() {
		return sortCode;
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

	public Set<Pstruct> getChildren() {
		return children;
	}

	public String getState() {
		return state;
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

	public void setPreStruct(Pstruct preStruct) {
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

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}

	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}

	public void setChildren(Set<Pstruct> children) {
		this.children = children;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int compareTo(Pstruct o) {
		if(this.sortCode!=null&&o.getSortCode()!=null){
			return this.sortCode.compareTo(o.getSortCode());
		}
		return -1;
	}
}
*/