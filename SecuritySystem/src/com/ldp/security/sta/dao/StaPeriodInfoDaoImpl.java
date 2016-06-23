package com.ldp.security.sta.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.util.HibernateSessionHolder;

public class StaPeriodInfoDaoImpl implements StaPeriodInfoDao{

	public void deleteStaPeriodInfo(StaPeriodInfo staPeriodInfo) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(staPeriodInfo);
	}

	public void saveStaPeriodInfo(StaPeriodInfo staPeriodInfo) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(staPeriodInfo);
	}

	public StaPeriodInfo loadStaPeriodInfoById(long staPeriodInfoId) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		StaPeriodInfo staPeriodInfo = 
			(StaPeriodInfo) conn.get(StaPeriodInfo.class, staPeriodInfoId);
		return staPeriodInfo;
	}

}
