package com.ldp.security.sta.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.sta.domain.CommonSta;
import com.ldp.security.util.HibernateSessionHolder;

public class CommonStaDaoImpl implements CommonStaDao {

	public void delete(CommonSta commonSta) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(commonSta);
	}

	public void save(CommonSta commonSta) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(commonSta);
	}

}
