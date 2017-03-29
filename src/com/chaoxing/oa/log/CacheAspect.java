//package com.chaoxing.oa.log;
//
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.chaoxing.oa.service.LogService;
//
//public class CacheAspect {
//
//	@Autowired
//	private LogService logService;
//	@Pointcut("@annotation(com.chaoxing.oa.annotation.SystemControllerLog)")
//	public void controllerPoint(){
//	}
//	@Pointcut("@annotation(com.chaoxing.oa.annotation.SystemServiceLog)")
//	public void servicePoint(){
//	}
//	
//}
