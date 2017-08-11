package com.chaoxing.oa.entity.po.employee;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="异动确认表")
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
public class YidongConfirm {
	//人事信息
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
//	private Integer uid;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uid")
	private UserName user;
	@Column(name="姓名")
	private String username;
	@Column(name="邮箱")
	private String email;
	@Column(name="细胞核邮箱")
	private String cellEmail;
	@Column(name="指导邮箱")
	private String guidanceEmail;
	@Column(name="异动状态")
	private String ydStatus;
	@Column(name="人事确认")
	private Boolean confirm;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="催收时间")
	private Date cuishou;
	@Column(name="社保")
	private Boolean shebao;
	
	//合同信息
	@Column(name="用户合同")
	private Boolean contractUser;
	@Column(name="合同应收")
	private Boolean contractDue;
	@Column(name="未回合同")
	private Boolean contractNoback;
	@Column(name="合同备注")
	private String contractRemarks;
	
	//保证金信息
	@Column(name="保证金未回")
	private Boolean baozhengjin;
	@Column(name="保证金备注")
	private String bzjRemar;
	
	//财务信息
	@Column(name="财务借款")
	private Boolean caiwuJk;
	@Column(name="发票未回")
	private Boolean fapiaoNoback;
	@Column(name="房租")
	private Boolean rent;
	@Column(name="财务备注")
	private String caiwuRemarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserName getUser() {
		return user;
	}
	public void setUser(UserName user) {
		this.user = user;
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
