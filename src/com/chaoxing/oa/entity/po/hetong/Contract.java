package com.chaoxing.oa.entity.po.hetong;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "合同情况", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contract implements Serializable{
	private static final long serialVersionUID = 211351489592348383L;
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
	@Id
	@Column(name = "合同编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "登记时间")
	public Date getDengjiTime() {
		return dengjiTime;
	}
	@Column(name = "签定时间")
	public Date getSignedTime() {
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
	@Column(name = "贴花情况")
	public String getTiehuaStatus() {
		return tiehuaStatus;
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
	public Float getContractMoney() {
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
	@Column(name = "经办人")
	public String getOperator() {
		return operator;
	}
	@Column(name = "盖章时间")
	public Date getSealedTime() {
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
	@Column(name = "合同内容")
	public String getAgreementText() {
		return agreementText;
	}
	@Column(name = "备注")
	public String getRemarksText() {
		return remarksText;
	}
	@Column(name = "年代")
	public Float getYear() {
		return year;
	}
	@Column(name = "开票总金额")
	public Float getKaipiaoMoney() {
		return kaipiaoMoney;
	}
	@Column(name = "转财务时间")
	public Date getToCaiwuTime() {
		return toCaiwuTime;
	}
	@Column(name = "付款方式")
	public String getPayMethod() {
		return payMethod;
	}
	@Column(name = "用户编号")
	public Integer getCid() {
		return cid;
	}
	@Column(name = "所在卷")
	public String getSuozaijuan() {
		return suozaijuan;
	}
	@Column(name = "分类")
	public String getDivid() {
		return divid;
	}
	@Column(name = "业务类型")
	public Float getYewuType() {
		return yewuType;
	}
	@Column(name = "合同有效期")
	public String getYouxiaoqi() {
		return youxiaoqi;
	}
	@Column(name = "存档份数")
	public String getCundangNumber() {
		return cundangNumber;
	}
	@Column(name = "电子书")
	public Integer getEbooks() {
		return ebooks;
	}
	@Column(name = "电子书包库")
	public Integer getEbookLibs() {
		return ebookLibs;
	}
	@Column(name = "视频")
	public Integer getVideo() {
		return video;
	}
	@Column(name = "视频包库")
	public Integer getVideoLibs() {
		return videoLibs;
	}
	@Column(name = "读秀")
	public Integer getDuxiu() {
		return duxiu;
	}
	@Column(name = "MEDALINK")
	public Integer getMEDALINK() {
		return MEDALINK;
	}
	@Column(name = "合同终止时间")
	public Date getTerminateTime() {
		return terminateTime;
	}
	@Column(name = "性质")
	public String getXingzhi() {
		return xingzhi;
	}
	@Column(name = "公司")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name = "部门")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name = "岗位")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name = "小组")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name = "录库人")
	public String getLuku() {
		return luku;
	}
	@Column(name = "单位编号")
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
