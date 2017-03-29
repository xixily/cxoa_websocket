package com.chaoxing.oa.entity.page.SF;

public class OrderResponse {
	private String orderid ;//客户订单号
	private String mailno;//顺丰运单号
	private String return_tracking_no;//顺丰签回单服务运单号 
	private String origincode;//原寄地区域代码
	private String destcode;//目的地区域代码
	private Integer filter_result;//筛单结果： 1：人工确认 ;2、可收派；3、不可收派
	private String remark;//备注 <路由查询时>路由节点具体描述 
	//路由查询结果字段
	private String accept_time;//路由节点发生的时间
	private String accept_address;//路由节点发生的地点 
	private String opcode;//路由节点操作码 
	
	public String getOrderid() {
		return orderid;
	}
	public String getMailno() {
		return mailno;
	}
	public String getReturn_tracking_no() {
		return return_tracking_no;
	}
	public String getOrigincode() {
		return origincode;
	}
	public String getDestcode() {
		return destcode;
	}
	public Integer getFilter_result() {
		return filter_result;
	}
	public String getRemark() {
		return remark;
	}
	public String getAccept_time() {
		return accept_time;
	}
	public String getAccept_address() {
		return accept_address;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public void setReturn_tracking_no(String return_tracking_no) {
		this.return_tracking_no = return_tracking_no;
	}
	public void setOrigincode(String origincode) {
		this.origincode = origincode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public void setFilter_result(Integer filter_result) {
		this.filter_result = filter_result;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
	public void setAccept_address(String accept_address) {
		this.accept_address = accept_address;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	@Override
	public String toString() {
		return "OrderResponse [orderid=" + orderid + ", mailno=" + mailno + ", return_tracking_no=" + return_tracking_no
				+ ", origincode=" + origincode + ", destcode=" + destcode + ", filter_result=" + filter_result
				+ ", remark=" + remark + ", accept_time=" + accept_time + ", accept_address=" + accept_address
				+ ", opcode=" + opcode + "]";
	}
}
