package com.chaoxing.oa.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.page.websocket.PMessages;
import com.chaoxing.oa.entity.po.websocket.Messages;
import com.chaoxing.oa.service.ChatService;
import com.chaoxing.oa.service.SystemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class MyWebsocketHandler implements WebSocketHandler {
	private static Logger logger = Logger.getLogger(MyWebsocketHandler.class);
	public static final Map<Integer, WebSocketSession> userChatSession;
	@Autowired
	private ChatService chatService;
	@Autowired
	private SystemService sysService;
//	private final static Logger log = Logger.getLogger(MyWebsocketHandler.class);
	
	static{
		userChatSession = new ConcurrentSkipListMap<Integer, WebSocketSession>();
	}
	
	private static synchronized void addChatSession(WebSocketSession session, Integer uid){
		if(null == userChatSession.get(uid)){
			userChatSession.put(uid, session);
			logger.info("[MyWebsocketHandler.addChatSession] add session[" + uid + "] success!");
		}
	}
	
	private static synchronized void removeSession(WebSocketSession session, Integer uid){
		if(null != userChatSession.get(uid)){
			userChatSession.remove(uid);
			logger.info("[MyWebsocketHandler.addChatSession] remove [" + uid + "] success!");
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		Integer uid = getSessionInfo(webSocketSession).getId();
		if(null != uid){
			removeSession(webSocketSession, uid);
		}
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
//			getAllMessages(webSocketSession);
//			logger.error("创建连接成功！");
		}
	}

	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength()==0)return;
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		try{
			PMessages msg = gs.fromJson(message.getPayload().toString(), PMessages.class);
			if(null != msg){
				msg.setDate(new Date());
				if(null != sessionInfo){
					msg.setSid(sessionInfo.getId());
					msg.setSender(sessionInfo.getUsername());
					MessageDispatch(msg, webSocketSession);
				}
			}else{
				logger.error("[MyWebsocketHandler.handleMessage] with an error ：Gson.fromJson(...) Json 数据格式转换错误");
			}
		}catch(Exception e){
			logger.error("[MyWebsocketHandler.handleMessage] with an error:" + e);
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
	private void MessageDispatch(PMessages msg, WebSocketSession webSocketSession) {
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		Integer uid = sessionInfo.getId();
		Integer to = msg.getTo();
		if(null == msg.getMsg_type()){
			msg.setMsg_type(PMessages.NORMAL_MESSAGES);
		}
		switch(msg.getMsg_type()){
		case PMessages.SYSTEM_MANAGE:
			sendSysManageMsg(msg, webSocketSession);
			break;
		case PMessages.CW_SH_MESSAGES:
			sendCaiwuMsg(msg, webSocketSession);
			break;
		case PMessages.GET_SH_MESSAGES:
			if(null != msg.getLis_id()){
				getShListMessages(msg, webSocketSession);
			}
			break;
		case PMessages.HEART_BEAT:
			sendHeartBeatMsg(msg, webSocketSession);
			break;
		default:
			if(null == to ){
				msg = systemMsg("没有收信人id", uid);
			}else{
				webSocketSession = userChatSession.get(to);
			}
			sendNormalMsg(msg, webSocketSession);
			break;
		}
	}
	
	private void getShListMessages(PMessages msg,WebSocketSession webSocketSession) {
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		boolean isChecker = sysService.getCaiwushRoleId() == sessionInfo.getRoleId() ? true : false;
		if(null != sessionInfo){
			List<Messages> messages = chatService.findShChatRecordBylisId(msg.getLis_id(), sessionInfo.getId(), isChecker);
			Map<String,Object> results = new HashMap<String, Object>();
			results.put("uls", messages);
			results.put("msg_type", PMessages.GET_SH_MESSAGES);
			results.put("uid", sessionInfo.getId());
			results.put("isChecker", isChecker);
			TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(results));
			try {
				webSocketSession.sendMessage(textMsg);
				chatService.updateToRead(sessionInfo.getId());
			} catch (IOException e) {
				logger.error("[MyWebsocketHandler.pushHistoryMsg] with an error:" + e);
			}
		}
	}

	@SuppressWarnings("unused")
	private void getAllMessages(WebSocketSession webSocketSession) {
		SessionInfo sessionInfo = getSessionInfo(webSocketSession);
		if(null != sessionInfo){
			List<Messages> messages = chatService.findAllChatRecord(sessionInfo.getId());
			Map<String,Object> results = new HashMap<String, Object>();
			results.put("uls", messages);
			results.put("msg_type", PMessages.SYSTEM_ALL_RECORDS);
			results.put("uid", sessionInfo.getId());
			TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(results));
			try {
				webSocketSession.sendMessage(textMsg);
				chatService.updateToRead(sessionInfo.getId());
			} catch (IOException e) {
				logger.error("[MyWebsocketHandler.pushHistoryMsg] with an error:" + e);
			}
		}
	}
	
	private PMessages sendMsg(PMessages msg, WebSocketSession webSocketSession) {
		TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
		msg.setStatus(1);
		if(null != webSocketSession){
			try {
				webSocketSession.sendMessage(textMsg);
				msg.setStatus(0);
			} catch (IOException e) {
				logger.error("[MyWebsocketHandler.sendMsg] with an error:" + e);
			}
		}
		return msg;
	}
	
	private void sendNormalMsg(PMessages msg, WebSocketSession webSocketSession) {
		if(null != webSocketSession){
			msg = sendMsg(msg, webSocketSession);
			Messages messages = new Messages();
			BeanUtils.copyProperties(msg, messages);
			saveMessages(messages, webSocketSession);
		}
	}

	private void saveMessages(Messages messages, WebSocketSession webSocketSession) {
		if(chatService.addChatRecord(messages).equals(0)){
			PMessages msg = systemMsg("录库失败", 0);
			if(null != webSocketSession){
				TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
				try {
					webSocketSession.sendMessage(textMsg);
				} catch (IOException e) {
					logger.error("[MyWebsocketHandler.addMessages] with an error:" + e);
				}
			}
		}
	}

	private void sendHeartBeatMsg(PMessages msg, WebSocketSession webSocketSession) {
		if(null != webSocketSession){
			msg.setMsg("连接进行中...");
			msg.setSid(10086);
			TextMessage textMsg = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
			try {
				webSocketSession.sendMessage(textMsg);
			} catch (IOException e) {
				logger.error("[MyWebsocketHandler.addMessages] with an error:" + e);
			}
		}
	}

	//财务审核信息每个财务审核管理员都能收到
	private void sendCaiwuMsg(PMessages msg, WebSocketSession webSocketSession) {
		SessionInfo session = getSessionInfo(webSocketSession);
		Integer cwRoleId = sysService.getCaiwushRoleId();
		WebSocketSession wbsession = null;
		if(cwRoleId == session.getRoleId()){// 财务管理员处理信息发送给申报人
			Integer ownerId = msg.getOwnerId();
			if(null != ownerId){
				wbsession = userChatSession.get(ownerId);
				msg.setTo(ownerId);
				if(null != wbsession){
					msg = sendMsg(msg, wbsession);
				}
			}
		}else{
			//群发给审核管理员
			List<Integer> receivers = chatService.findUserIdByrole(cwRoleId);
			for (Integer id : receivers) {
				wbsession = userChatSession.get(id);
				if(null != wbsession){
					msg = sendMsg(msg, wbsession);
				}
			}
		}
		//放入数据库
		Messages messages = new Messages();
		BeanUtils.copyProperties(msg, messages);
		saveMessages(messages, webSocketSession);
	}

	private void sendSysManageMsg(PMessages msg, WebSocketSession webSocketSession) {
//		SessionInfo session = getSessionInfo(webSocketSession);
		
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
	private PMessages systemMsg(String msg, Integer uid) {
		PMessages message = new PMessages();
		message.setMsg(msg);
		message.setDate(new Date());
		message.setMsg_type(PMessages.SYSTEM_MESSAGES);
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
