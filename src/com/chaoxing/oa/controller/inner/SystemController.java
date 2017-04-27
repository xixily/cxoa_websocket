package com.chaoxing.oa.controller.inner;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;
import com.chaoxing.oa.entity.page.system.PUserRole;
import com.chaoxing.oa.entity.po.system.UserRole;
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
	public Map<String,Object> allMenus(PMenus_ pmenus, Integer id){
		if(null != id) pmenus.setMenuId(id);
		return systemService.findMenus(pmenus);
	}
	
	@RequestMapping(value = "/updateLevel")
	@ResponseBody
	public Json upMenuLevel(PMenus_ pmenus){
		Json result = new Json();
		if(null != pmenus.getMenuId() && null != pmenus.getMenuLevel()){
			Integer num = systemService.updateMenuLevel(pmenus);
			if(num>0){
				result.setSuccess(true);
				result.setObj(num);
				result.setMsg("改变级别成功！~");
			};
		}else{
			result.setMsg("没有找到该记录或者数据错误，请刷新后重试。");
		}
		return result;
	}

	@RequestMapping(value = "/removeMenus")
	@ResponseBody
	public Json removeMenus(PMenus_ pmenus){
		Json result = new Json();
		if(null != pmenus.getMenuId()){
			if(systemService.removeMenu(pmenus)>0){
				result.setSuccess(true);
				result.setMsg("删除成功！~");
			};
		}else{
			result.setMsg("没有找到该记录或者数据错误，请刷新后重试。");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateMenu")
	@ResponseBody
	public Json updateMenu(PMenus_ pmenus){
		Json result = new Json();
		if(null != pmenus.getMenuId() && null != pmenus.getMenuLevel()){
			if(null == pmenus.getMenuLevel() || pmenus.getMenuLevel().equals("")){
				pmenus.setSortCode("Z999");
			}
			Integer num = systemService.updateMenu(pmenus);
			if(num > 0){
				result.setSuccess(true);
				result.setObj(num);
				result.setMsg("更新成功！~");
			};
		}else{
			result.setMsg("没有找到该记录或者数据错误，请刷新后重试。");
		}
		return result;
	}
	
	@RequestMapping(value = "/saveMenu")
	@ResponseBody
	public Json saveMenu(PMenus_ pmenus){
		Json result = new Json();
		if(null != pmenus.getMenuLevel()){
			if(null == pmenus.getMenuLevel() || pmenus.getMenuLevel().equals("")){
				pmenus.setSortCode("Z999");
			}
			Serializable num = systemService.saveMenu(pmenus);
			if(num != null){
				result.setSuccess(true);
				result.setMsg("保存成功！~");
				result.setObj(num);
			};
		}else{
			result.setMsg("数据存在错误，请您刷新后重试。");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryRoles")
	@ResponseBody
	public Map<String,Object> queryRoles(PUserRole role, Integer id){
		if(null != id) role.setRoleId(id);
		return systemService.findRoles(role);
	}
	
	@RequestMapping(value = "/queryResourceById")
	@ResponseBody
	public Map<String,Object> queryResourecesById(@RequestParam(required = true) Integer rid){
		if(rid != 0){
			return systemService.findRoresoucesByrid(rid);
		}
		return null;
	}
	
	@RequestMapping(value = "/addRoleMenus")
	@ResponseBody
	public Json addRoleMenus(@RequestParam(required = true, value = "ids[]") List<Integer> ids, Integer roleId, Integer sid){
		Json result = new Json();
		System.out.println(ids);
		if(null != roleId && null != ids){
			systemService.addMenus(ids, roleId, sid);
		}else{
			result.setMsg("没有角色id。");
		}
		return null;
	}
	
	@RequestMapping(value = "/removeRoleMenus")
	@ResponseBody
	public Json removeRoleMenus(@RequestParam(required = false, value = "ids[]") List<Integer> ids,@RequestParam(required=true) Integer roleId){
		Json result = new Json();
		System.out.println(ids);
		if(null != roleId && null != ids){
			System.out.println("已经删除["+systemService.removeMenus(ids, roleId)+"]条数据。");;
		}else{
			result.setMsg("没有角色id。");
		}
		return null;
	}
//	@RequestMapping(value = "/getRoleMenus")
//	@ResponseBody
//	public Map<String, Object> getRoleMenus(@RequestParam(required = true) Integer roleId){
//		return systemService.findRoresoucesByrid(roleId);
//	}
	
	
}
