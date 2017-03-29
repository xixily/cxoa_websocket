package com.chaoxing.oa.entity.page.SF;
/**
 * 路由实体
 */
public class RouteRequest {
	private int tracking_type=2;//1：根据顺丰运单号查询， 2：根据客户订单号查询
	private String tracking_number;//查询号
	private int method_type = 1;//路由查询类别：1：标准路由查询2：定制路由查询 
	
	public int getTracking_type() {
		return tracking_type;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public int getMethod_type() {
		return method_type;
	}
	public void setTracking_type(int tracking_type) {
		this.tracking_type = tracking_type;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public void setMethod_type(int method_type) {
		this.method_type = method_type;
	}
	
}
