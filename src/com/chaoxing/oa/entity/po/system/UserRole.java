package com.chaoxing.oa.entity.po.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userrole", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserRole implements Serializable {
	private static final long serialVersionUID = -7906680425187234920L;
	private int roleId;
	private String roleName;
	private int roleLevel;
//	private Set<RoleMenu> roleMenus = new HashSet<RoleMenu>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "userRoleTableGenerator", strategy = "native")
	public int getRoleId() {
		return roleId;
	}
	@Column(name = "roleName")
	public String getRoleName() {
		return roleName;
	}
	@Column(name = "rolevel")
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
