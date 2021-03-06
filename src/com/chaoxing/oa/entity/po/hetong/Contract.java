package com.chaoxing.oa.entity.po.hetong;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import aj.org.objectweb.asm.Type;

@Entity
@Table(name = "合同情况", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contract implements Serializable{
	private static final long serialVersionUID = 211351489592348383L;
	private Integer id;//合同编号
	private Date dengjiTime;//登记时间
	private Date signedTime;//签订时间
	private String company;//所属公司
	private String depart;//单位(购买单位)
	private String guidangCode;//归档编号
	private String tiehuaStatus;//贴花情况
	private String pingming;//品名
	private String bookCount;//图书数量
	private Float contractMoney;//合同金额
	private String area;//地区
	private String province;//省份
	private String operator;//经办人
	private Date sealedTime;//盖章时间
	private String coopStatus;//对方盖章情况
	private String guidangDate;//归档时间
	private String youjiStatus;//邮寄情况
	private String fapiaoStatus;//发票情况
	private String agreementNumber;//合同数量
	private String agreementText;//合同内容
	private String remarksText;//备注
	private Float year;//年代
	private Float kaipiaoMoney;//开票金额
	private Date toCaiwuTime;//转财务时间
	private String payMethod;//付款方式
	private Integer cid;//用户单位表外键  用户编号
	private String suozaijuan;//所在卷
	private String divid;//分类
	private Float yewuType;//业务类型
	private String youxiaoqi;//有效期
	private String cundangNumber;//存档编号
	private Integer ebooks;//电子书
	private Integer ebookLibs;//电子书包库
	private Integer video;//视频
	private Integer videoLibs;//视频包库
	private Integer duxiu;//独秀
	private Integer MEDALINK;//MEDALINK
	private Date terminateTime;//合同终止时间
	private String xingzhi;//性质 (单位性质)
	private String firstLevel;//一级 公司    (岗位性质,前场,中场,后场)
	private String secondLevel;//二级  部门
	private String thirdLevel;//三级 岗位  (省份)
	private String fourthLevel;//四级 小组
	private String luku;//录库人
	private Integer didNum;//单位编号
	//yang add
	/*private String company_property;//单位性质
*/	private String user_property;//用户性质
	/*private String receivedAmount;//回款金额
*///	private Integer expressCondition;//快递情况   1 已发 0 未发
	private Integer dealConditon;//处理状态   -1 垃圾合同  0 未处理 1 审核未通过 2 审核已通过 3 合同完结 4 保存(暂存) 5 未处理(修改) 6 审核未通过(修改) 7 废弃合同      023不能修改  14能修改
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;//项目预计结束时间(合同到期时间)
/*	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date receiveTime;//回款时间
*/	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submitTime;//提交时间
	private String guidangNum;//归档份数
	private String kuaijifenlei;//会计分类
	private Integer copyId;//存的是某个合同的id,表示此合同副本,默认-1
	private Integer type;//合同类型 1表示普通合同,2表示副本合同 ,数据库默认为1
	/*private String receivedAmount;//回款金额
	private Date receiveTime;//回款时间
	private String product;//所含产品
*/
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
	//提交日期
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
	@Column(name = "合同内容")//合同概要
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
	/*@Column(name = "开票总金额")
	public Float getKaipiaoMoney() {
		return kaipiaoMoney;
	}*/
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
	public Integer getDidNum() {
		return didNum;
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
/*	public void setKaipiaoMoney(Float kaipiaoMoney) {
		this.kaipiaoMoney = kaipiaoMoney;
	}*/
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
/*	@Column(name = "回款金额")
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}*/
	/*@Column(name = "快递情况")
	public Integer getExpressCondition() {
		return expressCondition;
	}
	public void setExpressCondition(Integer expressCondition) {
		this.expressCondition = expressCondition;
	}*/
	@Column(name = "处理状态")
	public Integer getDealConditon() {
		return dealConditon;
	}
	public void setDealConditon(Integer dealConditon) {
		this.dealConditon = dealConditon;
	}
	@Column(name = "项目预计结算时间")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/*@Column(name = "回款时间")
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}*/
	@Column(name = "提交时间")
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
/*	@Column(name = "所含产品")
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}*/
	@Column(name = "归档份数")
	public String getGuidangNum() {
		return guidangNum;
	}
	public void setGuidangNum(String guidangNum) {
		this.guidangNum = guidangNum;
	}
	@Column(name = "会计分类")
	public String getKuaijifenlei() {
		return kuaijifenlei;
	}
	public void setKuaijifenlei(String kuaijifenlei) {
		this.kuaijifenlei = kuaijifenlei;
	}
	@Column(name = "开票总金额")
	public Float getKaipiaoMoney() {
		return kaipiaoMoney;
	}
	public void setKaipiaoMoney(Float kaipiaoMoney) {
		this.kaipiaoMoney = kaipiaoMoney;
	}
	@Column(name = "副本所属合同")
	public Integer getCopyId() {
		return copyId;
	}
	public void setCopyId(Integer copyId) {
		this.copyId = copyId;
	}
	@Column(name = "合同类型")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
