package com.chaoxing.oa.entity.page.system;

import java.util.Date;

public class PLog {
	private Long id;
	private Integer userId;
	private String requestIp;
	private Date createdate;
	private String content;
	private String method;
	private String result;
	
	public Long getId() {
		return id;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public String getContent() {
		return content;
	}
	public String getMethod() {
		return method;
	}
	public String getResult() {
		return result;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
