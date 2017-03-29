package com.chaoxing.oa.entity.page.pub.caiwu;


public class PChupiaoBaoxiao {
	private Long id;
	private Integer cpid;//出票人id
	private Float tuipiao;//退票金额
	private String caiwuRemarks;//财务备注
	private Float koujk;//扣借款
	private Float baoxMoney;//汇款金额
//	private String baoxTime;//汇款时间
//	private Integer status;//状态
	private String kunhao;//捆号
	
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
	
}
