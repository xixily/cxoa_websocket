package com.chaoxing.oa.entity.po.hetong;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


//@Entity
//@Table(name="发货情况")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Fahuo implements Serializable {
	private static final long serialVersionUID = -5363417287600832573L;
	private Integer orderid;//序号
	private Integer hetongCode;//合同编号
	private String d_contact;//收件人
	private String d_company;//邮寄单位(到方
	private String d_address;//邮寄地址
	private String d_tel;//联系电话（到方）
	private String postMethod;//这里我只需要顺丰的
	private String jDate;//邮寄时间
	private String mailno;//邮寄凭证号(运单号)
	private String remark;//备注
	private String d_post_code;//邮编
	private String content;//内容
	private String areaCode;//区号
	private String sender;//发货人
	private String d_city;//城市
	private String area;//地区
	private String recorder;//录库人
	/**
	 * 货单新增
	 */
	private String destcode;//目的地区域代码
//	private String 
	@Id
	@Column(name="序号")
	public Integer getOrderid() {
		return orderid;
	}
	@Column(name="合同编号")
	public Integer getHetongCode() {
		return hetongCode;
	}
	@Column(name="收件人")
	public String getD_contact() {
		return d_contact;
	}
	@Column(name="邮寄单位")
	public String getD_company() {
		return d_company;
	}
	@Column(name="邮寄地址")
	public String getD_address() {
		return d_address;
	}
	@Column(name="联系电话")
	public String getD_tel() {
		return d_tel;
	}
	@Column(name="邮寄方式")
	public String getPostMethod() {
		return postMethod;
	}
	@Column(name="邮寄时间")
	public String getjDate() {
		return jDate;
	}
	@Column(name="邮寄凭证号")
	public String getMailno() {
		return mailno;
	}
	@Column(name="备注")
	public String getRemark() {
		return remark;
	}
	@Column(name="邮编")
	public String getD_post_code() {
		return d_post_code;
	}
	@Column(name="内容")
	public String getContent() {
		return content;
	}
	@Column(name="区号")
	public String getAreaCode() {
		return areaCode;
	}
	@Column(name="发货人")
	public String getSender() {
		return sender;
	}
	@Column(name="城市")
	public String getD_city() {
		return d_city;
	}
	@Column(name="地区")
	public String getArea() {
		return area;
	}
	@Column(name="录库人")
	public String getRecorder() {
		return recorder;
	}
	@Column
	public String getDestcode() {
		return destcode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public void setHetongCode(Integer hetongCode) {
		this.hetongCode = hetongCode;
	}
	public void setD_contact(String d_contact) {
		this.d_contact = d_contact;
	}
	public void setD_company(String d_company) {
		this.d_company = d_company;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}
	public void setPostMethod(String postMethod) {
		this.postMethod = postMethod;
	}
	public void setjDate(String jDate) {
		this.jDate = jDate;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setD_city(String d_city) {
		this.d_city = d_city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

}
