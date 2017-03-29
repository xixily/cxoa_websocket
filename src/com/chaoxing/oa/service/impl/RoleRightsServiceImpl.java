//package com.chaoxing.oa.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.chaoxing.oa.dao.BaseDaoI;
//import com.chaoxing.oa.entity.po.RoleRights;
//import com.chaoxing.oa.service.RoleRightsService;
//
//@Service("roleRightsService")
//public class RoleRightsServiceImpl implements RoleRightsService {
//	private BaseDaoI<RoleRights> roleRightsDao;
//	private BaseDaoI<Object> objectDao;
//	
//	public BaseDaoI<Object> getObjectDao() {
//		return objectDao;
//	}
//	@Autowired
//	public void setObjectDao(BaseDaoI<Object> objectDao) {
//		this.objectDao = objectDao;
//	}
//	public BaseDaoI<RoleRights> getRoleRightsDao() {
//		return roleRightsDao;
//	}
//	@Autowired
//	public void setRoleRightsDao(BaseDaoI<RoleRights> roleRightsDao) {
//		this.roleRightsDao = roleRightsDao;
//	}
//	@Override
//	public List<RoleRights> getRightsbyId(int id) {
//		List<RoleRights> list = roleRightsDao.find("from RoleRights r where id = 1");
////		List<RoleRights> list = roleRightsDao.find("from RoleRights r where id = 1");
//		 return list;
//	}
//	@Override
//	public List<Object> getObjectList(int id) {
//		return objectDao.find("from RoleRights r where id = 1");
//	}
//	@Override
//	public List<RoleRights> getRightsbyRoleId(int id) {
//		List<RoleRights> list = roleRightsDao.find("from RoleRights r where r.roleId.roleId = 1 ");
//		return list;
//	}
//	@Override
//	public int updateRoleRight(RoleRights rolerights) {
//		try {
//			roleRightsDao.update(rolerights);
//		} catch (Exception e) {
//			System.out.println("Exception:" + e);
//			return 0;
//		}
//		return 1;
//	}
//	@Override
//	public int addRoleRight(RoleRights rolerights) {
//		try {
//			roleRightsDao.save(rolerights);
//		} catch (Exception e) {
//			System.out.println("Exception:" + e);
//			return 0;
//		}
//		return 1;
//	}
//	
//}
