package com.ldp.security.report.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.util.HibernateSessionHolder;

public class SecurityFormDaoImpl implements SecurityFormDao {

	public void deleteSecurityForm(SecurityForm securityForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(securityForm);
	}

	public void saveSecurityForm(SecurityForm securityForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(securityForm);
	}

	public void updateSecurityForm(SecurityForm securityForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(securityForm);
	}

	public SecurityForm loadSecurityFormById(long securityFormId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		SecurityForm securityForm = (SecurityForm) conn.get(SecurityForm.class, securityFormId);
		return securityForm;
	}

}
