package com.chaoxing.oa.controller.inner;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
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
	
	
	@RequestMapping(value = "/getMenus")
	@ResponseBody
	public Map<String,Object> allMenus(PMenus_ pmenus){
		return systemService.findMenus(pmenus);
	}
	
	@RequestMapping(value = "/updateLevel")
	@ResponseBody
	public Json upMenuLevel(PMenus_ pmenus){
		Json result = new Json();
		if(null != pmenus.getMenuId()){
			Integer num = systemService.updateMenuLevel(pmenus);
			if(num>0){
				result.setSuccess(true);
				result.setObj(num);
				result.setMsg("升级成功！~");
			};
		}else{
			result.setMsg("菜单id不存在");
		}
		return result;
	}
	
//	@RequestMapping(value = "/downMenuLevel")
//	@ResponseBody
//	public Json downMenuLevel(PMenus_ pmenus){
//		Json result = new Json();
//		Integer num = systemService.updateMenuLevel(pmenus);
//		if(num>0){
//			result.setSuccess(true);
//			result.setObj(num);
//			result.setMsg("降级成功！~");
//		};
//		return result;
//	}

	@RequestMapping(value = "/removeMenus")
	@ResponseBody
	public Json removeMenus(PMenus_ pmenus){
		Json result = new Json();
		if(systemService.removeMenu(pmenus)>0){
			result.setSuccess(true);
			result.setMsg("删除成功！~");
		};
		return result;
	}
	
	@RequestMapping(value = "/updateMenu")
	@ResponseBody
	public Json updateMenu(PMenus_ pmenus){
		Json result = new Json();
		Integer num = systemService.updateMenu(pmenus);
		if(num > 0){
			result.setSuccess(true);
			result.setObj(num);
			result.setMsg("更新成功！~");
		};
		return result;
	}
	
	@RequestMapping(value = "/saveMenu")
	@ResponseBody
	public Json saveMenu(PMenus_ pmenus){
		Json result = new Json();
		Serializable num = systemService.svaeMenu(pmenus);
		if(num != null){
			result.setSuccess(true);
			result.setMsg("保存成功！~");
			result.setObj(num);
		};
		return result;
	}
}
