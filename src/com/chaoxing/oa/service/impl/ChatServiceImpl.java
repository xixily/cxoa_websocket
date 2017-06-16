package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.websocket.PMessages;
import com.chaoxing.oa.entity.po.websocket.Messages;
import com.chaoxing.oa.service.ChatService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.system.SqlHelper;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	private BaseDaoI<Messages> messagesDao;
	@Autowired
	private BaseDaoI<Object> objDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Serializable addChatRecord(PMessages pmsg) {
		Messages msg = new Messages();
		BeanUtils.copyProperties(pmsg, msg);
		try {
			return messagesDao.save(msg);
		} catch (Exception e) {
			logger.error("[ChatServiceImpl.addChatRecord] erro with:" + e);
		}
		return 0;
	}
	
	@Override
	public Serializable addChatRecord(Messages msg) {
		try {
			return messagesDao.save(msg);
		} catch (Exception e) {
			logger.error("[ChatServiceImpl.addChatRecord] erro with:" + e);
		}
		return 0;
	}

	@Override
	public int updateChatRecord(PMessages pmsg) {
		Messages msg = new Messages();
		BeanUtils.copyProperties(pmsg, msg);
		try {
			messagesDao.update(msg);
			return 1;
		} catch (Exception e) {
			logger.error("[ChatServiceImpl.updateChatRecord] erro with:" + e);
		}
		return 0;
	}

	@Override
	public int deleteChatRecord(Integer id) {
		Messages msg = new Messages();
		msg.setId(id);
		try {
			messagesDao.delete(msg);
			return 1;
		} catch (Exception e) {
			logger.error("[ChatServiceImpl.deleteChatRecord] erro with:" + e);
		}
		return 0;
	}

	@Override
	public List<Messages> findChatRecord(PMessages pmsg, Page page) {
		Messages msg = new Messages();
		BeanUtils.copyProperties(pmsg, msg);
		StringBuffer hql = new StringBuffer("from Messages t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		List<Messages> msgList = new ArrayList<Messages>();
//		Calendar cal = Calendar.getInstance();
		try {
			hql.append(SqlHelper.prepareAndSql(msg, params, true));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("[ChatServiceImpl.findChatRecord] erro with:" + e);
			return null;
		}
//		hql.append(" and ((TO_DAYS(NOW())-TO_DAYS(t.date)<=7))");
		String sort = "id";
		String order = SysConfig.DESC;
		if(page.getSort() != null){
			sort = page.getSort();
			if(page.getOrder() != null){
				order = page.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = null != page.getPage() ? page.getPage():0;
		int pageSize = null!=page.getRows() ? page.getRows():10;
		msgList = messagesDao.find(hql.toString(),params,intPage,pageSize);
		return msgList;
	}
	
	@Override
	public List<Messages> findAllChatRecord(int id) {
		String hql = "from Messages t where t.sid=:id or t.to=:id order by t.date asc";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return messagesDao.find(hql,params);
	}
	
	@Override
	public List<Messages> findShChatRecordBylisId(Long lisId, int sid, boolean isChecker) {
		String hql = "from Messages t where t.lis_id=:lisId and (t.sid=:id or t.to=:id) order by t.date asc";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("lisId", lisId);
		if(isChecker){
			hql = "from Messages t where t.lis_id=:lisId order by t.date asc";
		}else{
			params.put("id", sid);
		}
		return messagesDao.find(hql,params);
	}

	@Override
	public void getChatRecord(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateToRead(int id) {
//		String sql = "update t_messages set _status = 0 where _to = :id";
		String hql = "update Messages t set t.status=0 where t.to = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return messagesDao.executeHql(hql, params);
	}

	@Override
	public List<Integer> findUserIdByrole(int rid) {
		String hql = "select t.id from UserName t where t.roleId = :rid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rid", rid);
		List<Object> ids = objDao.find(hql, params);
		List<Integer> lis = new ArrayList<Integer>();
		for (Object object : ids) {
			if(object instanceof Integer){
				lis.add((Integer) object);
			}
		}
		return lis;
	}
	
	

}
