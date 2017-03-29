package com.chaoxing.oa.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.util.http.IpUtil;
import com.chaoxing.oa.util.system.ResourceUtil;

public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String ip = IpUtil.getIpAddr(request);//ip 过滤
		logger.debug(url);
		boolean ipFlag = false;
		if (excludeUrls.contains(url)) {
			return true;
		} else {
			if(url.contains("/public/")){
				ipFlag = true;
			}
			if(!ipFlag){
				if(ip.contains("192.168.") || ip.contains("127.0.") || ip.contains("localhost")){
					ipFlag = true;
				}else{
					ipFlag = false;
				}
			}
			if(ipFlag){
				SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
				if(null == sessionInfo) return false;
				if (sessionInfo.getRoleId()==0) {// 超管不需要验证权限
					return true;
				} else {
					List<String> urls = sessionInfo.getResourceUrls();
					if (urls.contains(url)) {
						return true;
					} else {
						request.setAttribute("msg", "对不起，您没有访问此资源的权限！");
						request.getRequestDispatcher("/error/noSecurity.jsp").forward(request, response);
						return false;
					}
				}
			}else{
				request.setAttribute("msg", "{\"msg\":\"您的ip不允许访问该资源，请使用公司内部IP访问该资源。\"}");
					request.getRequestDispatcher("/error/noSecurity.jsp").forward(request, response);
					return false;
			}
		}
	}
}
