package com.chaoxing.oa.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.util.system.ResourceUtil;


public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if(null != session && null != session.getAttribute(ResourceUtil.getSessionInfoName())){
				SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
				attributes.put("userInfo", sessionInfo);
				Integer id = sessionInfo.getId();
				System.out.println("Websocket:用户[id:" + id + "]握手成功。");
//				if(null != sessionInfo){
//					attributes.put("userInfo", sessionInfo);
//					System.out.println("Websocket:用户[ID:"+ id + "]握手成功。");
//				}else{
//					
//				}
			}
			
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
