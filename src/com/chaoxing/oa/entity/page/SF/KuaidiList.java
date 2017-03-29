package com.chaoxing.oa.entity.page.SF;
/**
 * 顺丰打印单信息，有一些没有做
 * @author dengxf
 *
 */
public class KuaidiList {
	private Integer orderid;//序号
	private String order_name;//下单
	private String mailno;//顺丰运单号
	private Integer express_type=1;//快件产品类别，见《快件产品类别表》，1、标准快递；2、顺丰特惠；3、电商特惠；5、顺丰次晨；6、即日件；7、电商速配;28、电商专配
	
	private String addService_name;//增值服务名称，如 COD 等。（关于增值服务具体查看文档4.2.6增值服务下单属性）
	private String addService_value1;//增值服务扩展属性，参考增值服务传 值说明。 
	private String addService_value2;//增值服务扩展属性
	
	private String destcode;//目的地区域代码
	private String d_contact;//到件方联系人（必填）
	private String d_tel;//到件方联系电话（必填）
	private String d_mobile;//到件方手机 
	private String d_company;//邮寄单位(到方）
	private String d_address;//到件方详细地址（必填），如果不传输 d_province/d_city 字段，此详细地址 需包含省市信息，以提高地址识别的 
	private String d_post_code;//邮编
	
	private String origincode;//寄方区域代码
	private String j_contact;//寄件方联系人
	private String j_tel;//寄件方联系电话
	private String j_company;//寄件方单位
	private String j_address;//寄件方详细地址(条件，如果需要 生成电子运单)
	
	private Integer pay_method=1;//付款方式：1、寄方付；2、收方付；3、第三方付 
	private Integer parcel_quantity=1;//包裹数,字母件	
	private String custid;//顺丰月结卡号（条件）
	private String content;//内容>>托寄物
	private String remark;//备注
	
	private String name;//货物名称
	private Integer count;// 货物数量
	public String getMailno() {
		return mailno;
	}
	public Integer getExpress_type() {
		return express_type;
	}
	public String getAddService_name() {
		return addService_name;
	}
	public String getAddService_value1() {
		return addService_value1;
	}
	public String getAddService_value2() {
		return addService_value2;
	}
	public String getDestcode() {
		return destcode;
	}
	public String getD_contact() {
		return d_contact;
	}
	public String getD_tel() {
		return d_tel;
	}
	public String getD_mobile() {
		return d_mobile;
	}
	public String getD_company() {
		return d_company;
	}
	public String getD_address() {
		return d_address;
	}
	public String getJ_contact() {
		return j_contact;
	}
	public String getJ_tel() {
		return j_tel;
	}
	public String getJ_company() {
		return j_company;
	}
	public String getJ_address() {
		return j_address;
	}
	public Integer getPay_method() {
		return pay_method;
	}
	public Integer getParcel_quantity() {
		return parcel_quantity;
	}
	public String getCustid() {
		return custid;
	}
	public String getContent() {
		return content;
	}
	public String getRemark() {
		return remark;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public void setExpress_type(Integer express_type) {
		this.express_type = express_type;
	}
	public void setAddService_name(String addService_name) {
		this.addService_name = addService_name;
	}
	public void setAddService_value1(String addService_value1) {
		this.addService_value1 = addService_value1;
	}
	public void setAddService_value2(String addService_value2) {
		this.addService_value2 = addService_value2;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public void setD_contact(String d_contact) {
		this.d_contact = d_contact;
	}
	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}
	public void setD_mobile(String d_mobile) {
		this.d_mobile = d_mobile;
	}
	public void setD_company(String d_company) {
		this.d_company = d_company;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public void setJ_contact(String j_contact) {
		this.j_contact = j_contact;
	}
	public void setJ_tel(String j_tel) {
		this.j_tel = j_tel;
	}
	public void setJ_company(String j_company) {
		this.j_company = j_company;
	}
	public void setJ_address(String j_address) {
		this.j_address = j_address;
	}
	public void setPay_method(Integer pay_method) {
		this.pay_method = pay_method;
	}
	public void setParcel_quantity(Integer parcel_quantity) {
		this.parcel_quantity = parcel_quantity;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public String getOrder_name() {
		return order_name;
	}
	public String getD_post_code() {
		return d_post_code;
	}
	public String getOrigincode() {
		return origincode;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}
	public void setOrigincode(String origincode) {
		this.origincode = origincode;
	}
	public String getName() {
		return name;
	}
	public Integer getCount() {
		return count;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
