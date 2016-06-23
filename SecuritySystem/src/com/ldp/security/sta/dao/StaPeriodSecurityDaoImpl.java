package com.ldp.security.sta.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.util.HibernateSessionHolder;

public class StaPeriodSecurityDaoImpl implements StaPeriodSecurityDao{

	public void delete(StaPeriodSecurity staPeriodSecurity) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(staPeriodSecurity);
	}

	public StaPeriodSecurity loadById(long staPeriodSecurityId) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		StaPeriodSecurity staPeriodSecurity = 
			(StaPeriodSecurity) conn.get(StaPeriodSecurity.class, staPeriodSecurityId);
		return staPeriodSecurity;
	}

	public void save(StaPeriodSecurity staPeriodSecurity) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(staPeriodSecurity);
	}

}
