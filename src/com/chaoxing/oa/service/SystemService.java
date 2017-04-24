package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;

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

	public Serializable svaeMenu(PMenus_ pmenus);

}
