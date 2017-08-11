package com.chaoxing.oa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public interface BaseDaoI<T> {

	public Serializable save(T o) throws Exception;

	public void delete(T o) throws HibernateException;
	
	public void update(T o) throws Exception;

	public void saveOrUpdate(T o) throws HibernateException;

	public T get(Class<T> c, Serializable id);

	public T load(Class<T> c, Serializable id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql) throws HibernateException;
	
	public int executeSql(String sql) throws HibernateException;

	public int executeHql(String hql, Map<String, Object> params) throws HibernateException;
	
	public int executeSql(String sql, Map<String, Object> params) throws HibernateException;
	
	public List<T> findSql(String sql) throws HibernateException;
	
	public List<T> findSql(String sql, Map<String, Object> params) throws HibernateException;
	
	public int prepareCall(String sql, Map<String, Object> params) throws HibernateException;
	
	public int bigSave(List<T> objs);
	
	public void bigUpdate(List<T> objs);
	
	public List<T> queryResultList(Class<T> className, Map<String,Object> varables,int page,int pageSize);
	
	public Query selectStatement(Class<T> className, Map<String,Object> varables, Session session,int page,int pageSize);
	
	public Integer queryResultList(Class<T> className, Map<String,Object> varables);
	
	public Query selectStatement(Class<T> className, Map<String,Object> varables, Session session);

	public List<T> find(T valueObj, int offset, int pageSize);

	public long count(T valueObj);
	
	
}
