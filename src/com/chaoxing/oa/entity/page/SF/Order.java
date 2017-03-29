package com.chaoxing.oa.entity.page.SF;

public class Order {
	//Order 属性
	private Integer orderid;//(必填)
	private String mailno;//顺丰运单号
	private Integer is_gen_bill_no;//要求返回运单号
	private String j_company;//寄件方单位
	private String j_contact;//寄件方联系人
	private String j_tel;//寄件方联系电话
//	private String j_shippercode;//寄件方国家/城市代码(条件，跨境必填)
//	private String j_country;//寄方国家 
	private String j_province;//寄件方所在省份 
	private String j_city;//寄件方所在城市名称
	private String j_county;//寄件人所在县/区
	private String j_address;//寄件方详细地址(条件，如果需要 生成电子运单)
//	private String j_post_code ;//寄方邮编(条件，跨境必填)
	//Order 到方
	private String d_company;//到方公司名称（必填）
	private String d_contact;//到件方联系人（必填）
	private String d_tel;//到件方联系电话（必填）
	private String d_mobile;//到件方手机 
//	private String d_deliverycode;//到件方代码，（条件，跨境必填）
//	private String d_country;//到方国家
	private String d_province;//到件方所在省份
	private String d_city;//到件方所在城市名
	private String d_county;//到件方所在县/区
	private String d_address;//到件方详细地址（必填），如果不传输 d_province/d_city 字段，此详细地址 需包含省市信息，以提高地址识别的 
	private String d_post_code;//到方邮编
	//Order 其他信息
	private String custid;//顺丰月结卡号（条件）
	private Integer pay_method=1;//付款方式：1、寄方付；2、收方付；3、第三方付 
	private Integer express_type=1;//快件产品类别，见《快件产品类别表》，1、标准快递；2、顺丰特惠；3、电商特惠；5、顺丰次晨；6、即日件；7、电商速配;28、电商专配
	private Integer parcel_quantity;//包裹数,字母件
	private Float cargo_length;//客户订单货物总长number(10,3)
	private Float cargo_width;//客户订单货物总宽number(10,3)
	private Float cargo_height;//客户订单货物总number(10,3)
	private Float volume;//订单货物总体积number(17,3)
	private Float cargo_total_weight ;//订单货物总重量number(10,3)
//	private Float declared_value;//客户订单货物总声明价值(跨境件必填）
//	private String declared_value_currency;//货物声明价值币别，CNY: 人民币 ；USD: 美元 ；HKD: 港币 ；NTD: 新台币 ；
//	private String customs_batchs;//报关批次 
	private String sendstarttime;//要求上门取件开始时间
	private Integer is_docall;//是否要求通过是否手持终端通知顺丰
//	private String return_tracking;//顺丰签回单服务运单号
//	private String d_tax_no;//收件人税号 
	private String tax_pay_type;//税金付款方式： 1、寄方；2、到付
//	private String tax_set_accounts;//税金结算账号 
//	private String original_number;//电商原始订单号 
	private String payment_tool;//支付工具 
	private String payment_number;//支付号码
//	private String goods_code;//商品编号
//	private String in_process_waybill_no;//头程运单号
//	private String brand;//货物品牌 
//	private String specifications;//货物规格型号 
//	private String temp_range;//温度范围类型（条件，，当 express_type 为 12，1、冷藏；3、冻藏 ）
	private String order_name;//客户订单下单人姓名 
//	private String order_cert_type;//客户订单下单人证件类型 
//	private String order_source;//客户订单来源
//	private String template;//业务模板编码 
	private String remark;//备注
	private String oneself_pickup_flg;//快件自取；1 表示客户同意快件自取； 非 1 表示客户不同意快件自取 
//	private String dispatch_sys;//订单数据分发的系统编码 
	public Integer getOrderid() {
		return orderid;
	}
	public String getMailno() {
		return mailno;
	}
	public Integer getIs_gen_bill_no() {
		return is_gen_bill_no;
	}
	public String getJ_company() {
		return j_company;
	}
	public String getJ_contact() {
		return j_contact;
	}
	public String getJ_tel() {
		return j_tel;
	}
	public String getJ_province() {
		return j_province;
	}
	public String getJ_city() {
		return j_city;
	}
	public String getJ_county() {
		return j_county;
	}
	public String getJ_address() {
		return j_address;
	}
	public String getD_company() {
		return d_company;
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
	public String getD_province() {
		return d_province;
	}
	public String getD_city() {
		return d_city;
	}
	public String getD_county() {
		return d_county;
	}
	public String getD_address() {
		return d_address;
	}
	public String getD_post_code() {
		return d_post_code;
	}
	public String getCustid() {
		return custid;
	}
	public Integer getPay_method() {
		return pay_method;
	}
	public Integer getExpress_type() {
		return express_type;
	}
	public Integer getParcel_quantity() {
		return parcel_quantity;
	}
	public Float getCargo_length() {
		return cargo_length;
	}
	public Float getCargo_width() {
		return cargo_width;
	}
	public Float getCargo_height() {
		return cargo_height;
	}
	public Float getVolume() {
		return volume;
	}
	public Float getCargo_total_weight() {
		return cargo_total_weight;
	}
	public String getSendstarttime() {
		return sendstarttime;
	}
	public Integer getIs_docall() {
		return is_docall;
	}
	public String getTax_pay_type() {
		return tax_pay_type;
	}
	public String getPayment_tool() {
		return payment_tool;
	}
	public String getPayment_number() {
		return payment_number;
	}
	public String getOrder_name() {
		return order_name;
	}
	public String getRemark() {
		return remark;
	}
	public String getOneself_pickup_flg() {
		return oneself_pickup_flg;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public void setIs_gen_bill_no(Integer is_gen_bill_no) {
		this.is_gen_bill_no = is_gen_bill_no;
	}
	public void setJ_company(String j_company) {
		this.j_company = j_company;
	}
	public void setJ_contact(String j_contact) {
		this.j_contact = j_contact;
	}
	public void setJ_tel(String j_tel) {
		this.j_tel = j_tel;
	}
	public void setJ_province(String j_province) {
		this.j_province = j_province;
	}
	public void setJ_city(String j_city) {
		this.j_city = j_city;
	}
	public void setJ_county(String j_county) {
		this.j_county = j_county;
	}
	public void setJ_address(String j_address) {
		this.j_address = j_address;
	}
	public void setD_company(String d_company) {
		this.d_company = d_company;
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
	public void setD_province(String d_province) {
		this.d_province = d_province;
	}
	public void setD_city(String d_city) {
		this.d_city = d_city;
	}
	public void setD_county(String d_county) {
		this.d_county = d_county;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public void setPay_method(Integer pay_method) {
		this.pay_method = pay_method;
	}
	public void setExpress_type(Integer express_type) {
		this.express_type = express_type;
	}
	public void setParcel_quantity(Integer parcel_quantity) {
		this.parcel_quantity = parcel_quantity;
	}
	public void setCargo_length(Float cargo_length) {
		this.cargo_length = cargo_length;
	}
	public void setCargo_width(Float cargo_width) {
		this.cargo_width = cargo_width;
	}
	public void setCargo_height(Float cargo_height) {
		this.cargo_height = cargo_height;
	}
	public void setVolume(Float volume) {
		this.volume = volume;
	}
	public void setCargo_total_weight(Float cargo_total_weight) {
		this.cargo_total_weight = cargo_total_weight;
	}
	public void setSendstarttime(String sendstarttime) {
		this.sendstarttime = sendstarttime;
	}
	public void setIs_docall(Integer is_docall) {
		this.is_docall = is_docall;
	}
	public void setTax_pay_type(String tax_pay_type) {
		this.tax_pay_type = tax_pay_type;
	}
	public void setPayment_tool(String payment_tool) {
		this.payment_tool = payment_tool;
	}
	public void setPayment_number(String payment_number) {
		this.payment_number = payment_number;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOneself_pickup_flg(String oneself_pickup_flg) {
		this.oneself_pickup_flg = oneself_pickup_flg;
	}
	
}
