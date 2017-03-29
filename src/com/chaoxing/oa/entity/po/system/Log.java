package com.chaoxing.oa.entity.po.system;

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
@Table(name = "t_log")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Log implements Serializable {
	private static final long serialVersionUID = -5798260232290127856L;
	private Long id;
	private Integer userId;
	private String requestIp;
	private Date createdate;
	private String content;
	private String method;
	private String result;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column
	public Integer getUserId() {
		return userId;
	}

	@Column
	public String getRequestIp() {
		return requestIp;
	}

	@Column(insertable=false,updatable=false)
	public Date getCreatedate() {
		return createdate;
	}

	@Column
	public String getContent() {
		return content;
	}

	@Column
	public String getMethod() {
		return method;
	}

	@Column
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
