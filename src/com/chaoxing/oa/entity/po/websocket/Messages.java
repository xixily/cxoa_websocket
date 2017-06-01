package com.chaoxing.oa.entity.po.websocket;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chaoxing.oa.entity.po.employee.UserName;


@Entity
@Table(name="t_messages", schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Messages implements Serializable {
	private static final long serialVersionUID = -2744046558124905576L;
	
	private Integer id;
	private Long lis_id;
//	private String lis_id;
	private String sender;
	private Integer sid;
	private Integer to;
	private String msg;
	private Date date;
	private Integer msg_type;
//	private Date createTime;
	private Integer status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Column
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id",unique = false,updatable = true)
	@Column(name="_to")
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
	}
	@Column
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Column(name="_date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column
	public Integer getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
//	@Column(updatable=false, insertable=false, columnDefinition=" timestamp NULL DEFAULT CURRENT_TIMESTAMP")
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
	@Column(name="_status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column
	public Long getLis_id() {
		return lis_id;
	}
	public void setLis_id(Long lis_id) {
		this.lis_id = lis_id;
	}
	
//	@Column
//	public String getLis_id() {
//		return lis_id;
//	}
//	public void setLis_id(String lis_id) {
//		this.lis_id = lis_id;
//	}
	
}
