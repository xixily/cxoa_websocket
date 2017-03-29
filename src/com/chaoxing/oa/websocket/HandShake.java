package com.chaoxing.oa.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if(null != session){
				Long uid = (Long) session.getAttribute("uid");
				if(uid!=null){
					attributes.put("uid", uid);
					attributes.put("userInfo", session.getAttribute("userInfo"));
					System.out.println("Websocket:用户[ID:"+ uid + "]握手成功。");
				}else{
					return false;
				}
			}
			
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
