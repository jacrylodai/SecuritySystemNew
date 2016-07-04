package com.ldp.security.basedata.manager;

import java.util.List;
import java.util.Map;

import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.RoleResourceAuthority;

public interface RoleResourceAuthorityManager {

	public void saveRoleResourceAuthority(RoleResourceAuthority authority);
	
	public void updateRoleResourceAuthority(RoleResourceAuthority authority);

	/**
	 * 对权限进行添加或更新，如果无id就添加，有id就更新
	 * @param authorityList
	 */
	public void updateOrSaveAuthorityList(
			List<RoleResourceAuthority> authorityList);

	/**
	 * 取出当前角色所有的权限
	 * @param roleId
	 * @return
	 */
	public List<RoleResourceAuthority> getAuthorityListByResourceTypeRoleId(
			int resourceType,long roleId);

	/**
	 * 返回一个map,资源id-资源权限类，如果资源没有对应的权限，则建立一个全false的权限类
	 * @param resourceTree
	 * @param authorityList
	 * @return
	 */
	public Map<Long, RoleResourceAuthority> getAuthorityMapByResourceTreeAuthorityList(
			List<Resource> resourceTree,
			List<RoleResourceAuthority> authorityList);

	/**
	 * 根据条件，取得权限类
	 * @param roleId 角色id
	 * @param resourceId 资源id
	 * @return
	 */
	public RoleResourceAuthority getAuthorityByRoleIdResourceId(long roleId,
			long resourceId);

	public RoleResourceAuthority loadRoleResourceAuthorityById(
			long authorityId);

	/**
	 * 取得资源对应的所有权限，用于删除
	 * @param resourceId
	 * @return
	 */
	public List<RoleResourceAuthority> getAuthorityListByResourceId(
			long resourceId);

	public void deleteRoleResourceAuthority(RoleResourceAuthority authority);

	/**
	 * 取得角色对应的所有权限，用于删除
	 * @param roleId
	 * @return
	 */
	public List<RoleResourceAuthority> getAuthorityListByRoleId(long roleId);
	
}
