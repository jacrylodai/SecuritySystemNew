package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.util.PageModel;

public interface RoleManager {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public Role loadRoleById(long roleId);

	public List<DataDict> getRoleTypeDataDictList();

	public DataDict getRoleTypeDataDictById(String roleTypeDataDictId);

	public PageModel<Role> listRoleInPage();

	public List<Role> getRoleListByRoleTypeId(String roleTypeId);
	
}
