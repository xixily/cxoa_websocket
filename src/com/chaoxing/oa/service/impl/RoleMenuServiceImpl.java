package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.system.PMenu;
import com.chaoxing.oa.entity.page.system.PUlList;
import com.chaoxing.oa.entity.po.system.Menu;
import com.chaoxing.oa.entity.po.view.RoleResourcesV;
//import com.chaoxing.oa.entity.po.RoleRights;
import com.chaoxing.oa.service.RoleMenuService;

@Service("roleMenuService")
/*@Transactional*/
public class RoleMenuServiceImpl implements RoleMenuService {
	private static final Logger logger = Logger.getLogger(RoleMenuServiceImpl.class);
	private BaseDaoI<Menu> menuDao;
	private BaseDaoI<RoleResourcesV> roleResourcesDao;
	
	public BaseDaoI<RoleResourcesV> getRoleResourcesDao() {
		return roleResourcesDao;
	}
	@Autowired
	public void setRoleResourcesDao(BaseDaoI<RoleResourcesV> roleResourcesDao) {
		this.roleResourcesDao = roleResourcesDao;
	}
	public BaseDaoI<Menu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<PMenu> findMenuByRole(int roleId, HttpSession session) {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<RoleResourcesV> roleResources = roleResourcesDao.find("from RoleResourcesV r where r.roleId=:roleId and r.menuLevel=1 ORDER BY r.sortCode ASC ",params);
		List<RoleResourcesV> roleResources2 = roleResourcesDao.find("from RoleResourcesV r where r.roleId=:roleId and r.menuLevel=2 ORDER BY r.sortCode ASC",params);
		Iterator<RoleResourcesV> it1 = roleResources.iterator();
		Iterator<RoleResourcesV> it2;
		PMenu pm ;
		while(it1.hasNext()){
			RoleResourcesV roleV = it1.next();
			pm = new PMenu();
			pm.setMenuId(roleV.getMenuId());
			pm.setUserId(roleV.getRoleId());
			pm.setMenuName(roleV.getMenuName());
			it2 = roleResources2.iterator();
			RoleResourcesV roleV2 ;
			while(it2.hasNext()){
				roleV2 = it2.next();
				if(roleV2.getPreMenuId() == pm.getMenuId()){
					PUlList ulList = new PUlList();
					ulList.setSortCode(roleV2.getSortCode());
					ulList.setDomId(roleV2.getMenuId());
					ulList.setText(roleV2.getMenuName());
					ulList.setIconCls(roleV2.getIconCls());
					ulList.setUrl(roleV2.getUrl());
					pm.getUls().add(ulList);
					it2.remove();
				}
			}
			l_menuInfos.add(pm);
		}
		
		Collections.sort(l_menuInfos);
		return l_menuInfos;
	}
	
	@Override
	public List<PMenu> findAllMenu() {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
//		Map<String, Object> params = new HashMap<String, Object>();
		List<Menu> menus1 = menuDao.find("from Menu t where t.menuLevel=1 and t.sortCode not like('Pub%')");
		for (Menu menu : menus1) {
			PMenu menuInfo = new PMenu(); 
//			menuInfo.setUserId(menu.getRoleId().getRoleId());
			menuInfo.setMenuId(menu.getMenuId());
			menuInfo.setMenuName(menu.getMenuName());
			menuInfo.setSortCode(menu.getSortCode());
			for (Menu menu2 : menu.getMenus()) {
				PUlList ulList = new PUlList();
				ulList.setSortCode(menu2.getSortCode());
				ulList.setDomId(menu2.getMenuId());
				ulList.setText(menu2.getMenuName());
				ulList.setIconCls(menu2.getIconCls());
				if(menu2.getUrl()==null){
					ulList.setUrl("");
				}else{
					ulList.setUrl(menu2.getUrl());
				}
				menuInfo.getUls().add(ulList);
			}
			l_menuInfos.add(menuInfo);
		}
		Collections.sort(l_menuInfos);
		return l_menuInfos;
	}
	
	/**
	 * sortCode 为Pub开头的，就是公共
	 */
	@Override
	public List<PMenu> findPubMenuByRole(int roleId) {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<RoleResourcesV> roleResources = roleResourcesDao.find("from RoleResourcesV r where r.roleId=:roleId and r.menuLevel=1 and r.sortCode LIKE('Pub%') order by r.sortCode ASC ",params);
		List<RoleResourcesV> roleResources2 = roleResourcesDao.find("from RoleResourcesV r where r.roleId=:roleId and r.menuLevel=2 and sortCode LIKE('Pub%') order by r.sortCode ASC ",params);
		Iterator<RoleResourcesV> it1 = roleResources.iterator();
		Iterator<RoleResourcesV> it2;
		PMenu pm ;
		while(it1.hasNext()){
			RoleResourcesV roleV = it1.next();
			pm = new PMenu();
			pm.setMenuId(roleV.getMenuId());
			pm.setUserId(roleV.getRoleId());
			pm.setMenuName(roleV.getMenuName());
			it2 = roleResources2.iterator();
			RoleResourcesV roleV2 ;
			while(it2.hasNext()){
				roleV2 = it2.next();
				if(roleV2.getPreMenuId() == pm.getMenuId()){
					PUlList ulList = new PUlList();
					ulList.setSortCode(roleV2.getSortCode());
					ulList.setDomId(roleV2.getMenuId());
					ulList.setText(roleV2.getMenuName());
					ulList.setIconCls(roleV2.getIconCls());
					ulList.setUrl(roleV2.getUrl());
					pm.getUls().add(ulList);
					it2.remove();
				}
			}
			l_menuInfos.add(pm);
		}
		Collections.sort(l_menuInfos);
		return l_menuInfos;
	}
	
}
