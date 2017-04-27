package com.chaoxing.oa.entity.po.system;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private UserRole preRole;
//	private Set<RoleMenu> roleMenus = new HashSet<RoleMenu>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "userRoleTableGenerator", strategy = "native")
	public Integer getRoleId() {
		return roleId;
	}
	@Column(name = "roleName")
	public String getRoleName() {
		return roleName;
	}
	@Column(name = "rolevel")
	public Integer getRoleLevel() {
		return roleLevel;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "preId",unique = false,updatable = true)
	public UserRole getPreRole() {
		return preRole;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setPreRole(UserRole preRole) {
		this.preRole = preRole;
	}
	public UserRole(Integer roleId) {
		super();
		this.roleId = roleId;
	}
	public UserRole() {
		super();
	}
	
}
