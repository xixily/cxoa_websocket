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

	public List<Messages> findAllChatRecord(int id);

	/**
	 * 查询list下的聊天记录
	 * @param lisId list Id
	 * @param sid 当前查询用户的id
	 * @param isChecker 当前查询用户为审核人的时候为true，否则为fals
	 * @return
	 */
	public List<Messages> findShChatRecordBylisId(String lisId, int sid, boolean isChecker);

	public int updateToRead(int id);

	public List<Integer> findUserIdByrole(int rid);

}
