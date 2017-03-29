package com.chaoxing.oa.interceptors;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.system.PLog;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.LogService;
import com.chaoxing.oa.util.system.ResourceUtil;

public class LogInterceptor implements HandlerInterceptor {
	
	private List<String> excludeUrls;
	@Autowired
	private LogService logService;
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (!excludeUrls.contains(url)) {
			if(request.isRequestedSessionIdValid()){
				SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
				if(null != sessionInfo){
					sessionInfo.setResponse(null);
				}
			}
			return ;
		}
		if(request.isRequestedSessionIdValid()){
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
			if(null == sessionInfo){
				return ;
			}
			Enumeration<?> enu = request.getParameterNames();
			StringBuffer str1 = new StringBuffer("queryForm:[");
			PLog log = new PLog();
			while(enu.hasMoreElements()){
				String paraName = (String) enu.nextElement();
				str1.append("{" + paraName + ":" + request.getParameter(paraName) + "}, ");
			}
			String str = "";
			if(str1.length()> 11){
				System.out.println(str);
				str = str1.substring(0, str1.length()-2) + "]";
			}else{
				str = str1.toString() + "]";
			}
			log.setContent(str);
			log.setMethod(url);
			log.setRequestIp(request.getRemoteAddr());
			log.setUserId(sessionInfo.getId());
			if (excludeUrls.contains(url)) {
//				System.out.println("文件下载，文件下载");
				return;
			}
			if(null != sessionInfo.getResponse()){
				if(sessionInfo.getResponse().toString().length()>1024){
					log.setResult(sessionInfo.getResponse().toString().substring(0,1024));
				}else{
					log.setResult(sessionInfo.getResponse().toString());
				}
			}
			sessionInfo.setResponse(null);
			logService.addlog(log);
//			System.out.println(str);
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		return true;
	}

}
