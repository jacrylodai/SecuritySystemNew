package com.ldp.security.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * hibernate session保存器
 * @author Administrator
 *
 */
public class HibernateSessionHolder {
	
	private static final Logger logger = Logger.getLogger(HibernateSessionHolder.class);

	private static ThreadLocal threadLocal;
	
	//嵌套级别保存map，一旦进入manager方法时，嵌套级别加1，初始为null
	//，第一次进入则当前级别为1
	//退出manager时嵌套级别减1
	private static ThreadLocal<Integer> levelLocal;
	
	static{
		threadLocal=new ThreadLocal();
		levelLocal = new ThreadLocal<Integer>();
	}
	
	public void beginTransaction(JoinPoint joinPoint){		
		
		Integer preLevel = levelLocal.get();
		if(preLevel == null){
			//进入最外层嵌套节点
			logger.warn("进入最外层嵌套节点，保存数据库连接");
			HibernateDaoSupport hds=(HibernateDaoSupport)joinPoint.getTarget();
			threadLocal.set(hds.getHibernateTemplate());
			
			preLevel = 0;
		}
		Integer currLevel = preLevel+1;
		levelLocal.set(currLevel);
		logger.warn("进入数据库切面方法，当前节点："+currLevel);
	}
	
	public static HibernateTemplate getConn(){
		HibernateTemplate conn=(HibernateTemplate) threadLocal.get();
		if(conn==null){
			throw new RuntimeException("no session is initialed");
		}
		return conn;
	}
	
	public void closeTransaction(){
		
		Integer currLevel = levelLocal.get();
		if(currLevel == null){
			throw new RuntimeException("没有设置嵌套级别");
		}
		
		logger.warn("退出数据库切面方法，当前节点："+currLevel);
		
		Integer nextLevel = currLevel-1;
		if(nextLevel == 0){
			//退出最外层嵌套节点
			logger.warn("退出最外层嵌套节点，移除数据库连接");
			threadLocal.remove();
			
			levelLocal.remove();
		}else{
			levelLocal.set(nextLevel);
		}
	}
}
