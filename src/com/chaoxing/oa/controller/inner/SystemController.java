package com.chaoxing.oa.controller.inner;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.system.PMenus;
import com.chaoxing.oa.entity.page.system.PMenus_;
import com.chaoxing.oa.entity.page.system.PUserRole;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.SystemService;
import com.chaoxing.oa.util.system.ResourceUtil;

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
	
	@RequestMapping(value="addRole")
	@ResponseBody()
	public Json addRole(PUserRole purole, HttpSession session){
		Json result = new Json();
		if(null != purole.getPreId() && null != purole.getRoleLevel() && null != purole.getRoleName()){
			if(purole.getRoleLevel()>3){
				result.setMsg("角色级别低于最低级别限制，不能添加。");
				return result;
			}
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			Integer roleId = sessionInfo.getRoleId();
			if(roleId == 1 || ifParent(roleId, purole.getPreId())){
				Serializable id = systemService.addRole(purole);
				if(null != id){
					result.setSuccess(true);
					result.setMsg("添加成功。");
				}else{
					result.setMsg("保存失败。");
				}
			}else{
				result.setMsg("添加失败，您只可以添加您的子集角色。");
			}
		}else{
			result.setMsg("缺少必要信息，请确认。");
		}
		return result;
	}
	
	private boolean ifParent(Integer roleId, Integer pid) {
		if(null == roleId || null == pid){
			return false;
		}
		if(roleId.equals(pid) || roleId == 1){
			return true;
		}
		while(null != pid && !roleId.equals(pid)){
			pid = systemService.getPreRoleId(pid);
			if(null != pid && roleId.equals(pid)){
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value="removeRole")
	@ResponseBody()
	public Json removeRole(@RequestParam(required = true) Integer rid, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Integer roleId = sessionInfo.getRoleId();
		if(roleId == 1 || ifParent(roleId, rid)){
			if(systemService.removeRole(rid) > 0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败,请您检查是否还有角色id为[" + rid + "]的人");
			}
		}else{
			result.setMsg("添加失败，您只可以删除您的子集角色。");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryRoles")
	@ResponseBody
	public Map<String,Object> queryRoles(PUserRole role, Integer id, HttpSession session){
		if(null != id) role.setRoleId(id);
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Integer roleId = sessionInfo.getRoleId();
		if(null == role.getRoleId()){
			role.setRoleId(roleId);
		}
		if(ifParent(roleId, role.getRoleId())){
			return systemService.findRoles(role);
		}
		return null;
	}
	
	@RequestMapping(value = "/queryResourceById")
	@ResponseBody
	public Map<String,Object> queryResourecesById(@RequestParam(required = true) Integer rid){
		if(rid != 1){
			return systemService.findRoresoucesByrid(rid);
		}else{
			return systemService.findMenus(new PMenus_());
		}
	}
	
	@RequestMapping(value = "/addRoleMenus")
	@ResponseBody
	public Json addRoleMenus(@RequestParam(required = true, value = "ids[]") List<Integer> ids,@RequestParam(required=true) Integer roleId,@RequestParam(required=true) Integer sid,HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Integer sroleId = sessionInfo.getRoleId();
		if(null != roleId && null != ids && null!= sid){
			if(sroleId != 1){
				List<Integer> pResources = systemService.findParentResoucerIds(sroleId);//获取当前用户下所有权限
				StringBuffer msg = new StringBuffer("");
				for (int i = 0; i < ids.size(); i++) {
					if(pResources.indexOf(ids.get(i)) < 0){
						msg.append(ids.get(i) + ",");
						ids.remove(ids.get(i));
					}
				}
				if(msg.length() > 0){
					result.setMsg("父级角色没有菜单Id为：[" + msg.toString() + "]的权限，请先为父级角色添加。");
					return result;
				}
			}
			int total = systemService.addMenus(ids, roleId, sid);
			if(total > 0){
				result.setSuccess(true);
				result.setMsg("添加角色权限成功，已添加[" + total + "]个权限");
			}
		}else{
			result.setMsg("没有角色id。");
		}
		return result;
	}
	
	@RequestMapping(value = "/removeRoleMenus")
	@ResponseBody
	public Json removeRoleMenus(@RequestParam(required = false, value = "ids[]") List<Integer> ids,@RequestParam(required=true) Integer roleId){
		Json result = new Json();
		System.out.println(ids);
		if(null != roleId && null != ids){
			result.setSuccess(true);
			result.setMsg("已经删除["+systemService.removeMenus(ids, roleId)+"]条数据。");
//			System.out.println();;
		}else{
			result.setMsg("没有角色id。");
		}
		return result;
	}
//	@RequestMapping(value = "/getRoleMenus")
//	@ResponseBody
//	public Map<String, Object> getRoleMenus(@RequestParam(required = true) Integer roleId){
//		return systemService.findRoresoucesByrid(roleId);
//	}
	
//	@RequestMapping(value = "/test")
//	@ResponseBody
//	public Json test(){
//		Json result = new Json();
//		result.setObj(systemService.findParentResoucerIds(100));
////		System.out.println(systemService.findParentResoucerIds(1000));
//		return result;
//	}
	
	
}
