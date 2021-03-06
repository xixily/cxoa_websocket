package com.chaoxing.oa.util.system;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chaoxing.oa.entity.po.caiwu.Baoxiao;

/**
 * 
 * @author dengxf
 *
 */
public class SqlHelper {
	
	public final static Logger logger = Logger.getLogger(SqlHelper.class);
	
	/**
	 * 预设hql and语句
	 * @param obj 查询数据对象
	 * @param params 
	 * @param isLike "like" 还是 "="
	 * @param expect 数组，被排除的字段
	 * @see  1=1 and id=:id;
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static String prepareAndSql(Object obj, Map<String, Object> params, boolean isLike, String... expect) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return prepareAndSql(obj,params,"t",isLike,true,expect);
	}
	
	public static String prepareOrSql(Object obj, Map<String, Object> params, boolean isLike, String... expect) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return prepareAndSql(obj,params,"t",isLike,false,expect);
	}
	
	public static String prepareAndSql(Object obj, Map<String, Object> params, String tableName, boolean isLike, boolean isAnd,String... expect){
		Class<?> clss = obj.getClass();
		Field[] fs = clss.getDeclaredFields();
		StringBuffer sql = new StringBuffer(" 1=1 ");
		String concat = isAnd ? "and " : "or ";
		Method method = null;
		Object value = null;
		String type = null;
		String name = null;
		String upperName = null;
		int flag = 0;
		for (Field field : fs) {
			type = field.getType().getSimpleName();
			name = field.getName();
			upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
			try {
				method = clss.getMethod("get" + upperName);
				value = method.invoke(obj);
			} catch (NoSuchMethodException e1) {
				System.out.println("没有[get" + upperName + "]方法,属性[" + name + "]不拼接");
				continue;
			} catch (IllegalAccessException e) {
				System.out.println(e.toString());
				continue;
//				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
				continue;
			} catch (InvocationTargetException e) {
				System.out.println(e.toString());
				continue;
			}
			if(value == null){
				continue;
			}
			if(expect.length>0){
				for (String e : expect) {
					if(e.equals(name)){
						flag += 1;
						break;
					}
				}
				if(flag>0){
					flag = 0;
					continue;
				}
			}
			if("String".equals(type)){
				if(!isLike){
					sql.append(concat + tableName + "." + name + "=:" + name +" ");
					params.put(name, String.valueOf(value));
				}else{
					sql.append(concat + tableName + "." + name + " like :" + name + " ");
					params.put(name, "%" + value + "%");
				}
			}else{
				setParams(type,name,sql,value,params,tableName);
			}
//			if(("float").equalsIgnoreCase(type)||("int").equals(type)||("Integer").equals(type)||("double").equalsIgnoreCase(type)||("long").equalsIgnoreCase(type)
//					||("boolean").equals(type)||("byte").equals(type)||("char").equals(type)||("String").equals(type)){
//				if(!isLike){
//					sql.append("and " + tableName + "." + name + "=:" + name +" ");
//					params.put(name, String.valueOf(value));
//				}else{
//					sql.append("and " + tableName + "." + name + " like :" + name + " ");
//					params.put(name, "%" + value + "%");
//				}
//			}else{
//				continue;
//			}
		}
//		System.out.println("return hql:" + sql.toString());
		logger.info("[SqlHelper.prepareAndSql] : return hql:" + sql.toString());
		return sql.toString();
	}
	
	public static void setParams(String type, String name,StringBuffer sql, Object value, Map<String,Object> params,String tableName){
		if("float".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Float) value);
		}else if("int".equals(type) || "Integer".equals(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Integer) value);
		}else if("double".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Double) value);
		}else if("long".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Long) value);
		}else if("byte".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Byte) value);
		}else if("short".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Short) value);
		}else if("boolean".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Boolean) value);
		}else if("BigDecimal".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(BigDecimal) value);
		}else if("Date".equalsIgnoreCase(type)){
//			sql.append("and " + tableName + ".str(" + name + ") like :" + name +" ");
//			params.put(name, "%" + value + "%");
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Date) value);
		}else{
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Object) value);
		}
	}
	
	public static String prepareSetSql(Object obj, Map<String, Object> params){
		return prepareSetSql(obj, params, null);
	}
	
	/**
	 * 
	 * @param obj
	 * @param params
	 * @param tableName
	 * @param expect
	 * @return
	 */
	public static String prepareSetSql(Object obj, Map<String, Object> params, String tableName, String... expect){
		Class<?> clss = obj.getClass();
		tableName = null != tableName ? tableName : "t";
		Field[] fs = clss.getDeclaredFields();
		StringBuffer sql = new StringBuffer(" ");
		String where = null;
		Method method = null;
		Object value = null;
		String type = null;
		String name = null;
		String upperName = null;
		int flag;
		int idFlag = 0;
		for (Field field : fs) {
			flag = 0;
			idFlag = 0;
			type = field.getType().getSimpleName();
			name = field.getName();
			//* 手动剔除不需要更新的
			if(expect.length>0){
				for (String e : expect) {
					if(e.equals(name)){
						flag += 1;
						break;
					}
				}
				if(flag>0){
					flag = 0;
					continue;
				}
			}
			//值为null或者@Column(updatable = false) 剔除
			upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
			try {
				method = clss.getMethod("get" + upperName);
				if(null != method.getAnnotation(javax.persistence.Column.class)){
					if(!method.getAnnotation(javax.persistence.Column.class).updatable()){
						continue;//不允许更新
					}
				}
				if(null != method.getAnnotation(javax.persistence.Id.class)){
					idFlag +=1;
				}
				value = method.invoke(obj);
			} catch (NoSuchMethodException e1) {
				System.out.println("没有[get" + upperName + "]方法,属性[" + name + "]不拼接");
				continue;
			} catch (IllegalAccessException e) {
				System.out.println(e.toString());
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
				continue;
			} catch (InvocationTargetException e) {
				System.out.println(e.toString());
				continue;
			}
			if(value == null){
				continue;
			}
			if(idFlag == 1){
				where = "where " + tableName+ "." + name + "=:" + name;
				params.put(name, value);
				continue;
			}
			setSetParams(type,name,sql,value,params,tableName);
		}
		if(null != where){
			return sql.toString().substring(0,sql.length()-1) + where;
		}else{
			return sql.toString().substring(0,sql.length()-1);
		}
	}

	private static void setSetParams(String type, String name, StringBuffer sql, Object value,
			Map<String, Object> params, String tableName) {
		sql.append(tableName + "." + name + "=:" + name + " ,");
		if("String".equals(type)){
			params.put(name, (String) value);
		}else if("float".equalsIgnoreCase(type)){
			params.put(name,(Float) value);
		}else if("int".equals(type) || "Integer".equals(type)){
			params.put(name,(Integer) value);
		}else if("double".equalsIgnoreCase(type)){
			params.put(name,(Double) value);
		}else if("long".equalsIgnoreCase(type)){
			params.put(name,(Long) value);
		}else if("byte".equalsIgnoreCase(type)){
			params.put(name,(Byte) value);
		}else if("short".equalsIgnoreCase(type)){
			params.put(name,(Short) value);
		}else if("boolean".equalsIgnoreCase(type)){
			params.put(name,(Boolean) value);
		}else if("BigDecimal".equalsIgnoreCase(type)){
			params.put(name,(BigDecimal) value);
		}else if("Date".equalsIgnoreCase(type)){
			params.put(name,(Date) value);
		}else{
			params.put(name,(Object) value);
		}
		
	}
	
	
	
	// 重新写的sqlhelper，精化了之前几个
	
	public static String prepareQuerySql(Object obj, Map<String, Object> params, String... except) {
		return prepareQuerySql("", obj, params, except);
	}
	
	/**
	 * <strong style="red">注意：</strong>被修饰为<strong>public</strong>和<strong>static</strong>的属性不会做条件拼接，<br/>
	 * 集合类型<strong>Collection</strong>和<strong>Map</strong>类型属性也不会拼接。
	 * @param alias 表别名
	 * @param obj 必须
	 * @param params 必须
	 * @param except 排除属性的属性名
	 * @return String hql
	 */
	public static String prepareQuerySql(String alias, Object obj, Map<String, Object> params, String... except) {
		alias = (null == alias || alias.equals("")) ? " " : " " + alias + ".";
		if(null != obj && null != params){
			Class<? extends Object> clazz = obj.getClass();
			String clsName = clazz.getSimpleName();
			Field[] fs = clazz.getDeclaredFields();
			StringBuffer sb = new StringBuffer("from " + clsName + " " + alias +"where 1=1");
			int mod = 0;//修饰符
			Object value = null;
			String type = null;
			String name = null;
			boolean ept = true;
			boolean empty = true;
			for (Field field : fs) {
				ept = true;
				mod = field.getModifiers();
				name = field.getName();
				type = field.getType().getSimpleName();
				for (String expt : except) {
					if(expt.equals(name)) ept = false; 
				}
				if((mod & 8) ==0 && (mod & 16) == 0 && ept){//修饰符为static 或者是 final 的属性做处理
					field.setAccessible(true);
					try {
						value = field.get(obj);
						if(empty && null != value) empty = false;
						if(null != value && !(value instanceof Map) && !(value instanceof Collection)){
							if("String".equals(type)){
								sb.append(" and" + alias + name + " like :" + name);
								value = "%" + value + "%";
							}else if("Date".equals(type)){
								sb.append(" and" + alias + name + ">= :" + name);
							}else{
								sb.append(" and" + alias + name + "=:" + name);
							}
							params.put(name, value);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			return (empty ? sb.toString() : sb.toString());
		}
		return "";
	}
	
	public static String prepareUpdateSql(Object obj, Map<String, Object> params,String identity,String... except){
		return prepareUpdateSql("", obj, params, identity, except);
	}
	
	/**
	 * <strong style="red">注意：</strong>被修饰为<strong>public</strong>和<strong>static</strong>的属性不会做条件拼接，<br/>
	 * 集合类型<strong>Collection</strong>和<strong>Map</strong>类型属性也不会拼接。
	 * @param alias 表别名
	 * @param obj 必须
	 * @param params 必须
	 * @param identity 必须，表的id字段
	 * @param except 排除属性的属性名
	 * @return String hql
	 */
	public static String prepareUpdateSql(String alias,Object obj, Map<String, Object> params,String identity, String... except){
		if(null != obj && null != params && null != identity){
			Class<? extends Object> clazz = obj.getClass();
			String clsName = clazz.getSimpleName();
			Field[] fs = clazz.getDeclaredFields();
			StringBuffer sb = new StringBuffer("update " + clsName + " " + alias + " set");
			alias = (null == alias || alias.equals("")) ? " " : " " + alias + ".";
			int mod = 0;//修饰符
			Object value = null;
			String name = null;
			boolean ept = true;
			boolean empty = true;
			for (Field field : fs) {
				ept = true;
				mod = field.getModifiers();
				name = field.getName();
				for (String expt : except) {
					if(expt.equals(name)) ept = false; 
				}
//				if(name.equals(identity)){
//					params.put(identity, value);
//					ept = false;
//					name=null;
//				}
				if((mod & 8) ==0 && (mod & 16) == 0 && ept){//修饰符为static 或者是 final 的属性做处理
					field.setAccessible(true);
					try {
						value = field.get(obj);
						if(name.equals(identity)){
							params.put(identity, value);
							ept = false;
							name=null;
						}else{
							if(empty && null != value) empty = false;
							if(null != value && !(value instanceof Map) && !(value instanceof Collection)){
								sb.append(alias + name + "=:" + name + ",");
								params.put(name, value);
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			return (empty ? "" : sb.substring(0, sb.length()-1) + " where" + alias + identity + "=:" + identity);
		}
		return "";
	}
	
	public static void main(String[] args) {
		Baoxiao bx = new Baoxiao();
		bx.setId(12l);
		bx.setAccount("123456");
		bx.setBank("交通银行");
		bx.setApproid(31);
		bx.setBaoxMoney(546f);
		bx.setMoney(666f);
		bx.setJtime(new Date());
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = prepareSetSql(bx,params,null);
		System.out.println(sql);
	}
}
