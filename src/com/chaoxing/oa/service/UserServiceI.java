package com.chaoxing.oa.service;


import com.chaoxing.oa.entity.page.caiwu.PCNUsername;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PUserName;
import com.chaoxing.oa.entity.page.system.SessionInfo;

import java.util.List;

public interface UserServiceI {

	public SessionInfo findUser(QueryForm userInfo);
	
	public PUserName getUserName(int id);
	
	public PCNUsername getCNUsername(Integer id, Integer uid);

	public int deleteUserName(QueryForm queryForm);

	public long addUserName(PUserName username);

	public long updateUserName(PUserName username);

	public long updateUserRole(PUserName username);

	public int updateSecret(PUserName username);

	public List<String> finRoleResoures(int roleId);

	public long updatePassword(QueryForm queryForm);

	public PUserName findUserByEmail(String email);

}
