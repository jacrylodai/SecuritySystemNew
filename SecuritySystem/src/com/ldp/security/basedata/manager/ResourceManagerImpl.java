package com.ldp.security.basedata.manager;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.basedata.dao.ResourceDao;
import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.database.ParameterObject;

public class ResourceManagerImpl extends AbstractManager<Resource>
	implements ResourceManager{
	
	private ResourceDao resourceDao;

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public Resource loadResourceById(long resourceId) {
		return resourceDao.loadResourceById(resourceId);
	}

	public void saveResource(Resource resource) {

		resourceDao.saveResource(resource);
	}

	public void updateResource(Resource resource) {

		resourceDao.updateResource(resource);
	}

	public List<Resource> getSubResourceListById(long resourceId) {
		
		String hql = 
			"from Resource resource" +
			" where resource.parentResource.resourceId=:resourceId" +
			" order by resource.orderNumber asc";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("resourceId",resourceId));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public List<Resource> getAllMenuResourceTreeInList() {

		List<Resource> resourceTree = getSubResourceListById(Resource.ROOT_MENU_RESOURCE_ID);
		for(Resource resource:resourceTree){
			List<Resource> subResourceList = getSubResourceListById(resource.getResourceId());
			resource.setSubResourceList(subResourceList);
		}
		return resourceTree;
	}

	public List<Resource> getAllActionResourceTreeInList() {

		List<Resource> resourceTree = getSubResourceListById(Resource.ROOT_ACTION_RESOURCE_RESOURCE_ID);
		for(Resource resource:resourceTree){
			List<Resource> subResourceList = getSubResourceListById(resource.getResourceId());
			resource.setSubResourceList(subResourceList);
		}
		return resourceTree;
	}

	public Resource getResourceByResourceUrlPathResourceType(
			String resourceUrlPath, int resourceType) {

		String hql = 
			"from Resource resource" +
			" where resource.resourceType=:resourceType" +
			" and resource.resourceUrlPath=:resourceUrlPath";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("resourceType",resourceType));
		paraObjList.add(new ParameterObject("resourceUrlPath",resourceUrlPath));
		
		List<Resource> resourceList = 
			findDataByHqlParameterListInList(hql, paraObjList);
		
		if(resourceList.size()>1){
			throw new RuntimeException("有重复定义的资源");
		}
		if(resourceList.size() == 1){
			return resourceList.get(0);
		}else{
			return null;
		}
		
	}

	public PageModel<Resource> listResourceInPage(int resourceType,
			long parentResourceId) {
		
		String hql = 
			"from Resource resource" +
			" where resource.resourceType=:resourceType" +
			" and resource.parentResource.resourceId=:parentResourceId" +
			" order by resource.orderNumber asc";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("resourceType",resourceType));
		paraObjList.add(new ParameterObject("parentResourceId",parentResourceId));

		return findDataByHqlParameterListInPage(hql, paraObjList);
	}

	public void deleteResource(Resource resource) {
		
		resourceDao.deleteResource(resource);
	}

}
