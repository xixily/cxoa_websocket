package com.chaoxing.oa.entity.po.caiwu;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "审核聊天记录", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ChatMessage implements Serializable {
	private static final long serialVersionUID = -5429689086223472047L;
	private Long id;
	private Long bxid;
	private byte sender;
	private String msg;
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column
	public Long getBxid() {
		return bxid;
	}
	@Column
	public byte getSender() {
		return sender;
	}
	@Column
	public String getMsg() {
		return msg;
	}
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBxid(Long bxid) {
		this.bxid = bxid;
	}
	public void setSender(byte sender) {
		this.sender = sender;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
