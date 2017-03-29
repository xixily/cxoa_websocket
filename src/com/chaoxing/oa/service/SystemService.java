package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;

public interface SystemService {
	public List<PMenus> getMenus(PMenus pmenu);

	public Map<String, Object> findAllMenus(PMenus_ pmenus);
}
