package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.util.PageModel;

public interface RoleManager {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public Role loadRoleById(long roleId);

	/**
	 * 取得所有的角色类别
	 * @return
	 */
	public List<DataDict> getRoleTypeDataDictList();

	/**
	 * 根据条件，取得角色类别
	 * @param roleTypeDataDictId 角色类别id
	 * @return
	 */
	public DataDict getRoleTypeDataDictById(String roleTypeDataDictId);

	public PageModel<Role> listRoleInPage();

	/**
	 * 根据条件，取得角色列表
	 * @param roleTypeId 角色类别id
	 * @return
	 */
	public List<Role> getRoleListByRoleTypeId(String roleTypeId);

	/**
	 * 检查角色是否被用户引用，包含被删除的用户，如被引用，则无法删除
	 * @param roleId
	 * @return
	 */
	public boolean checkIsRoleUsedByUser(long roleId);

	public void deleteRole(Role role);
	
}
