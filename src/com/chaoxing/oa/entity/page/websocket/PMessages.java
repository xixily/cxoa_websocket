package com.chaoxing.oa.entity.page.websocket;

import java.util.Date;

public class PMessages {
	public final static int NORMAL_MESSAGES = 100;
	public final static int SYSTEM_MESSAGES = 110;
	public final static int SYSTEM_CLEAR_ORDER = 111;
	public final static int SYSTEM_ALL_RECORDS = 118;
	public final static int SYSTEM_MANAGE = 119;
	public final static int CW_SH_MESSAGES = 301;
	public final static int HEART_BEAT = 1;
	public final static int GET_SH_MESSAGES = 114;
	
//	private String lis_id;
	private Long lis_id;
	private String sender;
	private Integer sid;
	private Integer to;
	private String msg;
	private Date date;
	private Integer msg_type;
	private Integer ownerId;
	private Integer status;
	
	
	public Long getLis_id() {
		return lis_id;
	}
	public void setLis_id(Long lis_id) {
		this.lis_id = lis_id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public static int getCwShMessages() {
		return CW_SH_MESSAGES;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
