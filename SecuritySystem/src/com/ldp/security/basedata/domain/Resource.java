package com.ldp.security.basedata.domain;

import java.util.List;

/**
 * 资源类
 * @author jacrylodai
 *
 */
public class Resource {
	
	/**
	 * 菜单根资源id
	 */
	public static final long ROOT_MENU_RESOURCE_ID = 1;
	
	/**
	 * action根资源id
	 */
	public static final long ROOT_ACTION_RESOURCE_RESOURCE_ID = 101;
	
	/**
	 * 资源类型：菜单
	 */
	public static final int RESOURCE_TYPE_MENU = 1;
	
	/**
	 * 资源类型：action资源
	 */
	public static final int RESOURCE_TYPE_ACTION_RESOURCE = 2;
	
	/**
	 * 资源级别，根节点级别
	 */
	public static final int RESOURCE_LEVEL_ROOT = 1;

	/**
	 * 资源级别，一级资源
	 */
	public static final int RESOURCE_LEVEL_TOP_RESOURCE = 2;

	/**
	 * 资源级别，二级资源
	 */
	public static final int RESOURCE_LEVEL_SECOND_RESOURCE = 3;

	//资源id
	private long resourceId;

	//父资源
	private Resource parentResource;

	//资源名称
	private String resourceName;

	//资源URL地址
	private String resourceUrlPath;
	
	//排序号
	private int orderNumber;
	
	//资源类型。1-菜单，2-用户对action请求的资源
	private int resourceType;
	
	//资源级别。1-root，2-一级资源，3-二级资源。最多只能有2级资源
	private int resourceLevel;
	
	private List<Resource> subResourceList;

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public Resource getParentResource() {
		return parentResource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrlPath() {
		return resourceUrlPath;
	}

	public void setResourceUrlPath(String resourceUrlPath) {
		this.resourceUrlPath = resourceUrlPath;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<Resource> getSubResourceList() {
		return subResourceList;
	}

	public void setSubResourceList(List<Resource> subResourceList) {
		this.subResourceList = subResourceList;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public int getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(int resourceLevel) {
		this.resourceLevel = resourceLevel;
	}
	
}
