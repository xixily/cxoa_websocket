package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.Map;

import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.system.PLog;

public interface LogService {
	public Serializable addlog(PLog myLog);
	
	public int updateLog(PLog myLog);
	
	public int deleteLog(PLog myLog);
	
	public Map<String, Object> findLog(PLog myLog, Page page);
	
	public Map<String, Object> findLog(PLog myLog, Page page, boolean isExport);

	long getCount(String hql, Map<String, Object> params, String table);
}
