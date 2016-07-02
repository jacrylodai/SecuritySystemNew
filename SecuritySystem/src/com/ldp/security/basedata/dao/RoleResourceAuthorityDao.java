package com.ldp.security.basedata.dao;

import com.ldp.security.basedata.domain.RoleResourceAuthority;

public interface RoleResourceAuthorityDao {

	public void saveRoleResourceAuthority(RoleResourceAuthority authority);
	
	public void updateRoleResourceAuthority(RoleResourceAuthority authority);

	public RoleResourceAuthority loadRoleResourceAuthorityById(long authorityId);

	public void deleteRoleResourceAuthority(RoleResourceAuthority authority);
	
}
