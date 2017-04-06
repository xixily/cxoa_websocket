package com.chaoxing.oa.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.page.websocket.Messages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class MyWebsocketHandler implements WebSocketHandler {
	public static final Map<Integer, WebSocketSession> userChatSession;
//	private final static Logger log = Logger.getLogger(MyWebsocketHandler.class);
	
	static{
		userChatSession = new ConcurrentSkipListMap<Integer, WebSocketSession>();
	}
	
	private static synchronized void addChatSession(WebSocketSession session, Integer uid){
		if(null == userChatSession.get("uid")){
			userChatSession.put(uid, session);
		}
	}
	
	private static synchronized void removeSession(WebSocketSession session, Integer uid){
		if(null != userChatSession.get(uid)){
			userChatSession.remove(uid);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		Integer uid = (Integer) webSocketSession.getAttributes().get("uid");
		removeSession(webSocketSession, uid);
	}

	/**
	 * 连接建立之后，把该连接加入到userChatSession中统一管理，也不知道这样的方式承载能力有多强
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		if(null != sessionInfo){
			Integer uid = (Integer) sessionInfo.getId();
			addChatSession(webSocketSession, uid);
		}

	}

	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength()==0)return;
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Messages msg = gs.fromJson(message.getPayload().toString(), Messages.class);
		msg.setDate(new Date());
		if(null != sessionInfo){
			msg.setSid(sessionInfo.getId());
			msg.setSender(sessionInfo.getUsername());
			MessageDispatch(msg, webSocketSession);
		}else{
			return ;
		}
	}
	
	/**
	 * sessionInfo
	 * @param webSocketSession
	 * @return
	 */
	private SessionInfo getSessionInfo(WebSocketSession webSocketSession){
		return (SessionInfo) webSocketSession.getAttributes().get("userInfo");
	}
	
	/**
	 * 消息分发
	 * @param msg
	 * @param webSocketSession
	 */
	private void MessageDispatch(Messages msg, WebSocketSession webSocketSession) {
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		Integer to = msg.getTo();
		if(null == to ){
			to = (Integer) sessionInfo.getId();
			msg = systemMsg("没有收信人id", to);
		}else{
			webSocketSession = userChatSession.get(to);
		}
		TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
		if(null != webSocketSession){
			try {
				webSocketSession.sendMessage(textMsg);
			} catch (IOException e) {
//				log.error("MyWebsocketHandler.MessageDispatch:[" + e);
				System.out.println();
			}
		}
	}
	
	/**
	 * 想某人发送消息
	 * @param to 收件人id
	 * @param textMsg
	 */
	public static void sendMessages(Integer to, TextMessage textMsg){
		WebSocketSession webSocketSession = userChatSession.get(to);
		if(null != webSocketSession){
			try {
				webSocketSession.sendMessage(textMsg);
			} catch (IOException e) {
//				log.error("MyWebsocketHandler.MessageDispatch:[" + e);
			}
		}
	}
	
	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<Integer, WebSocketSession>> it = userChatSession
				.entrySet().iterator();

		// 多线程群发
		while (it.hasNext()) {
			final Entry<Integer, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
	}

	/**
	 * 返回系统消息
	 */
	private Messages systemMsg(String msg, Integer uid) {
		Messages message = new Messages();
		message.setMsg(msg);
		message.setDate(new Date());
		message.setMsg_type(Messages.SYSTEM_MESSAGES);
		message.setTo(uid);
		message.setSid(10000);
		return message;
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
