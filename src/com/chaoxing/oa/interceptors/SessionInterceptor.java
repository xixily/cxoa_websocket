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

public class SessionInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

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
//		logger.debug(url);
		if (excludeUrls.contains(url)) {
			return true;
		} else {
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
			if (sessionInfo != null && sessionInfo.getId() != 0) {
				return true;
			} else {
				System.out.println("登录超时~！");
				request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
				request.setAttribute("obj", "1000");
//				response.sendRedirect("/cxoa/error/nosession.json");
				request.getRequestDispatcher("/error/noSession.jsp").forward(request, response);
				return false;
			}
		}
	}

}
