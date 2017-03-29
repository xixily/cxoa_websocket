package com.chaoxing.oa.controller.inner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;
import com.chaoxing.oa.service.SystemService;

@Controller
@RequestMapping("/system")
public class SystemController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	@RequestMapping(value = "/queryMenus")
	@ResponseBody
	public List<PMenus> getMnenus(PMenus pmenu){
		List<PMenus> pmenus = systemService.getMenus(pmenu);
//		for (PMenuc pMenuc : pmenus) {
//			System.out.println("parent====>>:");
//			Set<PMenuc> ps = pMenuc.getChildren();
//			System.out.println("size:" +ps.size());
//			for (PMenuc menu : ps) {
//				System.out.println("c:" + menu.getMenuName());
//			}
//		}
		return pmenus;
	}
	
	@RequestMapping(value = "/getAllMenus")
	@ResponseBody
	public Map<String,Object> queryJiagou(PMenus_ pmenus){
		if(pmenus==null){
			Map<String,Object> meunsInfo = systemService.findAllMenus(pmenus);
			return meunsInfo;
		}
		return null;
	}
}
