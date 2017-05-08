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
import com.chaoxing.oa.entity.page.system.PUserRole;
import com.chaoxing.oa.entity.po.system.Menu;
import com.chaoxing.oa.entity.po.system.RoleResources;
import com.chaoxing.oa.entity.po.system.SystemConfig;
import com.chaoxing.oa.entity.po.system.UserRole;
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
	@Autowired
	private BaseDaoI<RoleResources> roResourcesDao;
	@Autowired
	private BaseDaoI<UserRole> roleDao;

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
	public boolean getCaiwushRoleId(Integer urole) {
		String roleId = (String) CacheManager.getInstance().get(SysConfig.CACHE_CW + SysConfig.USER_ROLE_ID);
		if(null == roleId){
			StringBuffer hql = new StringBuffer("from SystemConfig t where t.configType=:type and name=:name");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("type", SysConfig.CW_BX_SH);
			params.put("name", SysConfig.USER_ROLE_ID);
			SystemConfig sys = systemConfigDao.get(hql.toString(),params);
			if(null != sys){
				roleId = String.valueOf(sys.getValue());
			}
			CacheManager.getInstance().put(SysConfig.CACHE_CW + SysConfig.USER_ROLE_ID, roleId);
		}
		return roleId.contains("" + urole);
	}
	
	@Override
	public Map<String, Object> findMenus(PMenus_ pmenus) {
		Map<String,Object> results = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select menuId,menuName,menuLevel,preMenuid,url,iconCls,sortCode from 菜单表  where 1=1 ");
		Map<String, Object> params = null;
		if(null != pmenus.getMenuId()){
			params = new HashMap<String, Object>();
			sql.append("and preMenuId=:preMenuId");
			params.put("preMenuId", pmenus.getMenuId());
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
				if(null != pm.getMenuLevel() && pm.getMenuLevel()<3){
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
		if(null != pmenus.getMenuLevel()){
			Map<String, Object> params = new HashMap<String, Object>();
			Menu preMenu = new Menu();
			preMenu.setMenuId(pmenus.get_preMenuId());
			String hql = "update Menu set preMenuId=:preMenuId,menuLevel=:menuLevel where menuId=:menuId";
			params.put("preMenuId", preMenu);
			params.put("menuLevel", pmenus.getMenuLevel());
			params.put("menuId", pmenus.getMenuId());
			return menuDao.executeHql(hql, params);
		}else{
			return 0 ;
		}
	}
	
	@Override
	public int removeMenu(PMenus_ pmenus) {
		if(null != pmenus.getMenuId()){
			Menu menu = new Menu();
			menu.setMenuId(pmenus.getMenuId());
			try {
				menuDao.delete(menu);
				return 1;
			} catch (Exception e) {
				return 0;
			}
		}
		return 0 ;
	}
	
	@Override
	public int updateMenu(PMenus_ pmenus) {
		String sql = "update 菜单表 t set t.menuName=:menuName,t.menuLevel=:menuLevel,t.preMenuId=:preMenuId,t.url=:url,t.iconCls=:iconCls,t.sortCode=:sortCode where t.menuId = :menuId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuName", pmenus.getMenuName());
		params.put("menuLevel", pmenus.getMenuLevel());
		params.put("preMenuId", pmenus.get_preMenuId());
		params.put("url", pmenus.getUrl());
		params.put("iconCls", pmenus.getIconCls());
		params.put("sortCode", pmenus.getSortCode());
		params.put("menuId", pmenus.getMenuId());
		try{
			return menuDao.executeSql(sql, params);
		}catch(Exception e){
			logger.error("[SystemServiceImpl.updateMenu] with a error:" + e);
			return 0;
		}
//		Menu menu = new Menu();
//		Menu preMenu = new Menu();
//		BeanUtils.copyProperties(pmenus, menu);
//		preMenu.setMenuId(pmenus.get_preMenuId());
//		menu.setPreMenuId(preMenu);
//		try {
//			menuDao.update(menu);
//			return 1;
//		} catch (Exception e) {
//			logger.error("[SystemServiceImpl.updateMenu] with a error:" + e);
//			return 0;
//		}
	}
	
	@Override
	public Serializable saveMenu(PMenus_ pmenus) {
		Menu menu = new Menu();
		Menu preMenu = null;
		if(null != pmenus.get_preMenuId()){
			preMenu = new Menu();
		}
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
	
	@Override
	public Map<String, Object> findRoles(PUserRole role) {
		StringBuffer sql = new StringBuffer("select roleId,roleName,rolevel,preId from userrole where 1=1 ");
		Map<String, Object> params = null;
		Map<String, Object> results = new HashMap<String, Object>();
		if(null != role.getRoleId()){
			params = new HashMap<String, Object>();
			sql.append("and (preId=:preId or roleId=:roleId) ORDER BY rolevel ASC ");
			params.put("preId", role.getRoleId());
			params.put("roleId", role.getRoleId());
		}
		List<Object> rs = objDao.findSql(sql.toString(),params);
		List<PUserRole> roles = new ArrayList<PUserRole>();
		Iterator<Object> it = rs.iterator();
		Object[] obj = null;
		PUserRole prole = null;
		int i = 0 ;
		while(it.hasNext()){
			obj = (Object[]) it.next();
			prole = new PUserRole();
			if(obj.length==4){
				prole.setRoleId((Integer) obj[0]);
				prole.setRoleName((String) obj[1]);
				prole.setRoleLevel((Integer) obj[2]);
				prole.setPreId((Integer) obj[3]);
				if(i == 0){//让它的头指向控，便于easyui gridtree 找到最高层
					prole.set_parentId(null);
				}
				if(null != prole.getRoleLevel() && prole.getRoleLevel()<3){
					prole.setState("closed");
				}
			}
			roles.add(prole);
			i++;
		}
		results.put("rows", roles);
		results.put("total", rs.size());
		return results;
	}
	
	@Override
	public Map<String, Object> findRoresoucesByrid(Integer rid) {
		String sql = new String("select menuId,menuName,menuLevel,preMenuid,url,iconCls,sortCode from roleresources where roleId = :rid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rid", rid);
		Map<String, Object> results = new HashMap<String, Object>();
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
				if(null != pm.getMenuLevel() && pm.getMenuLevel()<3){
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
	public List<Integer> findParentResoucerIds(Integer roleId) {
		String sql = "select menuId from 角色资源 where roleid=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<Object> objs = objDao.findSql(sql, params);
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < objs.size(); i++) {
			ids.add((Integer) objs.get(i));
		}
		return ids;
	}
	@Override
	public int addMenus(List<Integer> ids, Integer roleId, Integer sid) {
		List<RoleResources> rors = new ArrayList<RoleResources>();
		Menu menu = menuDao.get(Menu.class, sid);
		UserRole role = roleDao.get(UserRole.class, roleId);
		RoleResources rr = null;
		Integer mid = null;
		for (int i = 0; i < ids.size(); i++) {
			mid = ids.get(i);
			if(null !=  mid){
				rr = new RoleResources();
				rr.setMenuId(menuDao.get(Menu.class, mid));
				rr.setRoleId(role);
			}
			rors.add(rr);
		}
		int total = addPreMenuIds(menu, role);// 准备父级菜单
		logger.info("[SystemServiceImpl.addMenus] 动态添加了父级菜单" + total + "个。");
		return roResourcesDao.bigSave(rors);
	}
	
	private int addPreMenuIds(Menu menu, UserRole role){
		int sum = 0;
//		String sql = "select preMenuId from 菜单表  where menuId=:menuId";
		Menu prMenu = menu.getPreMenuId();
		if(null != prMenu){
			String sql = "select id from 角色资源 where roleId=:roleId and menuId=:menuId ";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("roleId", role.getRoleId());
			params.put("menuId", prMenu.getMenuId());
			List<Object> lis = objDao.findSql(sql, params);
			if(lis.size() < 1){
				RoleResources rrs = new RoleResources(role, prMenu);
				try{
					roResourcesDao.save(rrs);
					sum++;
				}catch(Exception e){
					logger.error("SystemServiceImpl.addPreMenuIds:" + e);
				}
			}
			return (sum + addPreMenuIds(prMenu, role));
		}
		return sum;
	}
	
	@Override
	public int removeMenus(List<Integer> ids, Integer roleId) {
		StringBuffer sql = new StringBuffer("delete from 角色资源  where roleId=:roleId and menuId in(");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		Integer mid = null;
		boolean flag = false;
		for (int i = 0; i < ids.size(); i++) {
			mid = ids.get(i);
			if(null !=  mid){
				flag = true;
				sql.append(":m" + i + ",");
				params.put("m" + i, mid);
			}
		}
		String sql2 = null;
		if(flag) sql2 = sql.substring(0, sql.length()-1);
		sql2 += ")";
		System.out.println(sql2);
		return objDao.executeSql(sql2, params);
	}
	
	@Override
	public Serializable  addRole(PUserRole purole) {
		String sql = "insert into userrole(preId, rolevel, roleName) values(:preId,:roleLevel,:roleName)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("preId", purole.getPreId());
		params.put("roleLevel", purole.getRoleLevel());
		params.put("roleName", purole.getRoleName());
//		UserRole urole = new UserRole();
//		urole.setRoleLevel(purole.getRoleLevel());
//		urole.setRoleName(purole.getRoleName());
//		urole.setPreRole(new UserRole(purole.getPreId()));
		try {
			return objDao.executeSql(sql, params);
		} catch (Exception e) {
			logger.error("[SystemService.addRole] with an error:" + e);
		}
		return null;
	}
	
	@Override
	public Integer getPreRoleId(Integer roleId) {
		String sql = "select preid from userrole where roleId=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<Object> objs = objDao.findSql(sql, params);
		Integer preId = null;
		if(objs.size()>0){
			preId = (Integer) objs.get(0);
		}
		return preId;
	}
	
	@Override
	public int removeRole(Integer rid) {
		String hql = "delete from UserRole where roleId=:rid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rid", rid);
		try{
			return roleDao.executeHql(hql, params);
		}catch(Exception e){
			logger.error("[SystemServiceImpl.removeRole] with an error :"  + e);
		}
		return 0;
	}
	
	
	
}
