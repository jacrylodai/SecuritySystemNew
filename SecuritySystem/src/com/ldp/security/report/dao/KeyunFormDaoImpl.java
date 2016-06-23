package com.ldp.security.report.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.report.domain.KeyunForm;
import com.ldp.security.util.HibernateSessionHolder;

public class KeyunFormDaoImpl implements KeyunFormDao {

	public void deleteKeyunForm(KeyunForm keyunForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(keyunForm);
	}

	public void saveKeyunForm(KeyunForm keyunForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(keyunForm);
	}

	public void updateKeyunForm(KeyunForm keyunForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(keyunForm);
	}

}
