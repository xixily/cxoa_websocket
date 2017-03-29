package com.chaoxing.oa.controller.pub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PUserName;
import com.chaoxing.oa.entity.page.pub.AppUser;
import com.chaoxing.oa.entity.page.system.PMenu;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.RoleMenuService;
import com.chaoxing.oa.service.UserServiceI;
import com.chaoxing.oa.util.data.DES;
import com.chaoxing.oa.util.http.IpUtil;
import com.chaoxing.oa.util.system.ResourceUtil;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/public/user")
public class PubUserManagerController {
	@Autowired
	private UserServiceI userService;
	@Autowired
	private RoleMenuService menuService;
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login.jsp";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Json logout(HttpSession session, Model model){
		if (session != null) {
			session.invalidate();
			System.out.println("*********session失效**********");
		}
		Json json = new Json();
		json.setMsg("登出成功！");
		json.setSuccess(true);
		return json;
	}
	
	@RequestMapping(value="/applogin",method=RequestMethod.GET)
	public ModelAndView login(String sn, HttpServletRequest request, HttpSession session){
		System.out.println("登录方法applogin 进入。");
		ModelAndView modelView = new ModelAndView("app_login");
		if(null!=sn && StringUtils.isNotBlank(sn)){
			JsonObject jo = DES.getObject(sn);
			if(jo != null){
				AppUser appUser = new AppUser();
				appUser.setEmail(DES.getMail(jo));
//				appUser.setEmail("1huangyun@chaoxing.com");
//				appUser.setEmail("zengchao@chaoxing.com");
				appUser.setEmail("chuanming@chaoxing.com");
//				appUser.setEmail("shihao@chaoxing.com");
//				appUser.setEmail("hailan@chaoxing.com");
//				appUser.setEmail("congcong@chaoxing.com");
				appUser.setName(DES.getName(jo));
				appUser.setUid(DES.getUid(jo));
				appUser.setPhone(DES.getPhone(jo));
				if(null!=appUser.getEmail() && !"".equals(appUser.getEmail())){
					PUserName pu = userService.findUserByEmail(appUser.getEmail());
					if(null != pu){
						SessionInfo sessionInfo = new SessionInfo();
						BeanUtils.copyProperties(pu, sessionInfo);
						sessionInfo.setResourceUrls(userService.finRoleResoures(sessionInfo.getRoleId()));
						sessionInfo.setIp(IpUtil.getIpAddr(request));
						sessionInfo.setLoginMethod(false);
						session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
						System.out.println(sessionInfo);
						modelView.setViewName("redirect:/app_index.html");
					}else{
						modelView = new ModelAndView("error/app_nosession");
					}
				}else{
					modelView = new ModelAndView("error/app_nosession");
				}
			}
		}
		return modelView;
	}
	
	@RequestMapping(value="/applogin",method=RequestMethod.POST)
	public ModelAndView login(String email, String password, HttpServletRequest request, HttpSession session){
		System.out.println("登录方法applogin 进入。");
		ModelAndView modelView = new ModelAndView("/app_login");
		if(null!=email && !"".equals(email) && null!=password && !"".equals(password)){
			SessionInfo sessionInfo = userService.findUser(new QueryForm(email));
			if(null!=sessionInfo && password.equals(sessionInfo.getPassword())){
				sessionInfo.setResourceUrls(userService.finRoleResoures(sessionInfo.getRoleId()));
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
				modelView.setViewName("redirect:/app_index.html");
			}else{
				modelView.addObject("login_check_error", "用户名不存在或者密码不正确!");
			}
		}else{
			modelView.addObject("user_login_error", "用户名或者密码不能为空!");
		}
		return modelView;
	}
	
	@RequestMapping(value="/getMenus")
	@ResponseBody
	public Json getMenus(HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		int role = sessionInfo.getRoleId();
//		role = 500;
		List<PMenu> pmenus = menuService.findPubMenuByRole(role);
		result.setObj(pmenus);
		result.setSuccess(true);
		return result;
	}
}
