package com.ldp.security.common.manager;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ldp.security.util.HibernateSessionHolder;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.PageParamHolder;
import com.ldp.security.util.database.ParameterObject;

public abstract class AbstractManager<T> extends HibernateDaoSupport{

	/**
	 * 
	 * @param hql
	 * @param params
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	protected PageModel<T> findDataByHqlInPage(
			final String hql,final Object [] params
			,final int offset,final int pageSize){
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		List<T> data=(List<T>)ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		

		long totalCountLong=(Long)ht.find(getCountQuery(hql), params).get(0);
		PageModel<T> pageModel=new PageModel<T>();
		pageModel.setData(data);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalCount((int)totalCountLong);
		return pageModel;
	}
	
	/**
	 * 用变量名-变量值对hql进行设置，已根据相应的条件进行查找
	 * @param hql
	 * @param parameterObjectList 变量名-变量值 列表
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	protected PageModel<T> findDataByHqlParameterListInPage(
			final String hql,final List<ParameterObject> parameterObjectList
			,final int offset,final int pageSize){
		
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		List<T> data=(List<T>)ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				for(ParameterObject parameterObject:parameterObjectList){
					String parameterName = parameterObject.getParameterName();
					Object parameterValue = parameterObject.getParameterValue();
					if(parameterValue instanceof Collection){
						Collection valueList = (Collection) parameterValue;
						query.setParameterList(parameterName, valueList);
					}else{
						query.setParameter(parameterName, parameterValue);
					}
				}
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		
		long count=(Long)ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(getCountQuery(hql));
				for(ParameterObject parameterObject:parameterObjectList){
					String parameterName = parameterObject.getParameterName();
					Object parameterValue = parameterObject.getParameterValue();
					if(parameterValue instanceof Collection){
						Collection valueList = (Collection) parameterValue;
						query.setParameterList(parameterName, valueList);
					}else{
						query.setParameter(parameterName, parameterValue);
					}
				}
				return query.uniqueResult();
			}
		});

		PageModel<T> pageModel=new PageModel<T>();
		pageModel.setData(data);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalCount((int)count);
		return pageModel;
	}

	protected PageModel<T> findDataByHqlParameterListInPage(
			final String hql,final List<ParameterObject> parameterObjectList){

		int offset=PageParamHolder.getPageOffset();
		int pageSize=PageParamHolder.getPageSize();
		return findDataByHqlParameterListInPage(hql, parameterObjectList, offset, pageSize);
	}
	
	protected List<T> findDataByHqlParameterListInList(
			final String hql,final List<ParameterObject> parameterObjectList){
		
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		List<T> data=(List<T>)ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				for(ParameterObject parameterObject:parameterObjectList){
					String parameterName = parameterObject.getParameterName();
					Object parameterValue = parameterObject.getParameterValue();
					if(parameterValue instanceof Collection){
						Collection valueList = (Collection) parameterValue;
						query.setParameterList(parameterName, valueList);
					}else{
						query.setParameter(parameterName, parameterValue);
					}
				}
				return query.list();
			}
		});
		
		return data;
	}
	
	protected void executeUpdateOrDeleteByHqlParameterListInList(
			final String hql,final List<ParameterObject> parameterObjectList){
		
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query=session.createQuery(hql);
				for(ParameterObject parameterObject:parameterObjectList){
					String parameterName = parameterObject.getParameterName();
					Object parameterValue = parameterObject.getParameterValue();
					if(parameterValue instanceof Collection){
						Collection valueList = (Collection) parameterValue;
						query.setParameterList(parameterName, valueList);
					}else{
						query.setParameter(parameterName, parameterValue);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
		
	}
	
	protected List<T> findDataByHqlInList(final String hql,final Object[] params){
		
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		List<T> data=(List<T>)ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				return query.list();
			}
		});
		return data;
	}
	
	protected long findDataCountByHql(final String hql,final Object[] params){
		
		final String countHql = getCountQuery(hql);
		HibernateTemplate ht=(HibernateTemplate)HibernateSessionHolder.getConn();
		long count=(Long) ht.execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(countHql);
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				return query.uniqueResult();
			}
		});
		return count;
	}
	
	protected PageModel<T> findDataByHqlInPage(String hql,Object[] params){
		
		int offset=PageParamHolder.getPageOffset();
		int pageSize=PageParamHolder.getPageSize();
		return findDataByHqlInPage(hql,params,offset,pageSize);
	}

	protected PageModel<T> findDataByHqlInPage(String hql,Object obj){
		
		return findDataByHqlInPage(hql,new Object[]{obj});
	}
	
	protected PageModel<T> findDataByHqlInPage(String hql){
		
		return findDataByHqlInPage(hql,new Object[]{});
	}
	
	private String getCountQuery(String hql){
		int start=hql.indexOf("from");
		if(start==-1){
			throw new RuntimeException("非法的hql语句");
		}
		return "select count(*) "+hql.substring(start);
	}
}
