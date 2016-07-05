package com.ldp.security.basedata.actionform;

import org.apache.struts.action.ActionForm;

import com.ldp.security.util.validate.ClientValidate;

public class ResourceActionForm extends ActionForm{

	//资源id
	private long resourceId;

	//父资源
	private long parentResourceId;

	//资源名称
	private String resourceName;

	//资源URL地址
	private String resourceUrlPath;
	
	//图片资源url地址
	private String pictureUrlPath;
	
	//排序号
	private int orderNumber;
	
	//资源类型。1-菜单，2-用户对action请求的资源
	private int resourceType;
	
	private long[] selectFlag;

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public long getParentResourceId() {
		return parentResourceId;
	}

	public void setParentResourceId(long parentResourceId) {
		this.parentResourceId = parentResourceId;
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

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public long[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(long[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getPictureUrlPath() {
		return pictureUrlPath;
	}

	public void setPictureUrlPath(String pictureUrlPath) {
		this.pictureUrlPath = pictureUrlPath;
	}

	public void validateData() {

		if(ClientValidate.isEmpty(resourceName)){
			throw new RuntimeException("资源名称不能为空");
		}

		if(ClientValidate.isEmpty(resourceUrlPath)){
			throw new RuntimeException("资源地址不能为空");
		}
		
	}
	
}
