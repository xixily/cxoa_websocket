package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.List;

import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.websocket.PMessages;
import com.chaoxing.oa.entity.po.websocket.Messages;

public interface ChatService {
	
	public Serializable addChatRecord(PMessages pmsg);
	
	public Serializable addChatRecord(Messages msg);
	
	public int updateChatRecord(PMessages pmsg);
	
	public int deleteChatRecord(Integer id);
	
	public List<Messages> findChatRecord(PMessages pmsg, Page page);
	
	public void getChatRecord(Integer id);

	public List<Messages> findChatRecord(int id);

	public int updateToRead(int id);
}
