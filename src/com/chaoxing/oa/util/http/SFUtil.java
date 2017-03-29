package com.chaoxing.oa.util.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;

import com.chaoxing.oa.entity.page.SF.AddedService;
import com.chaoxing.oa.entity.page.SF.Cargo;
import com.chaoxing.oa.entity.page.SF.Order;
import com.chaoxing.oa.entity.page.SF.OrderResponse;
import com.chaoxing.oa.entity.page.SF.RouteRequest;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.util.data.Md5AndBase64;
import com.chaoxing.oa.util.system.ResourceUtil;
/**
 * 
 * @author dengxf
 *
 */
public class SFUtil implements Runnable{
	private static SFUtil sfUtil;
	/**
	 * 下单服务
	 */
	public static final String SF_ORDERSERVICE = "OrderService";
	public static final String SF_ORDER = "Order";//订单信息  
	public static final String SF_CARGO = "Cargo";//货物信息  order的子节点
	public static final String SF_ADDSERVICE = "AddedService";//增值服务 order的子节点
	/**
	 * 查询订单服务
	 */
	public static final String SF_ORDERSEARCHSERVICE = "OrderSearchService";
	public static final String SF_ORDERSEARCH = "OrderSearch";
	/**
	 * 路由服务
	 */
	public static final String SF_ROUTESERVICE = "RouteService";
	public static final String SF_ROUTEREQUEST = "RouteRequest";//路由请求
	
	public static SFUtil getInstance(){
		if(sfUtil == null){
			synchronized (SFUtil.class) {
				sfUtil = new SFUtil();
			}
		}
		return sfUtil;
	}
	
//	public static void main(String[] args) {
//		PFahuo pf = new PFahuo();
//		Integer a = (int) (Math.random()*10000000);
//		pf.setOrderid(a);
//		pf.setArea("0796");
//		pf.setAreaCode("0796");
//		pf.setContent("就是想发个快递");
//		pf.setD_address("江西师范大学（瑶湖校区）邓 17744543034");
//		pf.setD_company("江西师范大学");
//		pf.setD_contact("小邓");
//		pf.setD_tel("18146612837");
//		pf.setD_post_code("330022");
//		pf.setD_city("南昌");
//		pf.setRemark("这是一个很长很长的故事i·~~");
//		pf.setSender("邓~");
//		Order order = new Order();
//		BeanUtils.copyProperties(pf, order);
//		
//		order.setJ_tel("17744543034");
//		order.setJ_company("世纪超星公司");
//		order.setJ_contact("小邓");
//		order.setJ_address("北京市海淀区 上地东里3区4号楼 601");
//		
//		order.setExpress_type(1);
//		order.setPay_method(1);
//		order.setCustid("0103289539");
//		Cargo cargo = new Cargo();
//		cargo.setName("发票");
//		cargo.setCount(1);
//		List<Cargo> cargos = new ArrayList<Cargo>();
//		cargos.add(cargo);
//		Json result = SFUtil.sendOrder(order, cargos, null);
//		if(result.isSuccess()){
//			OrderResponse or = (OrderResponse) result.getObj();
//			String mailno = or.getMailno();
//			or.getOrderid();
//			BarCode128C.getCode128CPicture(mailno, 22, "d:/code3.jpg");
//		}
//		System.out.println(result.getMsg());
////		查询订单 6320318
//		Json result2 = SFUtil.queryOrder(String.valueOf(pf.getOrderid()));
////		Json result2 = s.queryOrder("4624347");
//		System.out.println((OrderResponse)result2.getObj());
////		Json result3 = s.queryRoute("4624347");
////		System.out.println(result3.getMsg());
////		System.out.println("路由查询结果" + (OrderResponse)result3.getObj());
//		
//		
//	}
	
	public static void main(String[] args) {
		Json result = queryOrder("44031");
		System.out.println(result);
	}
	
	/**
	 * 下单对象生成xml文件
	 * @param _order @required 订单字段，一定需要
	 * @param _cargo @Optional 货物信息，可选
	 * @param _addService @Optional 增值服务信息，可选
	 * @return String
	 */
	public static String prepareXML(String type, Object _order, List<Cargo> cargos, List<AddedService> addServices){
		File xmlFile = new File(SFUtil.class.getResource("/").getFile().toString() + "sfTemplate/SFTemplate.xml");
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlFile);
			Element root = document.getRootElement();
			Element body = root.element("Body");
			if(type.equals(SFUtil.SF_ORDER)){//下单
				root.addAttribute("service",SFUtil.SF_ORDERSERVICE);
				Element order = body.addElement(SFUtil.SF_ORDER);//订单
				prepareXML(_order, order);
				if(cargos!=null){
					for (Cargo cargo0 : cargos) {
						Element cargo_node = order.addElement(SFUtil.SF_CARGO);
						prepareXML(cargo0, cargo_node);
					}
				}
				if(addServices!=null){
					for (AddedService addedService0 : addServices) {
						Element addService_node = order.addElement(SFUtil.SF_ADDSERVICE);
						prepareXML(addedService0, addService_node);
					}
				}
			}else if(type.equals(SFUtil.SF_ORDERSEARCH)){//查询订单
				root.addAttribute("service",SFUtil.SF_ORDERSEARCHSERVICE);
				Element search = body.addElement(SFUtil.SF_ORDERSEARCH);//订单查询
				search.addAttribute("orderid", String.valueOf(_order));
			}else if(type.equals(SFUtil.SF_ROUTEREQUEST)){//查询订单
				root.addAttribute("service",SFUtil.SF_ROUTESERVICE);
				Element search = body.addElement(SFUtil.SF_ROUTEREQUEST);//订单查询
				prepareXML(_order, search);
			}else{
				return null;
			}
			return document.asXML();
		} catch (DocumentException e) {
			System.out.println("error~!");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把实体源解析为某个Element对象的节点属性
	 * @param source 需要解析的实体源
	 * @param node 需要添加的Element 节点
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void prepareXML(Object source,Element node) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clss = source.getClass();
		Field[] fs = clss.getDeclaredFields();
		for (Field field : fs) {
			Method method = null;
			Object value = null;
			String type = field.getType().getSimpleName();
			String name = field.getName();
			String upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
			method = clss.getMethod("get" + upperName);
			value = method.invoke(source);
			if(value == null)
				continue;
			String attrValue = null;
			if(type.equalsIgnoreCase("String")){
				attrValue = (String) value;
			}else{
				attrValue = String.valueOf(value);
			} 
			node.addAttribute(name, attrValue);
		}
	}
	
	/**
	 * 为与节点相对应的实体属性节点设置值（）
	 * @param node 需要解析属性的节点
	 * @param clazz 与属性节点相对应的实体类
	 * @return
	 */
	public static Object parseXml2Response(Element node, Class<?> clazz){
		String name = null;
		String type = null;
		String value = null;
		Object obj = new Object();
		Field fd = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		List<Attribute> attrs = node.attributes();
		for (Attribute attr : attrs) {
			name = attr.getName();//属性名称  
			value = attr.getValue();//属性的值  
			try {
				fd = clazz.getDeclaredField(name);
				fd.setAccessible(true);
				type = fd.getType().getSimpleName();
				if(type.equalsIgnoreCase("Float")){
					fd.set(obj, Float.valueOf(value));
				}else if(type.equals("Integer")||type.equals("int")){
					fd.set(obj, Integer.valueOf(value));
				}else if(type.equalsIgnoreCase("Double")){
					fd.set(obj, Double.valueOf(value));
				}else{
					fd.set(obj, value);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * 发送一个快递请求
	 * @param pfahuo 发货信息
	 * @param xml 发货xml配置信息
	 * @return
	 */
	public static Json sendOrder(Order order, List<Cargo> cargo, List<AddedService> addService){
		Json result = new Json();
		OrderResponse oderResponse = null;
		if(order.getOrderid()==0 || order.getD_company()==null || order.getD_contact()==null||order.getD_address()==null){
			result.setMsg("id[" + order.getOrderid() +"]的发货参数（序号、邮寄单位、邮寄地址、联系电话、邮寄地址）有误！~");
			return result;
		}
		String url = ResourceUtil.getUrl1();
		String checkword = ResourceUtil.getCheckWord();
		String xml = prepareXML(SFUtil.SF_ORDER, order, cargo, addService);
		if(xml==null){
			result.setMsg("生成xml文件失败！~");
			return result;
		}
		System.out.println("请求的xml:"+xml);
		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
		StringBuffer params = new StringBuffer("xml=");
		try {
			params.append(URLEncoder.encode(xml, "UTF-8"));
			params.append("&verifyCode=");
			params.append(verifyCode);
			String result1 = HttpProvider.sendPost(url, params.toString().getBytes());
			System.out.println("结果xml："+result1);
			Document document = DocumentHelper.parseText(result1);
			Element response = document.getRootElement();
			Element head = response.element("Head");
			if(head.getText().equalsIgnoreCase("OK")){
				result.setSuccess(true);
				Element body = response.element("Body");
				if(body!=null){
					Element responz = body.element("OrderResponse");
					if(responz!=null){
						oderResponse = (OrderResponse) parseXml2Response(responz,OrderResponse.class);
						result.setMsg("下单成功！~");
						result.setObj(oderResponse);
//						System.out.println(oderResponse);
					}else{
						result.setMsg("下单请求成功，但查询结果为空！~");
					}
				}
				result.setObj(oderResponse);
			}else{
				Element error = response.element("ERROR");
				String errCode = error.attributeValue("code");
				String errText = error.getText();
				if("8016".equals(errCode)){
					errText = "重复下单";
				}
				result.setErrorCode(errCode);
				result.setMsg("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
			}
		} catch (UnsupportedEncodingException e) {
			result.setMsg("URLEncoder.encode(xml, 'UTF-8'),解析参数xml失败");
			System.out.println("URLEncoder.encode(xml, 'UTF-8'),解析参数xml失败");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询订单功能
	 * @param orderid 要查询的订单编号
	 * @return
	 */
	public static Json queryOrder(String orderid){
		Json result = new Json();
		OrderResponse oderResponse = null;
		if(orderid == null){
			result.setMsg("序号为空！~");
			return result;
		}
		String url = ResourceUtil.getUrl1();
		String checkword = ResourceUtil.getCheckWord();
		String xml = prepareXML(SFUtil.SF_ORDERSEARCH, orderid, null, null);
		if(xml==null){
			result.setMsg("生成xml文件失败！~");
			return result;
		}
		System.out.println("请求的xml:"+xml);
		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
		StringBuffer params = new StringBuffer("xml=");
		try {
			params.append(URLEncoder.encode(xml, "UTF-8"));
			params.append("&verifyCode=");
			params.append(verifyCode);
			String result1 = HttpProvider.sendPost(url, params.toString().getBytes());
			System.out.println("结果xml："+result1);
			Document document = DocumentHelper.parseText(result1);
			Element response = document.getRootElement();
			Element head = response.element("Head");
			if(head.getText().equalsIgnoreCase("OK")){
				result.setSuccess(true);
				Element body = response.element("Body");
				if(body!=null){
					Element responz = body.element("OrderResponse");
					if(responz!=null){
						oderResponse = (OrderResponse) parseXml2Response(responz,OrderResponse.class);
					}else{
						result.setMsg("查询成功，但查询结果为空！~");
					}
				}
				result.setObj(oderResponse);
			}else{
				Element error = response.element("ERROR");
				String errCode = error.attributeValue("code");
				String errText = error.getText();
				result.setErrorCode(errCode);
				result.setMsg("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 路由查询，按客户订单号标准路由查询
	 * @param orderid 客户订单号
	 * @return 
	 */
	public static Json queryRoute(String orderid){
		RouteRequest rtRequest = new RouteRequest();
		rtRequest.setTracking_type(2);
		rtRequest.setTracking_number(orderid);
		return queryRoute(rtRequest);
	}
	
	/**
	 * 路由查询
	 * @param type 1、按顺丰运单号查询；2、客户订单号查询
	 * @param orderid 单号
	 * @param method_type 路由查询类别：1：标准路由查询 ;2：定制路由查询  
	 * @return
	 */
	public static Json queryRoute(RouteRequest rtRequest){
		Json result = new Json();
		OrderResponse oderResponse = null;
		if(rtRequest.getTracking_number() == null){
			result.setMsg("序号为空！~");
			return result;
		}
		String url = ResourceUtil.getUrl1();
		String checkword = ResourceUtil.getCheckWord();
		String xml = prepareXML(SFUtil.SF_ROUTEREQUEST, rtRequest, null, null);
		if(xml==null){
			result.setMsg("生成xml文件失败！~");
			return result;
		}
		System.out.println("请求的xml:"+xml);
		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
		StringBuffer params = new StringBuffer("xml=");
		try {
			params.append(URLEncoder.encode(xml, "UTF-8"));
			params.append("&verifyCode=");
			params.append(verifyCode);
			String result1 = HttpProvider.sendPost(url, params.toString().getBytes());
			System.out.println("结果xml："+result1);
			Document document = DocumentHelper.parseText(result1);
			Element response = document.getRootElement();
			Element head = response.element("Head");
			if(head.getText().equalsIgnoreCase("OK")){
				result.setSuccess(true);
				Element body = response.element("Body");
				if(body!=null){
					OrderResponse tempResponse = null;
					Element responz = body.element("RouteResponse");
					if(responz!=null){
						oderResponse = (OrderResponse) parseXml2Response(responz,OrderResponse.class);
						Element responRoute = responz.element("RouteResponse");
						if(responz!=null){
							tempResponse = (OrderResponse) parseXml2Response(responRoute,OrderResponse.class);
							if(tempResponse != null){
								tempResponse.setMailno(oderResponse.getMailno());
								tempResponse.setOrderid(oderResponse.getOrderid());
								oderResponse = tempResponse;
							}
						}
					}else{
						result.setMsg("路由成功，但路由结果为空！~");
					}
				}
				result.setObj(oderResponse);
			}else{
				Element error = response.element("ERROR");
				String errCode = error.attributeValue("code");
				String errText = error.getText();
				result.setErrorCode(errCode);
				result.setMsg("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void run() {
		
	}

}
