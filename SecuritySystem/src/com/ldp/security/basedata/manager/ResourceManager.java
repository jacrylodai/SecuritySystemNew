package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.Resource;

public interface ResourceManager {

	public void saveResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public Resource loadResourceById(long resourceId);
	
	/**
	 * 根据资源id，取得所有的的子资源
	 * @param resourceId
	 * @return
	 */
	public List<Resource> getSubResourceListById(long resourceId);

	/**
	 * 取得所有的菜单资源
	 * @return
	 */
	public List<Resource> getAllMenuResourceTreeInList();

	/**
	 * 取得所有的访问action资源
	 * @return
	 */
	public List<Resource> getAllActionResourceTreeInList();

	/**
	 * 根据条件，取得对应的resource;
	 * @param resourceUrlPath 资源Url地址
	 * @param resourceType 资源类型
	 * @return
	 */
	public Resource getResourceByResourceUrlPathResourceType(
			String resourceUrlPath, int resourceType);
	
}
