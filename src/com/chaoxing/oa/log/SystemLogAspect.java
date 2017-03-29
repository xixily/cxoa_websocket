//package com.chaoxing.oa.log;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.lang.reflect.Method;
//
//import javax.servlet.http.HttpSession;
//
//import com.chaoxing.oa.annotation.SystemControllerLog;
//import com.chaoxing.oa.annotation.SystemServiceLog;
//import com.chaoxing.oa.entity.page.system.SessionInfo;
//import com.chaoxing.oa.entity.po.system.MyLog;
//import com.chaoxing.oa.service.LogService;
//import com.chaoxing.oa.util.ResourceUtil;
//
////@Aspect
////@Component
//public class SystemLogAspect {
//	@Autowired
//	private LogService logService;
//	@Autowired
//	private BeanFactory beanFactory;
//	private  static  final Logger logger = Logger.getLogger(SystemLogAspect. class);
//	
//	@Pointcut("execution(* com.chaoxing.oa.service..*Impl.insert*(..))")
//	public void serviceAspect(){
//		
//	}
//	
//	@Pointcut("@annotation(com.chaoxing.oa.annotation.SystemControllerLog)")
//	public void controllerAspect(){
//		
//	}
//	/**  
//     * 前置通知 用于拦截Controller层记录用户的操作  
//     *  
//     * @param joinPoint 切点  
//     */    
//    @Before("controllerAspect()")    
//     public  void doBefore(JoinPoint joinPoint) {    
//    
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
//        HttpSession session = request.getSession();    
//        //读取session中的用户    
//        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());    
//        //请求的IP    
//        String ip = request.getRemoteAddr();    
//         try {    
//            //*========控制台输出=========*//    
//            System.out.println("=====前置通知开始=====");    
//            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
//            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));    
//            System.out.println("请求人:" + sessionInfo.getUsername());    
//            System.out.println("请求IP:" + ip);    
//            //*========数据库日志=========*//    
//            MyLog log = (MyLog) beanFactory.getBean("mylog");
//            log.setUserId(sessionInfo.getId());
////            log.setCreatedate();
//            log.setOperation(getControllerMethodDescription(joinPoint));
//            log.setContent(getServiceMthodDescription(joinPoint));
////            Log log = SpringContextHolder.getBean("logxx");    
////            log.setDescription(getControllerMethodDescription(joinPoint));    
////            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
////            log.setType("0");    
////            log.setRequestIp(ip);    
////            log.setExceptionCode( null);    
////            log.setExceptionDetail( null);    
////            log.setParams( null);    
////            log.setCreateBy(user);    
////            log.setCreateDate(DateUtil.getCurrentDate());    
//            //保存数据库    
////            logService.add(log);
//            System.out.println("=====前置通知结束=====");
//        }  catch (Exception e) {    
//            //记录本地异常日志    
//            logger.error("==前置通知异常==");
//            logger.error("异常信息:{}", e);
//        }    
//    }    
//    
//    /**  
//     * 异常通知 用于拦截service层记录异常日志  
//     *  
//     * @param joinPoint  
//     * @param e  
//     */    
//    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
//     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
//        HttpSession session = request.getSession();    
//        //读取session中的用户    
//        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());     
//        //获取请求ip    
//        String ip = request.getRemoteAddr();
//        //获取用户请求方法的参数并序列化为JSON格式字符串    
//        StringBuffer params = new StringBuffer("");    
//         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
//             for ( int i = 0; i < joinPoint.getArgs().length; i++) {
//            	 params.append(joinPoint.getArgs()[i]).append(";");
////                params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";    
//            }    
//        }    
//         try {    
//              /*========控制台输出=========*/    
//            System.out.println("=====异常通知开始=====");    
//            System.out.println("异常代码:" + e.getClass().getName());    
//            System.out.println("异常信息:" + e.getMessage());    
//            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
//            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));    
//            System.out.println("请求人:" + sessionInfo.getUsername());    
//            System.out.println("请求IP:" + ip);    
//            System.out.println("请求参数:" + params);    
//               /*==========数据库日志=========*/    
//            MyLog log = (MyLog) beanFactory.getBean("mylog");
//            log.setUserId(sessionInfo.getId());
////            log.setCreatedate();
//            log.setOperation(getControllerMethodDescription(joinPoint));
//            log.setContent(getServiceMthodDescription(joinPoint));
//            log.setRequsetIp(ip);
////            log.setDescription(getServiceMthodDescription(joinPoint));    
////            log.setExceptionCode(e.getClass().getName());    
////            log.setType("1");    
////            log.setExceptionDetail(e.getMessage());    
////            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
////            log.setParams(params);    
////            log.setCreateBy(user);    
////            log.setCreateDate(DateUtil.getCurrentDate());    
////            log.setRequestIp(ip);    
//            //保存数据库    
////            logService.add(log);    
//            System.out.println("=====异常通知结束=====");    
//        }  catch (Exception ex) {    
//            //记录本地异常日志    
//            logger.error("==异常通知异常==");
//            logger.error("异常信息:{}", e);
////            logger.error("异常信息:{}", ex.getMessage());
//        }    
//         /*==========记录本地异常日志==========*/    
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}" + joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()+ e.getClass().getName() + e.getMessage() + params.toString());    
//    
//    }    
//    
//    
//    /**  
//     * 获取注解中对方法的描述信息 用于service层注解  
//     *  
//     * @param joinPoint 切点  
//     * @return 方法描述  
//     * @throws Exception  
//     */    
//     public  static String getServiceMthodDescription(JoinPoint joinPoint)    
//             throws Exception {    
//        String targetName = joinPoint.getTarget().getClass().getName();    
//        String methodName = joinPoint.getSignature().getName();    
//        Object[] arguments = joinPoint.getArgs();    
//        Class targetClass = Class.forName(targetName);    
//        Method[] methods = targetClass.getMethods();    
//        String description = "";    
//         for (Method method : methods) {    
//             if (method.getName().equals(methodName)) {    
//                Class[] clazzs = method.getParameterTypes();    
//                 if (clazzs.length == arguments.length) {    
//                    description = method.getAnnotation(SystemServiceLog. class).description();    
//                     break;    
//                }    
//            }    
//        }    
//         return description;    
//    }    
//    
//    /**  
//     * 获取注解中对方法的描述信息 用于Controller层注解  
//     *  
//     * @param joinPoint 切点  
//     * @return 方法描述  
//     * @throws Exception  
//     */    
//     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//         for (Method method : methods) {
//             if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                 if (clazzs.length == arguments.length) {
//                	 description = method.getAnnotation(SystemControllerLog. class).description();
//                     break;
//                }
//            }
//        }
//         return description;
//    }
//}
