package com.chaoxing.oa.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.util.system.ResourceUtil;

@Aspect
@Component
public class LogAspect {
	
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.add*(..))")
	public void insertServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.delete*(..))")
	public void deleteServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.update*(..))")
	public void updateServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.remove*(..))")
	public void removeServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.generate*(..))")
	public void generateServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.find*(..))")
	public void findServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.get*(..))")
	public void getServiceCall(){
	}
	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.*(..))")
	public void allServiceCall(){
		
	}
	
	@AfterReturning(value="allServiceCall()", argNames="rtv", returning="rtv")
	public void allCall(JoinPoint joinPoint, Object rtv){
//		System.out.println("========切面所有的请求========");
//		System.out.println(rtv.toString());
		readMethod(rtv);
	}
	
//	@AfterReturning(value="findServiceCall()", argNames="rtv", returning="rtv")
//	public void findCall(JoinPoint joinPoint, Object rtv){
//		readMethod(rtv);
//	}
//	
//	@AfterReturning(value="getServiceCall()", argNames="rtv", returning="rtv")
//	public void getCall(JoinPoint joinPoint, Object rtv){
//		readMethod(rtv);
//	}
//	
//	@Around(value = "deleteServiceCall()", argNames="rtv")
//	public Object deleteCall(ProceedingJoinPoint joinPoint){
//		Object result = null;
//		//读取session中的用户
//		PLog log = installMylog();
//		if(null == log){
//			System.out.println("没有找到用户session信息，屏蔽删除操作！~");
//			return null;
//		}
//		Object[] args = joinPoint.getArgs();
//		Object deleteObj = null;
//		for (int i = 0; i < args.length; i++) {
//			Object obj = args[i];
//			if(obj instanceof String){
//				System.out.println(obj.toString());//找出删除的ID记录下来
//				if(true){
//					deleteObj = obj;
//				}
//			}
//		}
//		try {
//			joinPoint.proceed();
//		} catch (Throwable e) {
//			log.setContent("");
//			e.printStackTrace();
//		}
//		if(null != deleteObj){
//			//创建日志对象
//			StringBuffer msg = new StringBuffer("用户ID为：[" + log.getId() + "]的用户删除id:[id]的角色");
//		}
//		return result;
//	}
	
//	private  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//         for (Method method : methods) {
//             if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
////                 if (clazzs.length == arguments.length) {
////                	 description = method.getAnnotation(SystemControllerLog. class).description();
////                     break;
////                }
//            }
//        }
//         return description;
//    }
	
	private HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	private SessionInfo getSessionInfo(){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		return sessionInfo;
	}
	
//	private PLog installMylog(){
//		PLog log = null;
//		HttpServletRequest request = getRequest();
//		SessionInfo sessionInfo = getSessionInfo();
//		
//		if(null != sessionInfo){
//			log = new PLog();
//			log.setUserId(sessionInfo.getId());
//			log.setId((long)sessionInfo.getId());
//			log.setRequestIp(request.getRemoteAddr());
//		}
//		
//		return log;
//	}
	
	private void readMethod(Object rtv){
		//读取session中的用户信息SessionInfo
		SessionInfo sessionInfo = getSessionInfo();
		if(null!=sessionInfo){
			sessionInfo.setResponse(rtv);
		}
	}
}