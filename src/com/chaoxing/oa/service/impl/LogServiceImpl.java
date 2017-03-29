package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.system.PLog;
import com.chaoxing.oa.entity.po.system.Log;
import com.chaoxing.oa.service.LogService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.system.SqlHelper;

@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private BaseDaoI<Log> myLogDao;
	@Autowired
	private BaseDaoI<Object> objDao;

	@Override
	public Serializable addlog(PLog pMyLog) {
		if(null != pMyLog){
			Log myLog = new Log();
			BeanUtils.copyProperties(pMyLog, myLog);
			try {
				return myLogDao.save(myLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int updateLog(PLog pMyLog) {
		if(null!=pMyLog && null != pMyLog.getId()){
			Log myLog = new Log();
			BeanUtils.copyProperties(pMyLog, myLog);
			try {
				myLogDao.update(myLog);
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int deleteLog(PLog pMyLog) {
		Log myLog = new Log();
		BeanUtils.copyProperties(pMyLog, myLog);
		try {
			myLogDao.delete(myLog);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Map<String, Object> findLog(PLog pMyLog, Page page) {
		return findLog(pMyLog, page, false);
	}

	@Override
	public Map<String, Object> findLog(PLog pMyLog, Page page, boolean isExport) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from MyLog t where ");
		Map<String, Object> logInfo = new HashMap<String, Object>();
		try {
			hql.append(SqlHelper.prepareAndSql(pMyLog, params, true));
			String sort = "id";
			String order = SysConfig.DESC;
			if(page.getSort() != null){
				sort = page.getSort();
				if(page.getOrder() != null){
					order = page.getOrder();
				}
			}
			hql.append(" order by t." + sort + " " + order);
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			if(!isExport){
				intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
				pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 100 : page.getRows();
			}
			List<Log> logs = myLogDao.find(hql.toString(), params, intPage, pageSize);
			Iterator<Log> it = logs.iterator();
			List<PLog> plogs = new ArrayList<PLog>();
			while(it.hasNext()){
				PLog plog = new PLog();
				BeanUtils.copyProperties(it.next(), plog);
				plogs.add(plog);
			}
			
			logInfo.put("rows", plogs);
			logInfo.put("total", getCount(hql.toString(),params, "MyLog"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return logInfo;
	}
	
	@Override
	public long getCount(String hql, Map<String, Object> params, String table) {
		StringBuffer hqll = new StringBuffer("select count(*) from " + table +" t where ");
		hqll.append(hql.split("where")[1]);
		return objDao.count(hqll.toString(), params);
	}

}
