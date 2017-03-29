package com.chaoxing.oa.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.chaoxing.oa.entity.page.system.PMenu;

public interface RoleMenuService {
//	public List<PMenu> findMenu(int roleId);
//	public List<PMenu> findMenu(int roleId, HttpSession session);

	public List<PMenu> findAllMenu();
	
	public List<PMenu> findMenuByRole(int roleId,HttpSession session);
	
	public List<PMenu> findPubMenuByRole(int roleId);

//	public void findRoleMenus(int role);

}
