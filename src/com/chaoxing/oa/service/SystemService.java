package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;
import com.chaoxing.oa.entity.page.system.PUserRole;
import com.chaoxing.oa.entity.po.system.UserRole;

public interface SystemService {
	public List<PMenus> getMenus(PMenus pmenu);

	public Map<String, Object> findAllMenus(PMenus_ pmenus);
	
	public Integer getCaiwushRoleId();

	public Map<String, Object> findMenus(PMenus_ pmenus);

	public int updateMenuLevel(PMenus_ pmenus);
//	public int updateMenuToup(PMenus_ pmenus);

//	public int updateMenuToDown(PMenus_ pmenus);

	public int removeMenu(PMenus_ pmenus);

	public int updateMenu(PMenus_ pmenus);

	public Serializable saveMenu(PMenus_ pmenus);

	public Map<String, Object> findRoles(PUserRole role);

	public Map<String, Object> findRoresoucesByrid(Integer rid);


	/**
	 * <strong>批量添加权限</strong>
	 * @param ids		权限id列表
	 * @param roleId	需要加权限的角色
	 * @param sid		最顶层权限id，为了判断它是否还有上级。
	 * @return
	 */
	public int addMenus(List<Integer> ids, Integer roleId, Integer sid);

	public int removeMenus(List<Integer> ids, Integer roleId);

}
