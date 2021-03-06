package com.chaoxing.oa.entity.page.hetong;

import java.sql.Date;

public class PContract {
	private Integer id;
	private Date dengjiTime;
	private Date signedTime;
	private String company;
	private String depart;
	private String guidangCode;
	private String tiehuaStatus;
	private String pingming;
	private String bookCount;
	private Float contractMoney;
	private String area;
	private String province;
	private String operator;
	private Date sealedTime;
	private String coopStatus;
	private String guidangDate;
	private String youjiStatus;
	private String fapiaoStatus;
	private String agreementNumber;
	private String agreementText;
	private String remarksText;
	private Float year;
	private Float kaipiaoMoney;
	private Date toCaiwuTime;
	private String payMethod;
	private Integer cid;//用户单位表外键
	private String suozaijuan;
	private String divid;
	private Float yewuType;
	private String youxiaoqi;
	private String cundangNumber;
	private Integer ebooks;
	private Integer ebookLibs;
	private Integer video;
	private Integer videoLibs;
	private Integer duxiu;
	private Integer MEDALINK;
	private Date terminateTime;
	private String xingzhi;
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String luku;
	private Integer did;//单位编号
	public Integer getId() {
		return id;
	}
	public Date getDengjiTime() {
		return dengjiTime;
	}
	public Date getSignedTime() {
		return signedTime;
	}
	public String getCompany() {
		return company;
	}
	public String getDepart() {
		return depart;
	}
	public String getGuidangCode() {
		return guidangCode;
	}
	public String getTiehuaStatus() {
		return tiehuaStatus;
	}
	public String getPingming() {
		return pingming;
	}
	public String getBookCount() {
		return bookCount;
	}
	public Float getContractMoney() {
		return contractMoney;
	}
	public String getArea() {
		return area;
	}
	public String getProvince() {
		return province;
	}
	public String getOperator() {
		return operator;
	}
	public Date getSealedTime() {
		return sealedTime;
	}
	public String getCoopStatus() {
		return coopStatus;
	}
	public String getGuidangDate() {
		return guidangDate;
	}
	public String getYoujiStatus() {
		return youjiStatus;
	}
	public String getFapiaoStatus() {
		return fapiaoStatus;
	}
	public String getAgreementNumber() {
		return agreementNumber;
	}
	public String getAgreementText() {
		return agreementText;
	}
	public String getRemarksText() {
		return remarksText;
	}
	public Float getYear() {
		return year;
	}
	public Float getKaipiaoMoney() {
		return kaipiaoMoney;
	}
	public Date getToCaiwuTime() {
		return toCaiwuTime;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public Integer getCid() {
		return cid;
	}
	public String getSuozaijuan() {
		return suozaijuan;
	}
	public String getDivid() {
		return divid;
	}
	public Float getYewuType() {
		return yewuType;
	}
	public String getYouxiaoqi() {
		return youxiaoqi;
	}
	public String getCundangNumber() {
		return cundangNumber;
	}
	public Integer getEbooks() {
		return ebooks;
	}
	public Integer getEbookLibs() {
		return ebookLibs;
	}
	public Integer getVideo() {
		return video;
	}
	public Integer getVideoLibs() {
		return videoLibs;
	}
	public Integer getDuxiu() {
		return duxiu;
	}
	public Integer getMEDALINK() {
		return MEDALINK;
	}
	public Date getTerminateTime() {
		return terminateTime;
	}
	public String getXingzhi() {
		return xingzhi;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getLuku() {
		return luku;
	}
	public Integer getDid() {
		return did;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDengjiTime(Date dengjiTime) {
		this.dengjiTime = dengjiTime;
	}
	public void setSignedTime(Date signedTime) {
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
	public void setTiehuaStatus(String tiehuaStatus) {
		this.tiehuaStatus = tiehuaStatus;
	}
	public void setPingming(String pingming) {
		this.pingming = pingming;
	}
	public void setBookCount(String bookCount) {
		this.bookCount = bookCount;
	}
	public void setContractMoney(Float contractMoney) {
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
	public void setSealedTime(Date sealedTime) {
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
	public void setYear(Float year) {
		this.year = year;
	}
	public void setKaipiaoMoney(Float kaipiaoMoney) {
		this.kaipiaoMoney = kaipiaoMoney;
	}
	public void setToCaiwuTime(Date toCaiwuTime) {
		this.toCaiwuTime = toCaiwuTime;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setSuozaijuan(String suozaijuan) {
		this.suozaijuan = suozaijuan;
	}
	public void setDivid(String divid) {
		this.divid = divid;
	}
	public void setYewuType(Float yewuType) {
		this.yewuType = yewuType;
	}
	public void setYouxiaoqi(String youxiaoqi) {
		this.youxiaoqi = youxiaoqi;
	}
	public void setCundangNumber(String cundangNumber) {
		this.cundangNumber = cundangNumber;
	}
	public void setEbooks(Integer ebooks) {
		this.ebooks = ebooks;
	}
	public void setEbookLibs(Integer ebookLibs) {
		this.ebookLibs = ebookLibs;
	}
	public void setVideo(Integer video) {
		this.video = video;
	}
	public void setVideoLibs(Integer videoLibs) {
		this.videoLibs = videoLibs;
	}
	public void setDuxiu(Integer duxiu) {
		this.duxiu = duxiu;
	}
	public void setMEDALINK(Integer mEDALINK) {
		MEDALINK = mEDALINK;
	}
	public void setTerminateTime(Date terminateTime) {
		this.terminateTime = terminateTime;
	}
	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setLuku(String luku) {
		this.luku = luku;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	
}
