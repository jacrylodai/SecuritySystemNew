package com.ldp.security.basedata.dao;

import com.ldp.security.basedata.domain.Role;

public interface RoleDao {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public Role loadRoleById(long roleId);
	
	
}
