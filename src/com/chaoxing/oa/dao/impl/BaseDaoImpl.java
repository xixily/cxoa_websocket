package com.chaoxing.oa.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.chaoxing.oa.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

//	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public Serializable save(T o) throws HibernateException{
			try {
				return this.getCurrentSession().save(o);
			} catch (HibernateException e) {
				throw e;
			}
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T load(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().load(c, id);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		String qq = q.toString();
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) throws HibernateException{
		try {
			this.getCurrentSession().delete(o);
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public void update(T o) throws HibernateException {
		try {
			this.getCurrentSession().update(o);
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public void saveOrUpdate(T o) throws HibernateException {
		try {
			this.getCurrentSession().saveOrUpdate(o);
		} catch (HibernateException e) {
			throw e;
		}
		
	}

	@Override
	public List<T> find(String hql) throws HibernateException{
		Query q = this.getCurrentSession().createQuery(hql);
		
		try {
			return q.list();
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/**
	 * 分页查询
	 * @param hql 需要查询hql语句
	 * @param params 填充占位符条件
	 * @param page 当前页
	 * @param rows 每页的规格
	 */
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int pageSize) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.setFirstResult(0);
		q.setMaxResults(30);
		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<T> find(String hql, int page, int pageSize) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) throws HibernateException {
		Query q = this.getCurrentSession().createQuery(hql);
		try {
			return q.executeUpdate();
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) throws HibernateException {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		try {
			return q.executeUpdate();
		} catch (HibernateException e) {
			throw e;
		}
	}
	
	@Override
	public List<T> findSql(String sql){
		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
		return sq.list();
	}
	
	@Override
	public List<T> findSql(String sql, Map<String, Object> params){
		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				sq.setParameter(key, params.get(key));
			}
		}
		return sq.list();
	}
	
	
	@Override
	public int prepareCall(String sql, Map<String, Object> params) throws HibernateException{
		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				sq.setParameter(key, params.get(key));
			}
		}
		try {
			return sq.executeUpdate();
		} catch (HibernateException e) {
			throw e;
		}
	}
	
//	public void excuteHql(String sql, Map<String,Object> params){
//		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				sq.setParameter(key, params.get(key));
//			}
//		}
//		
//		sq.executeUpdate();
//	}
	
	@Override
	public void bigSave(List<T> objs){
		Session session = getCurrentSession();
		//获得spring aop 嵌入的事务
//		Transaction tx = session.getTransaction();
		for (int i = 0; i < objs.size(); i++) {
			T obj = objs.get(i);
			try {
				session.save(obj);
				if(i%100 == 0){
					session.flush();
					session.clear();
//					tx.commit();
//					tx = session.beginTransaction();
				}
			} catch (Exception e) {
//				tx.rollback();
			}
			
		}
		session.flush();
		session.clear();
//		tx.commit();
	}
	
	@Override
	public void bigUpdate(List<T> objs){
		Session session = getCurrentSession();
		//手动开启事务
//		Transaction tx = session.getTransaction();
		for (int i = 0; i < objs.size(); i++) {
			T obj = objs.get(i);
			try {
				session.update(obj);
				if(i%100 == 0){
					session.flush();
					session.clear();
//					tx.commit();
//					tx = session.beginTransaction();
				}
			} catch (Exception e) {
//				tx.rollback();
			}
		}
		session.flush();
		session.clear();
//		tx.commit();
	}
}
