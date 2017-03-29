package com.chaoxing.oa.controller.pub;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.entity.page.pub.hetong.UserList;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubHetongService;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/public/hetong")
public class PubHetongController {
	@Autowired
	private PubHetongService pubHetongService;
	
	@RequestMapping(value = "/helloPublic")
	@ResponseBody
	public Json helloc(HttpSession session){
		Json result = new Json();
		result.setSuccess(true);
		result.setMsg("hello public ~!");
		return result;
	}
	
	@RequestMapping(value="/getAllSubordinate")
	@ResponseBody
	public Json getAllSubordinate(String email, Integer id, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		email = (null!=email && !"".equals(email)) ? email:sessionInfo.getEmail();
		String level = sessionInfo.getLevel();
		if(null!=level && "指导".equals(level)){
			if(null==email || "".equals(email)){
				return getAllCells(session);
			}else{
				return getCoreCells(email, session);
			}
		}else if(null!=level && "细胞核".equals(level)){
			return getCoreCells(email, session);
		}else{
			result.setMsg("对不起，您不是细胞核或者指导，无法查看。");
		}
		return result;
	}
	
	@RequestMapping(value = "/getAllCellCores")
	@ResponseBody
	public Json getAllCells(HttpSession session){
		SessionInfo sessionInfo = getSessionInfo(session);
		Json result = new Json();
		String level = sessionInfo.getLevel();
		String email = sessionInfo.getEmail();
		if(null!=level && "指导".equals(level)){
			result.setObj(pubHetongService.findCellCoresCount(email));
			result.setSuccess(true);
		}else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	} 
	
	@RequestMapping(value="getCoreCells")
	@ResponseBody
	public Json getCoreCells(String email, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		email = (null!=email&&!"".equals(email)?email:sessionInfo.getEmail());
		String level = sessionInfo.getLevel();
		String semail = sessionInfo.getEmail();
		if(null!=level && "细胞核".equals(level)){
			result.setObj(pubHetongService.findCoreCellsCount(semail));
			result.setSuccess(true);
		}else if(null!=level && "指导".equals(level)){
			if(null!=email && !"".equals(email)){
				result.setObj(pubHetongService.findCoreCellsCount(email));
				result.setSuccess(true);
			}else{
				result.setMsg("缺少email！~");
			}
		}else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	}
	
	@RequestMapping(value="getUserList")
	@ResponseBody
	public Json getUserList(String email, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		email = (null!=email&&!"".equals(email)?email:sessionInfo.getEmail());
		if(null!=email && !"".equals(email)){
			String semail = sessionInfo.getEmail();
			PRenshiEmployee pemployee = pubHetongService.getUserByEmai(email);
			if(null!=pemployee){
				if(semail.equals(pemployee.getEmail()) || semail.equals(pemployee.getCellCoreEmail()) || semail.equals(pemployee.getGuidanceEmail())){
					result.setObj(pubHetongService.findUserListCount(email, pemployee.getUsername()));
					result.setSuccess(true);
				}else{
					result.setMsg("您没有查看该销售用户列表的权限");
				}
			}else{
				result.setMsg("对不起，没有找到该员工信息。");
			}
		}else{
			result.setMsg("email信息为空，无法查询！~");
		}
		return result;
	}
	
	@RequestMapping(value="getContracts")
	@ResponseBody
	public Json getContracts(Integer id, HttpSession session){
		Json result = new Json();
		if(null!=id && id!=0){
			SessionInfo sessionInfo = getSessionInfo(session);
			String level = sessionInfo.getLevel();
			level = "指导";
			String email = sessionInfo.getEmail();
			email = "chuanming@chaoxing.com";
//			PRenshiEmployee pemployee = pubHetongService.getUserByEmai(email);
			pubHetongService.findUserList();
			PCustomerDepart pcd = pubHetongService.getUserList(id);
			if(email.equals(pcd.getEmail()) || email.equals(pcd.getCemail()) || email.equals(pcd.getGemail())){
				pubHetongService.findUserContractsCount(id);
			}
			pubHetongService.findUserContractsCount(id);
			Page page = new Page();
			page.setPage(1);
			PFapiao pfapiao = new PFapiao();
			pfapiao.setId(id);
			pubHetongService.findContractFapiaos(page, pfapiao);
			if(null!=level && "指导".equals(level)){
				result.setObj(pubHetongService.findCellCoresCount(email));
				result.setSuccess(true);
			}else if(null!=level && "细胞核".equals(level)){
				result.setObj(pubHetongService.findCoreCellsCount(email));
				result.setSuccess(true);
			}
			else{
				result.setMsg("只有指导可以查看他下面的细胞核情况~。");
			}
		}else{
			result.setMsg("Id 不存在。");
		}
		return result;
	}
	
	
	@RequestMapping(value="getShoukuan")
	@ResponseBody
	public Json getShoukuan(UserList userList, HttpSession session){
		Json result = new Json();
		if(null!=userList.getAutoCode() && userList.getAutoCode()!=0){
			SessionInfo sessionInfo = getSessionInfo(session);
			String email = sessionInfo.getEmail();
			PCustomerDepart pcd = pubHetongService.getUserList(userList.getAutoCode());
			if(email.equals(pcd.getEmail()) || email.equals(pcd.getCemail()) || email.equals(pcd.getGemail())){
				result.setObj(pubHetongService.findUserFapiao(userList));
				result.setSuccess(true);
			}else{
				result.setMsg("你没有查看这个用户单位的权限。");
			}
		}else{
			result.setMsg("Id 不存在。");
		}
		return result;
	}
	
	@RequestMapping(value="getYingshou")
	@ResponseBody
	public Json getYingshou(Integer id, HttpSession session){
		Json result = new Json();
		
		if(null!=id && id!=0){
			SessionInfo sessionInfo = getSessionInfo(session);
			String email = sessionInfo.getEmail();
			PCustomerDepart pcd = pubHetongService.getUserList(id);
			if(email.equals(pcd.getEmail()) || email.equals(pcd.getCemail()) || email.equals(pcd.getGemail())){
				result.setObj(pubHetongService.findYingshouCount(id));
				result.setObj(pubHetongService.findYingshouCount(id));
				result.setSuccess(true);
			}else{
				result.setMsg("你没有查看这个用户单位的权限。");
			}
		}else{
			result.setMsg("Id 不存在。");
		}
		return result;
	}
	
	@RequestMapping(value="getContractDetails")
	@ResponseBody
	public Json getContractDetails(Integer did, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		String level = sessionInfo.getLevel();
		level = "指导";
		String email = sessionInfo.getEmail();
		email = "chuanming@chaoxing.com";
		if(null!=level && "指导".equals(level)){
			result.setObj(pubHetongService.findCellCoresCount(email));
			result.setSuccess(true);
		}else if(null!=level && "细胞核".equals(level)){
			result.setObj(pubHetongService.findCoreCellsCount(email));
			result.setSuccess(true);
		}
		else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	}
	
	@RequestMapping(value = "/getUserlistCount")
	@ResponseBody
	public Json getUserlistCount(String semail, HttpSession session){
		SessionInfo sessionInfo = getSessionInfo(session);
		Json result = new Json();
		String email = sessionInfo.getEmail();
		if(null!=semail && !"".equals(semail)){
			int leader = ifCell(email,semail);
			if(leader > -1){
				result.setObj(pubHetongService.findUserListCount(semail, null));
				result.setSuccess(true);
			}
		}
		return result;
	}
	
	private int ifCell(String email, String semail){
		PRenshiEmployee puser = pubHetongService.getUserByEmai(semail);
		if(null != puser){
			if(email.equals(puser.getGuidanceEmail())){
				return 2;
			}else if(email.equals(puser.getCellCoreEmail())){
				return 1;
			}else if(email.equals(puser.getEmail())){
				return 0;
			}
		}
		return -1;
	}
	
	private SessionInfo getSessionInfo(HttpSession session){
		return (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
	}
}
