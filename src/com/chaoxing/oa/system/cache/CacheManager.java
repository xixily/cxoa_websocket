package com.chaoxing.oa.system.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.log4j.Logger;

import com.chaoxing.oa.util.system.ResourceUtil;

public class CacheManager {
	private final static Logger logger = Logger.getLogger(CacheUtil.class);
	private volatile long updateTime = 0l;//缓存更新时间
	private volatile static CacheManager cacheManager;
//	@Autowired
//	private BaseDaoI<SystemConfig> systemConfigDao;//从系统配置库里面加载初始化信息
	private static String COMMON_CACHE = "common";
	private static Map<String, Map<String, Object>> cacheMap = new ConcurrentSkipListMap<String, Map<String, Object>>();// 缓存容器 
	
	/**
	 * 单例构造
	 */
	private CacheManager(){
		this.loadCache();
		this.updateTime = System.currentTimeMillis();
	}
	
	private static synchronized void cacheInit(){
		if(null == cacheManager){
			cacheManager = new CacheManager();
		}
	}
	
	/**
	 * 单例初始化
	 * @return CacheManager
	 */
	public static CacheManager getInstance() {
		if(null == cacheManager){
			cacheInit();
//			synchronized (CacheManager.class) {
//				if(null == cacheManager){
//					cacheManager = new CacheManager();
//				}
//			}
		}
		return cacheManager;
	}
	
	
	
	
	//TODO 做一些初始化预设缓存
	private void loadCache() {
//		this.updateFlag = true;//更新锁
//		this.updateFlag = false;//结束锁
	}
	
	/**
	 * 向缓存中放入数据对象
	 * 
	 * @param key  (例：user_session，默认common_)
	 * @param value
	 */
	public void put(String key, Object value) {
		String mapKey = CacheManager.COMMON_CACHE;
		String valueKey = null;
		if(key.contains("_")){
			String[] keys = key.split("_");
			mapKey = keys[0];
			valueKey = keys[1];
		}else{
			valueKey = key;
		}
		Map<String, Object> cc = cacheMap.get(mapKey);
		if(null == cc){
			cacheMap.put(mapKey, new ConcurrentSkipListMap<String, Object>());
		}
		cacheMap.get(mapKey).put(valueKey, value);
	}
	
	/**
	 * 根据key，清空缓存中相应的数据
	 * 
	 * @param key
	 */
	public void remove(String key) {
		String mapKey = CacheManager.COMMON_CACHE;
		String valueKey = null;
		if (key.contains("_")) {
			String[] keys = key.split("_");
			mapKey = keys[0];
			valueKey = keys[1];
		} else {
			valueKey = key;
		}
		Map<String, Object> cc = cacheMap.get(mapKey);
		if (null == cc) {
			return;
		}
		cacheMap.get(mapKey).remove(valueKey);
	}
	
	/**
	 * 根据mapkey，移除所有的改map下所有的数据
	 * 
	 * @param mapKey
	 */
	public void removeAll(String mapKey) {
		Map<String, Object> cc = cacheMap.get(mapKey);
		if (null == cc) {
			return;
		}
		cacheMap.get(mapKey).clear();
	}
	
	/**
	 * 清空系统cache下所有的数据对象
	 */
	public void removeAll() {
		cacheMap.clear();
	}
	
	/**
	 * 获取 map值
	 * @return
	 */
	public Object get(String key) {
		String mapKey = CacheManager.COMMON_CACHE;
		String valueKey = null;
		Object resultObject = null;
		if (key.contains("_")) {
			String[] keys = key.split("_");
			mapKey = keys[0];
			valueKey = keys[1];
		} else {
			valueKey = key;
		}
		Map<String, Object> cc = cacheMap.get(mapKey);
		if (cc == null) {
			resultObject = null;
		}else {
			resultObject = cc.get(valueKey);
		}
		return resultObject;
	}
	
	/**
	 * 获取字典（系统预设的值）,如果值为空，则重置为预设
	 * @return
	 */
	public Object getDic(String key) {
		String mapKey = CacheManager.COMMON_CACHE;
		String valueKey = null;
		Object resultObject = null;
		if (key.contains("_")) {
			String[] keys = key.split("_");
			mapKey = keys[0];
			valueKey = keys[1];
		} else {
			valueKey = key;
		}
		Map<String, Object> cc = cacheMap.get(mapKey);
		if (cc == null) {
			resultObject = null;
		}else {
			resultObject = cc.get(valueKey);
		}
		if(null == resultObject){
			return loadInCache(valueKey);
		}
		return resultObject;
	}
	
	/**
	 * 得到系统中所有的基础缓存实例的key
	 * 
	 * @return
	 */
	public List<String> getAllBaseCacheKey() {
		Iterator<String> it = cacheMap.keySet().iterator();
		List<String> keyList = new ArrayList<String>();
		while (it.hasNext()) {
			keyList.add(it.next());
		}
		return keyList;
	}
	
	/**
	 * 获取指定的基础缓存对象的大小
	 * 
	 * @param key
	 * @return
	 */
	public int getCurrentCacheSize(String key) {
		Map<String,Object> cc = cacheMap.get(key);
		if (cc != null) {
			return cc.size();
		} else {
			logger.error("在系统中未找到基础缓存实例：[" + key + "]");
			return 0;
		}
	}
	
	/**
	 * 得到系统中所有的对象缓存大小
	 * 
	 * @return
	 */
	public int getCurrentCacheSize() {
		Iterator<String> it = cacheMap.keySet().iterator();
		int size = 0;
		while(it.hasNext()){
			String key = it.next();
			size += cacheMap.get(key).size();
		}
		return size;
	}
	
	/**
	 * 得到系统中所有的缓存实例
	 * 
	 * @return
	 */
	public List<Map<String,Object>> getAllBaseCache() {
		Iterator<String> it = cacheMap.keySet().iterator();
		List<Map<String,Object>> cacheList = new ArrayList<Map<String,Object>>();
		while (it.hasNext()) {
			Map<String,Object> cc = cacheMap.get(it.next());
			cacheList.add(cc);
		}
		return cacheList;
	}
	
	/**
	 * 在配置文件中加载预配值
	 * @param valueKey
	 * @return
	 */
	private Object loadInCache(String valueKey) {
//		SystemConfig sysConfig = systemConfigDao.get(SystemConfig.class, valueKey);
		Object value = ResourceUtil.getKey(valueKey);
		if(null!=value){
			cacheMap.get(CacheManager.COMMON_CACHE).put(valueKey, value);
		}
		return value;
	}

	/** 
	 * 获取缓存项大小 
	 * @return 
	 */  
	public int getCacheSize() {  
		return cacheMap.size();  
	}  
	
	  /** 
     * 获取更新时间 
     * @return 
     */  
	public long getUpdateTime() {  
        return this.updateTime;  
    }  

}
