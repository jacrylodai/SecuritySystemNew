package com.ldp.security.report.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.report.domain.CommonForm;
import com.ldp.security.util.HibernateSessionHolder;

public class CommonFormDaoImpl implements CommonFormDao {

	public void deleteCommonForm(CommonForm commonForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(commonForm);
	}

	public void saveCommonForm(CommonForm commonForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(commonForm);
	}

	public void updateCommonForm(CommonForm commonForm) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(commonForm);
	}

}
