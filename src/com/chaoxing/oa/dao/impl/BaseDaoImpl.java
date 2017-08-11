package com.chaoxing.oa.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.util.system.SqlHelper;

@Repository("baseDao")
@SuppressWarnings("unchecked")
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
			} catch(Exception e2){
				throw e2;
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
	public int executeSql(String sql) throws HibernateException {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		try {
			return q.executeUpdate();
		} catch (HibernateException e) {
			throw e;
		}
	}
	
	@Override
	public int executeSql(String sql, Map<String, Object> params) throws HibernateException {
		Query q = this.getCurrentSession().createSQLQuery(sql);
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
	public int bigSave(List<T> objs){
		Session session = getCurrentSession();
		int total = 0;
		//获得spring aop 嵌入的事务
//		Transaction tx = session.getTransaction();
		for (int i = 0; i < objs.size(); i++) {
			T obj = objs.get(i);
			try {
				session.save(obj);
				total ++;
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
		return total;
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
	
	
	@Override
	 public List<T> queryResultList(Class<T> className, Map<String,Object> varables,int page,int pageSize){        
	        Session session = sessionFactory.getCurrentSession();
	        List<T> valueList = selectStatement(className, varables, session,page,pageSize).list();
	        return valueList;       
	    }
	
	@Override
	 public Integer queryResultList(Class<T> className, Map<String,Object> varables){        
	        Session session = sessionFactory.getCurrentSession();
	        Long count = (Long)selectStatement(className, varables, session).iterate().next();
	        return count.intValue();       
	    }
	
	@Override
	public Query selectStatement(Class<T> className, Map<String,Object> varables, Session session,int page,int pageSize) {
	        StringBuilder stringBuilder = new StringBuilder();
	        /*
	         * 通过className得到该实体类的字符串形式,
	         */
	        stringBuilder.append("from " + sessionFactory.getClassMetadata(className).getEntityName());
	        stringBuilder.append(" where dealConditon!=4 ");
	        /*
	         * 动态的拼接sql语句,如果一个属性的值为"", 则不往条件中添加.
	         */
	        for(Entry<String, Object> entry : varables.entrySet()){
	            if(!entry.getValue().equals("") && !entry.getKey().equals("cid")){
	                stringBuilder.append(" and " + entry.getKey()+" like :" + entry.getKey());
	            }else if(!entry.getValue().equals("") && entry.getKey().equals("cid")){
	            	stringBuilder.append(" and " + entry.getKey()+" = :" + entry.getKey());
	            }
	        } 
	        stringBuilder.append(" order by id desc ");
	        Query query = session.createQuery(stringBuilder.toString());//查询语句
	        /*
	         * 动态的给条件赋值
	         */
	        for(Entry<String, Object> entry : varables.entrySet()){
	            if(!entry.getValue().equals("")){
	                query.setParameter(entry.getKey(), entry.getValue());
	            }
	        }
	        query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
	        return query;
	    }
	
	@Override
	public Query selectStatement(Class<T> className, Map<String,Object> varables, Session session) {
	        StringBuilder stringBuilder = new StringBuilder();
	        /*
	         * 通过className得到该实体类的字符串形式,
	         */
	        stringBuilder.append("select count(*) from " + sessionFactory.getClassMetadata(className).getEntityName());
			stringBuilder.append(" where dealConditon!=4 ");
	        /*
	         * 动态的拼接sql语句,如果一个属性的值为"", 则不往条件中添加.
	         */
	        for(Entry<String, Object> entry : varables.entrySet()){
	            if(!entry.getValue().equals("") && !entry.getKey().equals("cid")){
	                stringBuilder.append(" and " + entry.getKey()+" like :" + entry.getKey());
	       }else if(!entry.getValue().equals("") && entry.getKey().equals("cid")){
	            	stringBuilder.append(" and " + entry.getKey()+" = :" + entry.getKey());
	            }
	        } 
	   //     stringBuilder.append(" order by id desc ");
	        Query query = session.createQuery(stringBuilder.toString());
	        /*
	         * 动态的给条件赋值
	         */
	        for(Entry<String, Object> entry : varables.entrySet()){
	            if(!entry.getValue().equals("")){
	                query.setParameter(entry.getKey(), entry.getValue());
	            }
	        }
	        return query;
	    }
	
	//service 层和dao层做更进一步的分离
	
	@Override
	public List<T> find(T valueObj, int offset, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = SqlHelper.prepareQuerySql(valueObj,params);
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (List<T>)q.setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public long count(T valueObj) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = SqlHelper.prepareQuerySql(valueObj,params);
		hql = "select count(*)" + hql;
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (long) q.uniqueResult();
	}
}
