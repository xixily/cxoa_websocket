package com.chaoxing.oa.entity.page.employee;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PYidongConfirm {
	private Integer id;
	private Integer uid;
	private String username;
	private String email;
	private String cellEmail;
	private String guidanceEmail;
	private String ydStatus;
	private Boolean confirm;
	@DateTimeFormat(pattern="yyyy.MM.dd")
	@JsonFormat(pattern="yyyy.MM.dd",timezone="GMT+8")
	private Date cuishou;
	private Boolean shebao;
	
	//合同信息
	private Boolean contractUser;
	private Boolean contractDue;
	private Boolean contractNoback;
	private String contractRemarks;
	
	//保证金信息
	private Boolean baozhengjin;
	private String bzjRemar;
	
	//财务信息
	private Boolean caiwuJk;
	private Boolean fapiaoNoback;
	private Boolean rent;
	private String caiwuRemarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellEmail() {
		return cellEmail;
	}
	public void setCellEmail(String cellEmail) {
		this.cellEmail = cellEmail;
	}
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}
	public String getYdStatus() {
		return ydStatus;
	}
	public void setYdStatus(String ydStatus) {
		this.ydStatus = ydStatus;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	public Date getCuishou() {
		return cuishou;
	}
	public void setCuishou(Date cuishou) {
		this.cuishou = cuishou;
	}
	public Boolean getContractUser() {
		return contractUser;
	}
	public void setContractUser(Boolean contractUser) {
		this.contractUser = contractUser;
	}
	public Boolean getContractDue() {
		return contractDue;
	}
	public void setContractDue(Boolean contractDue) {
		this.contractDue = contractDue;
	}
	public Boolean getContractNoback() {
		return contractNoback;
	}
	public void setContractNoback(Boolean contractNoback) {
		this.contractNoback = contractNoback;
	}
	public String getContractRemarks() {
		return contractRemarks;
	}
	public void setContractRemarks(String contractRemarks) {
		this.contractRemarks = contractRemarks;
	}
	public Boolean getBaozhengjin() {
		return baozhengjin;
	}
	public void setBaozhengjin(Boolean baozhengjin) {
		this.baozhengjin = baozhengjin;
	}
	public String getBzjRemar() {
		return bzjRemar;
	}
	public void setBzjRemar(String bzjRemar) {
		this.bzjRemar = bzjRemar;
	}
	public Boolean getCaiwuJk() {
		return caiwuJk;
	}
	public void setCaiwuJk(Boolean caiwuJk) {
		this.caiwuJk = caiwuJk;
	}
	public Boolean getFapiaoNoback() {
		return fapiaoNoback;
	}
	public void setFapiaoNoback(Boolean fapiaoNoback) {
		this.fapiaoNoback = fapiaoNoback;
	}
	public Boolean getRent() {
		return rent;
	}
	public void setRent(Boolean rent) {
		this.rent = rent;
	}
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
	public void setCaiwuRemarks(String caiwuRemarks) {
		this.caiwuRemarks = caiwuRemarks;
	}
	public Boolean getShebao() {
		return shebao;
	}
	public void setShebao(Boolean shebao) {
		this.shebao = shebao;
	}
	
}
