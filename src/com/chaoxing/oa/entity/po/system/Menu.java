package com.chaoxing.oa.entity.po.system;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "菜单表", schema="")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Menu implements Serializable{
	private static final long serialVersionUID = 3894975781188319211L;
	private int menuId;
	private String menuName;
	private int menuLevel;
	private Menu preMenuId;
	private String url;
	private String iconCls;
	private String sortCode;
	private Set<Menu> menus = new TreeSet<Menu>();
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "m_tables_g",strategy = "native")
	public int getMenuId() {
		return menuId;
	}
	@Column
	public String getMenuName() {
		return menuName;
	}
	@Column
	public int getMenuLevel() {
		return menuLevel;
	}
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Menu.class)
	@JoinColumn(name = "preMenuId")
	public Menu getPreMenuId() {
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
	@Column(columnDefinition = "varchar(10) DEFAULT 'Z999'")
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="preMenuId")
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public void setPreMenuId(Menu preMenuId) {
		this.preMenuId = preMenuId;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
}
