package com.chaoxing.oa.system.cache;

import java.util.List;
import java.util.Timer;


/**
 * TODO 做一个缓存工具，用来存放系统通用缓存，
 * 		包括系统配置和sessioin管理功能。
 * ① 利用spring去自动注入内容。
 * 		启动服务的时候，spring自动去实例化该对象，并从数据库把数据存入该结构体中。
 * @author Administrator
 *
 */
public class CacheUtil{
	public static Timer chacheTimer = new Timer();
	
	public static Object getFromCache(String key){
		return CacheManager.getInstance().get(key);
	}
	
	public static void putInCache(String key, Object value){
		Object obj = getFromCache(key);
		if (obj == null) {
			CacheManager.getInstance().put(key, value);
		} else {
			removeCache(key);
			CacheManager.getInstance().put(key, value);
		}
	}

	public static void removeCache(String key) {
		CacheManager.getInstance().remove(key);
	}
	
	public static int getCacheSize() {
		CacheManager cacheManager = CacheManager.getInstance();
		return cacheManager.getCurrentCacheSize();
	}

	public static int getCacheSize(String key) {
		CacheManager cacheManager = CacheManager.getInstance();
		return cacheManager.getCurrentCacheSize(key);
	}

	public static List<String> getAllBaseCacheKey() {
		CacheManager cacheManager = CacheManager.getInstance();
		return cacheManager.getAllBaseCacheKey();
	}
	
}
