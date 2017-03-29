package com.chaoxing.oa.entity.page.websocket;

import java.util.Date;

public class Messages {
	public final static int NORMAL_MESSAGES = 100;
	public final static int SYSTEM_MESSAGES = 110;
	public final static int HEART_BEAT = 1;
	
	private String sender;
	private Long sid;
	private Long to;
	private String msg;
	private Date date;
	private Integer msg_type;
	
	public String getSender() {
		return sender;
	}
	public Long getSid() {
		return sid;
	}
	public Long getTo() {
		return to;
	}
	public String getMsg() {
		return msg;
	}
	public Date getDate() {
		return date;
	}
	public Integer getMsg_type() {
		return msg_type;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public void setTo(Long to) {
		this.to = to;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	
}
