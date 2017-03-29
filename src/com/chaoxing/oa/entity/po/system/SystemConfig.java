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
@Table(name="系统配置", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SystemConfig implements Serializable {
	private static final long serialVersionUID = 2716481175228661078L;
//	private int id;
	private String name;
	private byte locked;
//	private String locked;
	private String configType;
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GenericGenerator(name = "sysTable",strategy = "native")
//	public int getId() {
//		return id;
//	}
	@Id
	@Column(columnDefinition = "varchar(32)")
	public String getName() {
		return name;
	}
	@Id
	@Column(name = "类型",columnDefinition = "varchar(32)")
	public String getConfigType() {
		return configType;
	}
	@Column(name = "开关")
	public byte getLocked() {
		return locked;
	}

//	public void setId(int id) {
//		this.id = id;
//	}
	public void setName(String name) {
		this.name = name;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	
}
