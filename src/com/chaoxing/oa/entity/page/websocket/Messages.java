package com.chaoxing.oa.entity.page.websocket;

import java.util.Date;

public class Messages {
	public final static int NORMAL_MESSAGES = 100;
	public final static int SYSTEM_MESSAGES = 110;
	public final static int HEART_BEAT = 1;
	
	private String sender;
	private Integer sid;
	private Integer to;
	private String msg;
	private Date date;
	private Integer msg_type;
	
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
	
	
}
