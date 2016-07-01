package com.ldp.security.basedata.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.util.HibernateSessionHolder;

public class ResourceDaoImpl implements ResourceDao{

	public Resource loadResourceById(long resourceId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		Resource resource = (Resource) conn.load(Resource.class, resourceId);
		return resource;
	}

	public void saveResource(Resource resource) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(resource);
	}

	public void updateResource(Resource resource) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(resource);
	}

}
