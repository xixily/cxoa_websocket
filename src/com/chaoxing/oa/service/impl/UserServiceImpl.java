package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.caiwu.PCNUsername;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PUserName;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;
import com.chaoxing.oa.entity.po.employee.UserName;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.RoleResourcesV;
import com.chaoxing.oa.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

//	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDaoI<UserName> usernameDao;
	@Autowired
	private BaseDaoI<RenshiUserName> renshiUsernameDao;
	private BaseDaoI<OrganizationStructure> ogsDao;
	private BaseDaoI<RoleResourcesV> roleReDao;
	
	public BaseDaoI<RoleResourcesV> getRoleReDao() {
		return roleReDao;
	}
	@Autowired
	public void setRoleReDao(BaseDaoI<RoleResourcesV> roleReDao) {
		this.roleReDao = roleReDao;
	}
	public BaseDaoI<OrganizationStructure> getOgsDao() {
		return ogsDao;
	}
	@Autowired
	public void setOgsDao(BaseDaoI<OrganizationStructure> ogsDao) {
		this.ogsDao = ogsDao;
	}

	public BaseDaoI<UserName> getUsernameDao() {
		return usernameDao;
	}

	@Autowired
	public void setUsernameDao(BaseDaoI<UserName> usernameDao) {
		this.usernameDao = usernameDao;
	}


	/**
	 * 查找一个userName信息copy到UserInfo中
	 */
	@Override
	public SessionInfo findUser(QueryForm userPageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", userPageInfo.getEmail());
//		UserName userName = usernameDao.get("from UserName u where u.email=:email",params);
		RenshiUserName rsUserName = renshiUsernameDao.get("from RenshiUserName where email=:email",params);
		if(null != rsUserName){
			SessionInfo sessioninfo = new SessionInfo();
			BeanUtils.copyProperties(rsUserName, sessioninfo);
//			BeanUtils.copyProperties(userName, userPageInfo);
//			sessioninfo.setRights(userName.getRoleId());
			return sessioninfo;
		}
		return null;
	}

	@Override
	public PUserName getUserName(int id) {
		PUserName pusername = null; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		UserName username = usernameDao.get("from UserName u where u.id = :id", params);
		if(username!=null){
			OrganizationStructure ogs = ogsDao.get("from OrganizationStructure o where o.id=" + username.getDepartmentId());
			pusername = new PUserName();
			BeanUtils.copyProperties(username, pusername);
//			BeanUtils.copyProperties(ogs, pusername);//这里部门ID传入pusername了
			pusername.setDepartmentId(ogs.getId());
			pusername.setFirstLevel(ogs.getFirstLevel());
			pusername.setSecondLevel(ogs.getSecondLevel());
			pusername.setThirdLevel(ogs.getThirdLevel());
			pusername.setFourthLevel(ogs.getFourthLevel());
			pusername.setCellCore(ogs.getCellCore());
			pusername.setId(username.getId());
		}
		return pusername;
	}
	
	@Override
	public PCNUsername getCNUsername(Integer id, Integer uid) {
		PCNUsername pusername = null; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		UserName username = usernameDao.get("from UserName u where u.id = :id", params);
		if(username!=null){
			OrganizationStructure ogs = ogsDao.get("from OrganizationStructure o where o.id=" + username.getDepartmentId());
			pusername = new PCNUsername();
			BeanUtils.copyProperties(username, pusername);
//			BeanUtils.copyProperties(ogs, pusername);//这里部门ID传入pusername了
			pusername.setDepartmentId(ogs.getId());
			pusername.setFirstLevel(ogs.getFirstLevel());
			pusername.setSecondLevel(ogs.getSecondLevel());
			pusername.setThirdLevel(ogs.getThirdLevel());
			pusername.setFourthLevel(ogs.getFourthLevel());
			pusername.setCellCore(ogs.getCellCore());
			pusername.setId(username.getId());
			if(uid != 11){
				pusername.setManagementSystem(null);
			}
		}
		return pusername;
	}
	
	@Override
	public int deleteUserName(QueryForm queryForm) {
		UserName u = new UserName();
		BeanUtils.copyProperties(queryForm, u);
		try {
			usernameDao.delete(u);
		} catch (Exception e) {
			System.out.println("delete 失败！");
			return 0;
		}
		return 1;
	}

	@Override
	public long addUserName(PUserName username) {
		UserName u = new UserName();
		BeanUtils.copyProperties(username, u);
		u.setRoleId(99);
//		System.out.println(u);
		String level = username.getLevel();
		String depart = username.getFourthLevel();
		if(level!=null){
			if(depart==null){
				username.setRoleId(99);
			}
		}
		try {
			Integer re = (Integer) usernameDao.save(u);
			return re;
		} catch (Exception e) {
			System.out.println("添加失败：" + e);
			return -1;
		}
	}

	@Override
	public long updateUserName(PUserName username) {
		UserName u = new UserName();
		BeanUtils.copyProperties(username, u);
		System.out.println(u.getIfSecret());
		try {
			usernameDao.update(u);
		} catch (Exception e) {
			System.out.println("更新失败：" + e);
			return -1;
		}
		return 1;
	}
	@Override
	public long updateUserRole(PUserName username) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(username.getRoleId()==0 || username.getId()==0){
			return 0;
		}
		params.put("roleId", username.getRoleId());
		params.put("id", username.getId());
//		StringBuffer hql = new StringBuffer("update UserName t set t.roleId = :roleId");
		return usernameDao.executeHql("update UserName t set t.roleId = :roleId where id = :id", params);
	}
	@Override
	public int updateSecret(PUserName username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ifSecret", username.getIfSecret());
		params.put("id", username.getId());
		System.out.println(username.getIfSecret());
		String hql = "update UserName t set t.ifSecret = :ifSecret where id = :id";
		return usernameDao.executeHql(hql, params);
	}
	
	@Override
	public List<String> finRoleResoures(int roleId) {
		Map<String,Object> params = new HashMap<String, Object>();
		List<String> lists = new ArrayList<String>();
		params.put("roleId", roleId);
		List<RoleResourcesV> roleRes = roleReDao.find("from RoleResourcesV t where t.roleId =:roleId",params);
		for (RoleResourcesV roleResources : roleRes) {
			lists.add(roleResources.getUrl());
		}
		return lists;
	}
	@Override
	public long updatePassword(QueryForm queryForm) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "update UserName t set t.password = :newpassword where t.email = :email and t.password = :password";
		params.put("newpassword", queryForm.getNewpassword());
		params.put("email", queryForm.getEmail());
		params.put("password", queryForm.getPassword());
		return usernameDao.executeHql(hql,params);
	}
	
	@Override
	public PUserName findUserByEmail(String email) {
//		String hql = "from UserName where email=:email ";
		String hql = "from RenshiUserName where email=:email ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("email", email);
		List<RenshiUserName> user = renshiUsernameDao.find(hql,params);
		PUserName pu = null;
		if(user.size()>0){
			RenshiUserName u = user.get(0);
			pu = new PUserName();
			BeanUtils.copyProperties(u, pu);
		}
		return pu;
	}
	
}
