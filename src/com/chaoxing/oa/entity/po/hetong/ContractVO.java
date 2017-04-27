package com.chaoxing.oa.entity.po.hetong;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "合同情况错误信息", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ContractVO implements Serializable{
	private static final long serialVersionUID = 211351489592348383L;
	private Integer xuhao;//序号  主键
	private Integer id;//合同编号
	private String dengjiTime;//登记时间
	private String signedTime;//签订时间
	private String company;//所属公司
	private String depart;//单位(购买单位)
	private String guidangCode;//归档编号
	private String pingming;//品名
	private String bookCount;//图书数量
	private String contractMoney;//合同金额
	private String area;//地区
	private String province;//省份
	private String operator;//负责人
	private String sealedTime;//盖章时间
	private String coopStatus;//对方盖章情况
	private String guidangDate;//归档时间
	private String youjiStatus;//邮寄情况
	private String fapiaoStatus;//发票情况
	private String agreementNumber;//合同数量
	private String agreementText;//合同内容
	private String remarksText;//备注
	private String kaipiaoMoney;//开票金额
	private String toCaiwuTime;//转财务时间
	private String payMethod;//付款方式
	private String youxiaoqi;//有效期
	private String cundangNumber;//存档编号
	private String terminateTime;//合同终止时间
	private String xingzhi;//性质 (单位性质)
	private String luku;//录库人
	private Integer didNum;//单位编号
	
	//yang add
	/*private String company_property;//单位性质
*/	private String user_property;//用户性质
	private String receivedAmount;//回款金额
	private String lianxifangshi;
	private String productInfo;
	
	
	private String endTime;//项目预计结束时间
	
	@Column(name = "合同编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "登记时间")
	public String getDengjiTime() {
		return dengjiTime;
	}
	@Id
	@Column(name = "序号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getXuhao() {
		return xuhao;
	}
	public void setXuhao(Integer xuhao) {
		this.xuhao = xuhao;
	}
	//提交日期
	@Column(name = "签定时间")
	public String getSignedTime() {
		return signedTime;
	}
	@Column(name = "所属公司")
	public String getCompany() {
		return company;
	}
	@Column(name = "单位")
	public String getDepart() {
		return depart;
	}
	@Column(name = "归档编号")
	public String getGuidangCode() {
		return guidangCode;
	}
	@Column(name = "品名")
	public String getPingming() {
		return pingming;
	}
	@Column(name = "图书数量")
	public String getBookCount() {
		return bookCount;
	}
	@Column(name = "合同金额")
	public String getContractMoney() {
		return contractMoney;
	}
	@Column(name = "地区")
	public String getArea() {
		return area;
	}
	@Column(name = "省份")
	public String getProvince() {
		return province;
	}
	@Column(name = "经办人")//负责人
	public String getOperator() {
		return operator;
	}
	@Column(name = "盖章时间")
	public String getSealedTime() {
		return sealedTime;
	}
	@Column(name = "对方盖章情况")
	public String getCoopStatus() {
		return coopStatus;
	}
	@Column(name = "归档时间")
	public String getGuidangDate() {
		return guidangDate;
	}
	@Column(name = "邮寄情况")
	public String getYoujiStatus() {
		return youjiStatus;
	}
	@Column(name = "发票情况")
	public String getFapiaoStatus() {
		return fapiaoStatus;
	}
	@Column(name = "合同份数")
	public String getAgreementNumber() {
		return agreementNumber;
	}
	@Column(name = "合同内容")//合同概要
	public String getAgreementText() {
		return agreementText;
	}
	@Column(name = "备注")
	public String getRemarksText() {
		return remarksText;
	}
	@Column(name = "开票总金额")
	public String getKaipiaoMoney() {
		return kaipiaoMoney;
	}
	@Column(name = "转财务时间")
	public String getToCaiwuTime() {
		return toCaiwuTime;
	}
	@Column(name = "付款方式")
	public String getPayMethod() {
		return payMethod;
	}
	
	@Column(name = "合同有效期")
	public String getYouxiaoqi() {
		return youxiaoqi;
	}
	@Column(name = "存档份数")
	public String getCundangNumber() {
		return cundangNumber;
	}
	
	@Column(name = "合同终止时间")
	public String getTerminateTime() {
		return terminateTime;
	}
	@Column(name = "性质")
	public String getXingzhi() {
		return xingzhi;
	}
	@Column(name = "录库人")
	public String getLuku() {
		return luku;
	}
	@Column(name = "单位编号")
	public Integer getDidNum() {
		return didNum;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDengjiTime(String dengjiTime) {
		this.dengjiTime = dengjiTime;
	}
	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public void setGuidangCode(String guidangCode) {
		this.guidangCode = guidangCode;
	}

	public void setPingming(String pingming) {
		this.pingming = pingming;
	}
	public void setBookCount(String bookCount) {
		this.bookCount = bookCount;
	}
	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setSealedTime(String sealedTime) {
		this.sealedTime = sealedTime;
	}
	public void setCoopStatus(String coopStatus) {
		this.coopStatus = coopStatus;
	}
	public void setGuidangDate(String guidangDate) {
		this.guidangDate = guidangDate;
	}
	public void setYoujiStatus(String youjiStatus) {
		this.youjiStatus = youjiStatus;
	}
	public void setFapiaoStatus(String fapiaoStatus) {
		this.fapiaoStatus = fapiaoStatus;
	}
	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}
	public void setAgreementText(String agreementText) {
		this.agreementText = agreementText;
	}
	public void setRemarksText(String remarksText) {
		this.remarksText = remarksText;
	}
	
	public void setKaipiaoMoney(String kaipiaoMoney) {
		this.kaipiaoMoney = kaipiaoMoney;
	}
	public void setToCaiwuTime(String toCaiwuTime) {
		this.toCaiwuTime = toCaiwuTime;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public void setYouxiaoqi(String youxiaoqi) {
		this.youxiaoqi = youxiaoqi;
	}
	public void setCundangNumber(String cundangNumber) {
		this.cundangNumber = cundangNumber;
	}
	
	public void setTerminateTime(String terminateTime) {
		this.terminateTime = terminateTime;
	}
	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}

	public void setLuku(String luku) {
		this.luku = luku;
	}
	public void setDidNum(Integer didNum) {
		this.didNum = didNum;
	}
	
	//yang add
/*	@Column(name = "单位性质")
	public String getCompany_property() {
		return company_property;
	}
	public void setCompany_property(String company_property) {
		this.company_property = company_property;
	}*/
	@Column(name = "用户性质")
	public String getUser_property() {
		return user_property;
	}
	public void setUser_property(String user_property) {
		this.user_property = user_property;
	}
	@Column(name = "回款金额")
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	@Column(name = "项目预计结算时间")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "联系方式")
	public String getLianxifangshi() {
		return lianxifangshi;
	}
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	@Column(name = "产品信息")
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	
}
