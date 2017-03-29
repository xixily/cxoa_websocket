package com.chaoxing.oa.util.http;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
//		try {
//			System.out.println(Utils.getData(1276768871634l));
//			String[] a = "".split(",");
//			System.out.println(a);
//			System.out.println(32*32*14);
//			System.out.println(Utils.getUniqueid());
//			System.out.println(Utils.getUniqueid());
//			System.out.println(Utils.getUniqueid());
//			System.out.println(Integer.valueOf("01"));
//			1275613980
//			1275614580
//			String result = HttpProvider
//					.sendGet("http://211.151.35.106:8180/bill/interface?" +
//							"interType=hasFare&json={\"accountId\":\"" +
//							"Q000000003914\",\"type\":\"1\"}");
//			System.out.println(result);
//			
//			
//			String result2 = HttpProvider
//			.sendGet("http://211.151.35.106:8180/bill/interface?" +
//					"interType=queryLicenseCount&json={\"accountId\":\"" +
//					"Q000000003914\"}");
//			System.out.println(result2);
//		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		UserVO user = new UserVO();
//		user.setUsername("zxc");
//		user.setPassword("");
//		JSONObject json = null;
//		json = JSONUtil.appendAllParam("{username:zxc,password:,validateCode:test}", params, request);
//		json = JSONUtil.appendParam(new JSONObject(), "username", "zxc");
//		
//		JSONUtil jsonObj = new JSONUtil(new JSONObject());
//		
		
		
		
//		jsonObj.appendParam("validateCode", "test");
//		String[] str = "username:zxc,password:,validateCode:test".split(",");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> jsonData = new HashMap<String, Object>();
		jsonData.put("id", "91346d6e-ec61-4c92-8477-d83f753cb356");
		jsonData.put("createTime", "2015-01-12 14:19:01");
		jsonData.put("eventType", "updateAccountStatus");
		jsonData.put("serverId", "newApp_test");
		Map<String, Object> eventMsg = new HashMap<String, Object>();
		eventMsg.put("oldStatus", "tryout");
		eventMsg.put("appNodeID", "newApp_test");
		eventMsg.put("eventID", "bcb0bcc7547c499489b22fe6545e1300");
		eventMsg.put("accountId", "N000000007661");
		eventMsg.put("status", "tryout");
		eventMsg.put("eventType", "updateAccountStatus");
		eventMsg.put("stopTime", null);
		String data1 = "{\"oldStatus\":\"tryout\",\"appNodeID\":\"newApp_test\",\"eventID\":\"bcb0bcc7547c499489b22fe6545e1300\",\"accountId\":\"N000000007661\",\"status\":\"tryout\",\"eventType\":\"updateAccountStatus\",\"stopTime\":null}";
		
		jsonData.put("eventMsg", data1);
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("data", jsonData);
//		params.put("type","platformSync");
//		params.put("data", jsonData);
//		JSONObject jsonObject = JSONObject.fromObject(jsonData);
//		String data1 = jsonObject.toString();
		
		
		//==========
//		jsonObj.appendParam("type", "platformSync");
//		jsonObj.appendParam("data", "");
//		String data = JSONUtil.getJsonString(jsonObj);
//		JSONWriter writer = new JSONWriter();
//		JSONReader reader = new JSONReader();
		
//		String data1 = "{"id":"91346d6e-ec61-4c92-8477-d83f753cb356","createTime":"2015-01-12 14:19:01",\"eventType\":\"updateAccountStatus\",\"serverId\":\"newApp_test\",\"eventMsg\":\"{\"oldStatus\":\"tryout\",\"appNodeID\":\"newApp_test\",\"eventID\":\"bcb0bcc7547c499489b22fe6545e1300\",\"accountId\":\"N000000007661\",\"status\":\"tryout\",\"eventType\":\"updateAccountStatus\",\"stopTime\":null}\r\n\"}";
//		String json11 = writer.write(jsonData);
//		String data = "type=platformSync&data="+json11;
//		boolean reponse = HttpUtil.sendToAss("http://127.0.0.1:8080/bill/interface/toLogin", data, new HttpProcessor() {
//		HttpProvider.sendPost(url, data)
//		boolean reponse = HttpUtil.sendToAss("http://127.0.0.1:3000/service", data, new HttpProcessor() {
//			public boolean onFailed(int code, String result) {
//				System.out.println("����ʧ�ܣ�result:" + result + "======code:" + code);
//				return false;
//			}
//			public boolean onSuccess(int code, String result) {
//				System.out.println("����ɹ���" + result);
//				return true;
//			}
//		});
//		System.out.println(reponse);
		
		
		String str1 = "http://10.8.15.222:7717/app?Action=Dialout&ActionID=1234567890&Account=N000000007121&Exten=13488817474&FromExten=8000&PBX=1.1.1.100&Variable=directCallerIDNum%3d58103398";
		try {
			String re = HttpProvider.sendGet(str1);
			System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
