package com.chaoxing.oa.entity.page.system;

public class PUserRole {
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private Integer preId;
	private String state;
	private Integer _parentId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public Integer getPreId() {
		return preId;
	}
	public void setPreId(Integer preId) {
		this.preId = preId;
		this._parentId = this.preId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer get_parentId() {
		return _parentId;
	}
	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}
	
}
