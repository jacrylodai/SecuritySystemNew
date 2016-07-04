package com.ldp.security.basedata.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.Role;
import com.ldp.security.util.HibernateSessionHolder;

public class RoleDaoImpl implements RoleDao{

	public Role loadRoleById(long roleId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		Role role = (Role) conn.get(Role.class, roleId);
		return role;
	}

	public void saveRole(Role role) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(role);
	}

	public void updateRole(Role role) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(role);
	}

	public void deleteRole(Role role) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.delete(role);
	}

}
