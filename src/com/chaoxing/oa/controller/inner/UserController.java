package com.chaoxing.oa.controller.inner;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.caiwu.PCNUsername;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PUserName;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.UserServiceI;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.http.IpUtil;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	
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
	@RequestMapping(value="/login",method=RequestMethod.POST)
//	@RequestMapping(value="/login")
	public ModelAndView login(QueryForm queryForm, HttpServletRequest request, HttpSession session, Model model ){
		ModelAndView modelView = new ModelAndView("login.jsp");
		if(queryForm.getEmail() == null||queryForm.getEmail().equals("")|| queryForm.getPassword() == null||queryForm.getPassword().equals("")){
			modelView.setViewName("login");
			modelView.addObject("user_login_error", "请您输入用户名或者密码");
			return modelView;
		}
		String password = queryForm.getPassword();
		SessionInfo sessionInfo = userService.findUser(queryForm);
		if(null != sessionInfo){
			if(password.equals(sessionInfo.getPassword())){
				sessionInfo.setResourceUrls(userService.finRoleResoures(sessionInfo.getRoleId()));
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
				modelView.setViewName("redirect:/index.jsp");
			}else{
				modelView.setViewName("login");
				modelView.addObject("password_login_error", "您输入的密码不正确！");
			}
		}else{
			modelView.setViewName("login");
			modelView.addObject("user_login_error", "email不存在！");
		}
		return modelView;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Json login(QueryForm queryForm, HttpServletRequest request, HttpSession session){
		Json result = new Json();
		System.out.println("login Get 方法");
		if(queryForm.getEmail() == null||queryForm.getEmail().equals("")|| queryForm.getPassword() == null||queryForm.getPassword().equals("")){
			result.setMsg("请您输入用户名或者密码");
			return result;
		}
		String password = queryForm.getPassword();
		SessionInfo sessionInfo = userService.findUser(queryForm);
		if(null != sessionInfo){
			if(password.equals(sessionInfo.getPassword())){
				sessionInfo.setResourceUrls(userService.finRoleResoures(sessionInfo.getRoleId()));
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
				result.setSuccess(true);
				result.setMsg("登录成功！~");
				System.out.println(sessionInfo);
			}else{
				result.setMsg( "您输入的密码不正确！");
			}
		}else{
			result.setMsg("email不存在！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getUserName")
	@ResponseBody
	public Json getUserName(QueryForm queryForm, HttpSession session) {
		Json result = new Json();
		if(null != queryForm.getId()){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			Integer roleId = sessionInfo.getRoleId();
			if(roleId != 109){
				PUserName pusername = userService.getUserName(queryForm.getId());
				if (pusername != null) {
					result.setSuccess(true);
					result.setMsg("获取员工信息成功");
					result.setObj(pusername);
				} else {
					result.setErrorCode(SysConfig.REQUEST_ERROR);
					result.setMsg("获取员工编号：" + queryForm.getId() + " 职员信息失败！");
				}
			}else{
				return getCNUsername(queryForm,session);
			}
		}else{
			result.setMsg("员工id为空，请确认是否选择员工！");
		}
		return result;
	}
	
	public Json getCNUsername(QueryForm queryForm, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Integer id = sessionInfo.getId();
		PCNUsername pncu = userService.getCNUsername(queryForm.getId(), id);
		if (pncu != null) {
			result.setSuccess(true);
			result.setMsg("获取员工信息成功");
			result.setObj(pncu);
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("获取员工编号：" + queryForm.getId() + " 职员信息失败！");
		}
		return result;
	}

	@RequestMapping(value = "/addUserName")
	@ResponseBody
	public Json addUserName(PUserName username, HttpSession session) {
		Json result = new Json();
		long r = userService.addUserName(username);
		if (r != -1) {
			result.setSuccess(true);
			result.setMsg("添加成功！");
			result.setObj(r);
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("插入失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "deleteUserName")
	@ResponseBody
	public Json deleteUserName(QueryForm pu, HttpSession session) {
		System.out.println(pu);
		Json result = new Json();
		if (pu.getId() == 0) {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("您要查询的职员信息不存在！");
			return result;
		}
		int r = 0;
		r = userService.deleteUserName(pu);
		if (r != 0) {
			result.setSuccess(true);
			result.setMsg("删除成功！");
		} else {
			result.setSuccess(false);
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateUserName")
	@ResponseBody
	public Json updateUserName(PUserName username, HttpSession session) {
		Json result = new Json();
//		long r = 2;
		System.out.println("是否保密:"+username.getIfSecret());
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(sessionInfo.getRoleId()<=1 || sessionInfo.getRoleId()==100){
			System.out.println("是否保密：" + username.getIfSecret());
			if(username.getIfSecret()==null){
				username.setIfSecret("off");
			}
			userService.updateSecret(username);
		}
		long r = userService.updateUserName(username);
		if (r != -1) {
			result.setSuccess(true);
			result.setMsg("更新成功！");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("更新失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateUserRole")
	@ResponseBody
	public Json updateUserRole(PUserName username, HttpSession session) {
		Json result = new Json();
		long r = userService.updateUserRole(username);
		if (r != 0) {
			result.setSuccess(true);
			result.setMsg("更新成功！");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("更新失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/modifyPassword",method=RequestMethod.POST)
	public ModelAndView updatePassword(QueryForm queryForm, HttpSession session, Model model ) {
		ModelAndView modelView = new ModelAndView("login.jsp");
		if(queryForm.getEmail()==null || queryForm.getPassword()==null || queryForm.getNewpassword()==null || queryForm.getNewpassword().length()<8){
			modelView.setViewName("login");
			modelView.addObject("password_modify_error", "更新失败,可能是邮箱或者密码错误！~");
			return modelView;
		}
		long r = userService.updatePassword(queryForm);
		if (r != 0) {
			modelView.setViewName("login");
			modelView.addObject("password_modify_error", "恭喜您，密码修改成功！~");
		} else {
			modelView.setViewName("login");
			modelView.addObject("password_modify_error", "更新失败,可能是邮箱或者密码错误！~");
		}
		return modelView;
	}
	
	@RequestMapping(value = "/modifyPass",method=RequestMethod.POST)
	@ResponseBody
	public Json updatePassword_(QueryForm queryForm, HttpSession session) {
		Json result = new Json();
		if(queryForm.getEmail()==null || queryForm.getPassword()==null || queryForm.getNewpassword()==null || queryForm.getNewpassword().length()<8){
			result.setMsg("您输入的信息有误！~");
			return result;
		}
		long r = userService.updatePassword(queryForm);
		if (r != 0) {
			if (session != null) {
				session.invalidate();
				System.out.println("*********session失效**********");
			}
			result.setSuccess(true);
			result.setMsg("更新成功,请重新登录！~");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("更新失败,可能是邮箱或者密码错误！~");
		}
		return result;
	}
	/*@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseBody
	public Json login(UserInfo userInfo, HttpServletRequest request, HttpSession session){
		String password = userInfo.getPassword();
		userInfo = userService.findUser(userInfo);
		Json data = new Json();
		if(null != userInfo){
			if(password == userInfo.getPassword()){
				userInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userInfo);
				session.setAttribute("companys", userService.getCompany());
//				System.out.println("companys" + session.getAttribute("companys"));
//				System.out.println("sessionInfo" + session.getAttribute("sessionInfo"));
				data.setSuccess(true);
				data.setMsg("登陆成功");
				data.setObj(userInfo);
			}else{
				data.setSuccess(false);
				data.setMsg("您输入的密码有误！");
			}
		}else{
			data.setSuccess(false);
			data.setMsg("您输入的账号不存在！");
		}
		return data;
	}*/
}
