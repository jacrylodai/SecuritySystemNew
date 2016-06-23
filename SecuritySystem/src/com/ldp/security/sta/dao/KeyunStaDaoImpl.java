package com.ldp.security.sta.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.sta.domain.KeyunSta;
import com.ldp.security.util.HibernateSessionHolder;

public class KeyunStaDaoImpl implements KeyunStaDao{

	public void delete(KeyunSta keyunSta) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(keyunSta);
	}

	public void save(KeyunSta keyunSta) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(keyunSta);
	}

}
