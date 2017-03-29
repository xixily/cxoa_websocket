package com.chaoxing.oa.entity.po.system;

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

@Entity
@Table(name="角色资源", schema="")
@DynamicUpdate(true)
@DynamicInsert(true)
public class RoleResources implements Serializable {
	private static final long serialVersionUID = 1382136916950279442L;
	private int id;
	private UserRole roleId;
	private Menu menuId;
//	private String url;
//	private String sortCode;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="roleresources_table_g",strategy="native")
	public int getId() {
		return id;
	}
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=UserRole.class)
	@JoinColumn(name="roleId")
	public UserRole getRoleId() {
		return roleId;
	}
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Menu.class)
	@JoinColumn(name="menuId")
	public Menu getMenuId() {
		return menuId;
	}
//	@Column
//	public String getUrl() {
//		return url;
//	}
//	@Column
//	public String getSortCode() {
//		return sortCode;
//	}
//	public void setSortCode(String sortCode) {
//		this.sortCode = sortCode;
//	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRoleId(UserRole roleId) {
		this.roleId = roleId;
	}
	public void setMenuId(Menu menuId) {
		this.menuId = menuId;
	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
	
}
