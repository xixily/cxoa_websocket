package com.chaoxing.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.impl.BaseDaoImpl;
import com.chaoxing.oa.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Autowired
	private BaseDaoImpl<Object> objDao;
	
	@Override
	public void findSomeThings() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> findUsers() {
		List<Object> lis = objDao.find("select id,username,角色,姓名,一级,二级,三级,四级 from 人事username limit 0,50");
		return lis;
	}
	
	

}
