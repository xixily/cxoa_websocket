package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;
import com.chaoxing.oa.entity.po.system.Menu;
import com.chaoxing.oa.entity.po.system.SystemConfig;
import com.chaoxing.oa.service.SystemService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
	private Logger logger = Logger.getLogger(this.getClass());
	private BaseDaoI<Menu> menuDao;
	@Autowired
	private BaseDaoI<SystemConfig> systemConfigDao;
	@Autowired
	private BaseDaoI<Object> objDao;

	public BaseDaoI<Menu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<PMenus> getMenus(PMenus pmenu) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<PMenus> pmenus = new ArrayList<PMenus>();
		StringBuffer hql = new StringBuffer("from Menu t where 1=1 and t.menuLevel=1 ");
		addconditions(params, pmenu, hql);
		List<Menu> menus = menuDao.find(hql.toString(), params);
		for (Menu menu : menus) {
			PMenus _menu = new PMenus();
			BeanUtils.copyProperties(menu, _menu);
			if(menu.getPreMenuId()!=null){
				_menu.set_preMenuId(menu.getPreMenuId().getMenuId());
			}
			copy(menu.getMenus(),_menu);
			pmenus.add(_menu);
		}
		return pmenus;
	}
	
	private void copy(Set<Menu> menus, PMenus _menu) {
		if(menus.size()>0){
			Set<PMenus> children = new TreeSet<PMenus>();
			for (Menu menu : menus) {
				PMenus p = new PMenus();
				p.setMenuId(menu.getMenuId());
				p.setMenuName(menu.getMenuName());
				p.setMenuLevel(menu.getMenuLevel());
				p.set_preMenuId(menu.getPreMenuId().getMenuId());
				p.setUrl(menu.getUrl());
				p.setIconCls(menu.getIconCls());
				if(menu.getMenus().size()>0){
					copy(menu.getMenus(),p);
				}
				children.add(p);
			}
			_menu.setChildren(children);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllMenus(PMenus_ pmenus) {
		Map<String, Object> menusInfo = new HashMap<String, Object>();
		if(null == CacheManager.getInstance().get(SysConfig.CACHE_SYSTEM + SysConfig.SYSTEM_MENUS)){
			StringBuffer hql = new StringBuffer("from Menu t where 1=1");
			List<Menu> osList = menuDao.find(hql.toString(),null);
			List<PMenus_> pmenuList = new ArrayList<PMenus_>();
			Iterator<Menu> it = osList.iterator();
			while(it.hasNext()){
				Menu menu = it.next();
				PMenus_ pmenu = new PMenus_();
				BeanUtils.copyProperties(menu, pmenu);
				pmenu.set_preMenuId(menu.getPreMenuId().getMenuId());
				if(menu.getMenuLevel()<3){
					pmenu.setState("closed");
				}
				pmenuList.add(pmenu);
			}
			menusInfo.put("total", osList.size());
			menusInfo.put("rows", pmenuList);
			CacheManager.getInstance().put(SysConfig.CACHE_SYSTEM + SysConfig.SYSTEM_MENUS, menusInfo);
		}else{
			menusInfo = (Map<String, Object>) CacheManager.getInstance().get(SysConfig.CACHE_SYSTEM + SysConfig.SYSTEM_MENUS);
		}
		return menusInfo;
	}
	
	private void addconditions(Map<String, Object> params, PMenus pmenu,StringBuffer hql) {
		if(pmenu != null){
			if(pmenu.getMenuName()!= null && pmenu.getMenuName()!= ""){
				hql.append(" and t.menuName like :menuName ");
				params.put("menuName", "%" + pmenu.getMenuName() + "%");
			}
		}
	}
	
	@Override
	public Integer getCaiwushRoleId() {
		Integer roleId = (Integer) CacheManager.getInstance().get(SysConfig.CACHE_CW + SysConfig.USER_ROLE_ID);
		if(null == roleId){
			StringBuffer hql = new StringBuffer("from SystemConfig t where t.configType=:type and name=:name");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("type", SysConfig.CW_BX_SH);
			params.put("name", SysConfig.USER_ROLE_ID);
			SystemConfig sys = systemConfigDao.get(hql.toString(),params);
			if(null != sys){
				roleId = Integer.valueOf(sys.getValue());
			}
			CacheManager.getInstance().put(SysConfig.CACHE_CW + SysConfig.USER_ROLE_ID, roleId);
		}
		return roleId;
	}
	
	@Override
	public Map<String, Object> findMenus(PMenus_ pmenus) {
		Map<String,Object> results = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select menuId,menuName,menuLevel,preMenuid,url,iconCls,sortCode from 菜单表  where 1=1 ");
		Map<String, Object> params = null;
		if(null != pmenus.getMenuId()){
			params = new HashMap<String, Object>();
			sql.append("and menuId=:menuId");
			params.put("", pmenus.getMenuId());
		}
		List<Object> menus = objDao.findSql(sql.toString(),params);
		List<PMenus_> pms = new ArrayList<>();
		Iterator<Object> it = menus.iterator();
		Object[] obj = null;
		PMenus_ pm = null;
		while(it.hasNext()){
			obj = (Object[]) it.next();
			pm = new PMenus_();
			if(obj.length==7){
				pm.setMenuId((Integer) obj[0]);
				pm.setMenuName((String) obj[1]);
				pm.setMenuLevel((Integer) obj[2]);
				pm.set_preMenuId((Integer) obj[3]);
				pm.setUrl((String) obj[4]);
				pm.setIconCls((String) obj[5]);
				pm.setSortCode((String) obj[6]);
				if(pm.getMenuLevel()<3){
					pm.setState("closed");
				}
			}
			pms.add(pm);
		}
		results.put("rows", pms);
		results.put("total", menus.size());
		return results;
	}
	
	@Override
	public int updateMenuLevel(PMenus_ pmenus) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "update Menu set preMenuId=:preMenuId,menuLevel=:menuLevel where menuId=:menuId";
		params.put("preMenuId", pmenus.get_preMenuId());
		params.put("menuLevel", pmenus.getMenuLevel());
		params.put("menuId", pmenus.getMenuId());
		return menuDao.executeHql(hql, params);
	}
	
//	@Override
//	public int updateMenuToDown(PMenus_ pmenus) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		String hql = "update Menu set preMenuId=:preMenuId,menuLevel=:menuLevel where menuId=:menuId";
//		params.put("preMenuId", pmenus.get_preMenuId());
//		params.put("menuLevel", pmenus.getMenuLevel());
//		params.put("menuId", pmenus.getMenuId());
//		return menuDao.executeHql(hql, params);
//	}
	
	@Override
	public int removeMenu(PMenus_ pmenus) {
		Menu menu = new Menu();
		menu.setMenuId(pmenus.getMenuId());
		try {
			menuDao.delete(menu);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public int updateMenu(PMenus_ pmenus) {
		Menu menu = new Menu();
		Menu preMenu = new Menu();
		BeanUtils.copyProperties(pmenus, menu);
		preMenu.setMenuId(pmenus.get_preMenuId());
		menu.setPreMenuId(preMenu);
		try {
			menuDao.update(menu);
			return 1;
		} catch (Exception e) {
			logger.error("[SystemServiceImpl.updateMenu] with a error:" + e);
			return 0;
		}
	}
	
	@Override
	public Serializable svaeMenu(PMenus_ pmenus) {
		Menu menu = new Menu();
		Menu preMenu = new Menu();
		BeanUtils.copyProperties(pmenus, menu);
		preMenu.setMenuId(pmenus.get_preMenuId());
		menu.setPreMenuId(preMenu);
		try {
			return menuDao.save(menu);
		} catch (Exception e) {
			logger.error("[SystemServiceImpl.svaeMenu] with a error:" + e);
			return 0;
		}
	}
	
	
	
	
}
