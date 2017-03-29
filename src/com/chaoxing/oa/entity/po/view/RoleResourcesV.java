package com.chaoxing.oa.entity.po.view;

import java.io.Serializable;

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

import com.chaoxing.oa.entity.po.system.Menu;
import com.chaoxing.oa.entity.po.system.UserRole;

@Entity
@Table(name="roleresources", schema="")
//@DynamicUpdate(true)
//@DynamicInsert(true)
public class RoleResourcesV implements Serializable {
	private static final long serialVersionUID = 1382136916950279442L;
	private Integer id;
	private Integer roleId;//角色ID
	private String roleName;//角色名
	private Integer roleLevel;//角色等级;
	private Integer menuId;
	private String menuName;
	private Integer menuLevel;
	private Integer preMenuId;
	private String url;
	private String iconCls;
	private String sortCode;
	
	@Id
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getRoleId() {
		return roleId;
	}
	@Column
	public String getRoleName() {
		return roleName;
	}
	@Column(name="rolevel")
	public Integer getRoleLevel() {
		return roleLevel;
	}
	@Column
	public Integer getMenuId() {
		return menuId;
	}
	@Column
	public String getMenuName() {
		return menuName;
	}
	@Column
	public Integer getMenuLevel() {
		return menuLevel;
	}
	@Column
	public Integer getPreMenuId() {
		return preMenuId;
	}
	@Column
	public String getUrl() {
		return url;
	}
	@Column
	public String getIconCls() {
		return iconCls;
	}
	@Column
	public String getSortCode() {
		return sortCode;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public void setPreMenuId(Integer preMenuId) {
		this.preMenuId = preMenuId;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	
	
}
