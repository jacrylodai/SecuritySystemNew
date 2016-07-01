package com.ldp.security.basedata.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.util.HibernateSessionHolder;

public class DataDictDaoImpl implements DataDictDao{

	public DataDict loadDataDictById(String dataDictId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		DataDict dataDict = (DataDict) conn.get(DataDict.class, dataDictId);
		return dataDict;
	}

}
