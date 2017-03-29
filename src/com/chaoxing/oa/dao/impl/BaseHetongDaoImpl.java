//package com.chaoxing.oa.dao.impl;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Repository;
//
//import com.chaoxing.oa.dao.BaseHetongDaoI;
//
//@Repository("baseHetongDao")
//public class BaseHetongDaoImpl<T> implements BaseHetongDaoI<T> {
//	@Resource(name="SQLsessionFactory")
//	private SessionFactory SQLsessionFactory;
//	public SessionFactory getSessionFactory() {
//		return SQLsessionFactory;
//	}
//	
////	@Autowired
////	public void setSessionFactory(SessionFactory SQLsessionFactory) {
////		this.SQLsessionFactory = SQLsessionFactory;
////	}
////
//	private Session getCurrentSession() {
//		Session session = this.SQLsessionFactory.getCurrentSession();
//		clearSession(session);
//		return session;
////		return this.SQLsessionFactory.getCurrentSession();
//	}
//
//	public SessionFactory getSQLsessionFactory() {
//		return SQLsessionFactory;
//	}
//
//
////	@Autowired()
//	public void setSQLsessionFactory(SessionFactory sQLsessionFactory) {
//		SQLsessionFactory = sQLsessionFactory;
//	}
//
//
//
//	@Override
//	public Serializable save(T o) throws HibernateException{
//			try {
//				
//				return this.getCurrentSession().save(o);
//				
//			} catch (HibernateException e) {
//				throw e;
//			}
//	}
//
//	@Override
//	public T get(Class<T> c, Serializable id) {
//		return (T) this.getCurrentSession().get(c, id);
//	}
//
//	@Override
//	public T load(Class<T> c, Serializable id) {
//		return (T) this.getCurrentSession().load(c, id);
//	}
//
//	@Override
//	public T get(String hql) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		List<T> l = q.list();
//		if (l != null && l.size() > 0) {
//			return l.get(0);
//		}
//		return null;
//	}
//
//	@Override
//	public T get(String hql, Map<String, Object> params) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//		}
//		String qq = q.toString();
//		List<T> l = q.list();
//		if (l != null && l.size() > 0) {
//			return l.get(0);
//		}
//		return null;
//	}
//
//	@Override
//	public void delete(T o) throws HibernateException{
//		try {
//			this.getCurrentSession().delete(o);
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//
//	@Override
//	public void update(T o) throws HibernateException {
//		try {
//			this.getCurrentSession().update(o);
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//
//	@Override
//	public void saveOrUpdate(T o) throws HibernateException {
//		try {
//			this.getCurrentSession().saveOrUpdate(o);
//		} catch (HibernateException e) {
//			throw e;
//		}
//		
//	}
//
//	@Override
//	public List<T> find(String hql) throws HibernateException{
//		Session session = this.getCurrentSession();
//		Query q = this.getCurrentSession().createQuery(hql);
//		
//		try {
//			return q.list();
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//
//	@Override
//	public List<T> find(String hql, Map<String, Object> params) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//			
//		}
//		return q.list();
//	}
//	
//	/**
//	 * 分页查询
//	 * @param hql 需要查询hql语句
//	 * @param params 填充占位符条件
//	 * @param page 当前页
//	 * @param rows 每页的规格
//	 */
//	@Override
//	public List<T> find(String hql, Map<String, Object> params, int page, int pageSize) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//		}
//		q.setFirstResult(0);
//		q.setMaxResults(30);
//		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
//	}
//
//	@Override
//	public List<T> find(String hql, int page, int pageSize) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
//	}
//
//	@Override
//	public Long count(String hql) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		return (Long) q.uniqueResult();
//	}
//
//	@Override
//	public Long count(String hql, Map<String, Object> params) {
//		Query q = this.getCurrentSession().createQuery(hql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//		}
//		return (Long) q.uniqueResult();
//	}
//
//	@Override
//	public int executeHql(String hql) throws HibernateException {
//		Query q = this.getCurrentSession().createQuery(hql);
//		try {
//			return q.executeUpdate();
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//
//	@Override
//	public int executeHql(String hql, Map<String, Object> params) throws HibernateException {
//		Query q = this.getCurrentSession().createQuery(hql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//		}
//		try {
//			return q.executeUpdate();
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//	
//	public List<T> findSql(String sql){
//		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
//		return sq.list();
//	}
//	
//	public void prepareCall(String sql, Map<String, Object> params) throws HibernateException{
//		Session session = this.getCurrentSession();
//		
//		SQLQuery sq = this.getCurrentSession().createSQLQuery(sql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				sq.setParameter(key, params.get(key));
//			}
//		}
//		try {
//			sq.executeUpdate();
//		} catch (HibernateException e) {
//			throw e;
//		}
//	}
//	
//	private void clearSession(Session session){
//		if(null != session){
//			session.flush();
//			session.clear();
//		}
//	}
//}
