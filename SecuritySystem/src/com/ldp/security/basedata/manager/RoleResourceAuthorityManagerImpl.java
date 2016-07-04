package com.ldp.security.basedata.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ldp.security.basedata.dao.RoleResourceAuthorityDao;
import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.util.database.ParameterObject;

public class RoleResourceAuthorityManagerImpl
	extends AbstractManager<RoleResourceAuthority>
	implements RoleResourceAuthorityManager{

	private RoleResourceAuthorityDao roleResourceAuthorityDao;
	
	public void setRoleResourceAuthorityDao(
			RoleResourceAuthorityDao roleResourceAuthorityDao) {
		this.roleResourceAuthorityDao = roleResourceAuthorityDao;
	}

	public void saveRoleResourceAuthority(RoleResourceAuthority authority) {

		roleResourceAuthorityDao.saveRoleResourceAuthority(authority);
	}

	public void updateRoleResourceAuthority(RoleResourceAuthority authority) {

		roleResourceAuthorityDao.updateRoleResourceAuthority(authority);
	}

	public void updateOrSaveAuthorityList(
			List<RoleResourceAuthority> authorityList) {

		for(RoleResourceAuthority authority:authorityList){
			if(authority.getRoleResourceAuthorityId() == 0){
				roleResourceAuthorityDao.saveRoleResourceAuthority(authority);
			}else{
				roleResourceAuthorityDao.updateRoleResourceAuthority(authority);
			}
		}
	}

	public List<RoleResourceAuthority> getAuthorityListByResourceTypeRoleId(
			int resourceType,long roleId) {

		String hql = 
			"from RoleResourceAuthority authority" +
			" where authority.resource.resourceType=:resourceType" +
			" and authority.role.roleId=:roleId";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("resourceType",resourceType));
		paraObjList.add(new ParameterObject("roleId",roleId));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public Map<Long, RoleResourceAuthority> getAuthorityMapByResourceTreeAuthorityList(
			List<Resource> resourceTree,
			List<RoleResourceAuthority> authorityList) {

		Map<Long, RoleResourceAuthority> authorityMap = 
			new HashMap<Long, RoleResourceAuthority>();
		for(RoleResourceAuthority authority:authorityList){
			long resourceId = authority.getResource().getResourceId();
			authorityMap.put(resourceId, authority);
		}
		
		//检查authorityMap是否包含所有的资源权限类，
		//如果相应的资源没有权限类，则新建一个空的权限类，
		//这是为了方便页面的展示，页面上就不用判断资源权限类是否为空值
		for(Resource topResource:resourceTree){
			long topResourceId = topResource.getResourceId();
			if(!authorityMap.containsKey(topResourceId)){
				authorityMap.put(topResourceId, new RoleResourceAuthority());
			}
			
			List<Resource> subResourceList = topResource.getSubResourceList();
			for(Resource subResource:subResourceList){
				long subResourceId = subResource.getResourceId();
				if(!authorityMap.containsKey(subResourceId)){
					authorityMap.put(subResourceId, new RoleResourceAuthority());
				}
			}
		}
		
		return authorityMap;
	}

	public RoleResourceAuthority getAuthorityByRoleIdResourceId(long roleId,
			long resourceId) {

		String hql = 
			"from RoleResourceAuthority authority" +
			" where authority.role.roleId=:roleId" +
			" and authority.resource.resourceId=:resourceId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("roleId",roleId));
		paraObjList.add(new ParameterObject("resourceId",resourceId));
		
		List<RoleResourceAuthority> authorityList = 
			findDataByHqlParameterListInList(hql, paraObjList);
		
		if(authorityList.size()>1){
			throw new RuntimeException("指定角色，资源下，系统中存在重复的权限内容");
		}
		if(authorityList.size() == 1){
			return authorityList.get(0);
		}else{
			return null;
		}
	}

	public RoleResourceAuthority loadRoleResourceAuthorityById(long authorityId) {

		return roleResourceAuthorityDao.loadRoleResourceAuthorityById(authorityId);
	}

	public void deleteRoleResourceAuthority(RoleResourceAuthority authority) {

		roleResourceAuthorityDao.deleteRoleResourceAuthority(authority);
	}

	public List<RoleResourceAuthority> getAuthorityListByResourceId(
			long resourceId) {
		
		String hql = 
			"from RoleResourceAuthority authority" +
			" where authority.resource.resourceId=:resourceId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("resourceId",resourceId));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public List<RoleResourceAuthority> getAuthorityListByRoleId(long roleId) {

		String hql = 
			"from RoleResourceAuthority authority" +
			" where authority.role.roleId=:roleId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("roleId",roleId));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

}
