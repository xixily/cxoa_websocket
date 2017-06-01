package com.chaoxing.oa.service;


import com.chaoxing.oa.entity.page.caiwu.PCNUsername;
import com.chaoxing.oa.entity.page.common.POStruct;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PUserName;
import com.chaoxing.oa.entity.page.pub.PLeader;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;
import com.chaoxing.oa.entity.po.employee.UserName;

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

	public UserName getUserById(Integer id);
	
	/**
	 * 通过名字找到签字人的id、邮箱、架构等信息
	 * @param name
	 */
	public List<PLeader> findLearByname(String name);
	
	/**
	 * 查找默认签字人 id、邮箱、架构等信息
	 * @param departmentId
	 * @return
	 */
	public PLeader getCellCoreInfo(int departmentId);

	public void findCellsByemail(String email, String level);

	/**
	 * 根据细胞核查询架构
	 * @param email
	 */
	public List<POStruct>  findostructByEmail(String email);

}
