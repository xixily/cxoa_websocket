package com.chaoxing.oa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface BaseHetongDaoI<T> {

	public Serializable save(T o) throws Exception;

	public void delete(T o);

	public void update(T o) throws Exception;

	public void saveOrUpdate(T o);

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

	public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> params);
	
	public void prepareCall(String sql, Map<String, Object> params) throws HibernateException;

}
