package com.chaoxing.oa.entity.page.pub.caiwu;


public class PChupiaoBaoxiao {
	private Long id;
	private Integer cpid;//出票人id
	private Float tuipiao;//退票金额
	private String caiwuRemarks;//财务备注
	private Float koujk;//扣借款
	private Float baoxMoney;//汇款金额
	private String cpTime;//出票时间
//	private String baoxTime;//汇款时间
//	private Integer status;//状态
	private String kunhao;//捆号
	private Float tuikuan;//退款金额，补借款金额
	private Float kouchu;//扣除金额
	
	public Long getId() {
		return id;
	}
	public Integer getCpid() {
		return cpid;
	}
	public Float getTuipiao() {
		return tuipiao;
	}
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
	public Float getKoujk() {
		return koujk;
	}
	public Float getBaoxMoney() {
		return baoxMoney;
	}
	public String getKunhao() {
		return kunhao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public void setTuipiao(Float tuipiao) {
		this.tuipiao = tuipiao;
	}
	public void setCaiwuRemarks(String caiwuRemarks) {
		this.caiwuRemarks = caiwuRemarks;
	}
	public void setKoujk(Float koujk) {
		this.koujk = koujk;
	}
	public void setBaoxMoney(Float baoxMoney) {
		this.baoxMoney = baoxMoney;
	}
	public void setKunhao(String kunhao) {
		this.kunhao = kunhao;
	}
	public String getCpTime() {
		return cpTime;
	}
	public void setCpTime(String cpTime) {
		this.cpTime = cpTime;
	}
	public Float getTuikuan() {
		return tuikuan;
	}
	public void setTuikuan(Float tuikuan) {
		this.tuikuan = tuikuan;
	}
	public Float getKouchu() {
		return kouchu;
	}
	public void setKouchu(Float kouchu) {
		this.kouchu = kouchu;
	}
	
	
}
