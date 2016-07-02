package com.ldp.security.basedata.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.util.HibernateSessionHolder;

public class RoleResourceAuthorityDaoImpl implements RoleResourceAuthorityDao{

	public void saveRoleResourceAuthority(RoleResourceAuthority authority) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(authority);
	}

	public void updateRoleResourceAuthority(RoleResourceAuthority authority) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(authority);
	}

	public RoleResourceAuthority loadRoleResourceAuthorityById(long authorityId) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		RoleResourceAuthority authority = 
			(RoleResourceAuthority) conn.get(RoleResourceAuthority.class, authorityId);
		return authority;
	}

	public void deleteRoleResourceAuthority(RoleResourceAuthority authority) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(authority);
	}

}
