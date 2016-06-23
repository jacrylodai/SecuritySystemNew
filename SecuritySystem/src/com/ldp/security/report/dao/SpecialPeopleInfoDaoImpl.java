package com.ldp.security.report.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.report.domain.SpecialPeopleInfo;
import com.ldp.security.util.HibernateSessionHolder;

public class SpecialPeopleInfoDaoImpl implements SpecialPeopleInfoDao{

	public void deleteSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(specialPeopleInfo);
	}

	public SpecialPeopleInfo loadSpecialPeopleInfo(long specialPeopleInfoId) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		SpecialPeopleInfo specialPeopleInfo = 
			(SpecialPeopleInfo) conn.get(SpecialPeopleInfo.class, specialPeopleInfoId);
		
		return specialPeopleInfo;
	}

	public void saveSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(specialPeopleInfo);
	}

	public void updateSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(specialPeopleInfo);
	}

}
