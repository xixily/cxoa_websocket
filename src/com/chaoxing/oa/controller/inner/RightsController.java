package com.chaoxing.oa.controller.inner;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.system.PMenu;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.RoleMenuService;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/menu")
public class RightsController {
	private RoleMenuService menuService;
	
	public RoleMenuService getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(RoleMenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "allMenu")
	@ResponseBody
	public List<PMenu> getRleMenu(HttpSession session, Model model){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		int role = sessionInfo.getRoleId();
		if(role==0){
			return menuService.findAllMenu();
		}
		return menuService.findMenuByRole(role,session);
//		return menuService.findMenu(role,session);
	}
	
}
