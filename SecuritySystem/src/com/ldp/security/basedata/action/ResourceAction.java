package com.ldp.security.basedata.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.actionform.ResourceActionForm;
import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;

public class ResourceAction extends BaseAction{
	
	private static final String LIST_RESOURCE_PATH = 
		"basedata/resource/resourceFunc.do?command=listResource";

	public ActionForward saveResourcePrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		long parentResourceId = actionForm.getParentResourceId();
		
		Resource parentResource = resourceManager.loadResourceById(parentResourceId);
		
		request.setAttribute("parentResource", parentResource);
		return mapping.findForward("saveResourcePrepare");
	}

	public ActionForward saveResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		actionForm.validateData();

		Resource resource = new Resource();
		
		long parentResourceId = actionForm.getParentResourceId();		
		Resource parentResource = resourceManager.loadResourceById(parentResourceId);
		resource.setParentResource(parentResource);
		
		resource.setResourceName(actionForm.getResourceName());
		resource.setResourceUrlPath(actionForm.getResourceUrlPath());
		resource.setPictureUrlPath(actionForm.getPictureUrlPath());
		
		resource.setOrderNumber(actionForm.getOrderNumber());
		
		int resourceType = actionForm.getResourceType();
		if(resourceType != Resource.RESOURCE_TYPE_MENU 
				&& resourceType != Resource.RESOURCE_TYPE_ACTION_RESOURCE){
			throw new RuntimeException("资源类型的值错误");
		}
		resource.setResourceType(resourceType);
		
		int parentResourceLevel = parentResource.getResourceLevel();
		int resourceLevel = parentResourceLevel + 1;
		resource.setResourceLevel(resourceLevel);
		
		resourceManager.saveResource(resource);
		
		String redirectUrlPath = 
			LIST_RESOURCE_PATH + "&resourceType=" 
			+ resourceType + "&parentResourceId=" + parentResourceId;
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrlPath);
		
		return mapping.findForward("showMessage");
	}
	

	public ActionForward listResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		int resourceType = actionForm.getResourceType();
		
		long parentResourceId = actionForm.getParentResourceId();
		Resource parentResource = resourceManager.loadResourceById(parentResourceId);
		
		PageModel<Resource> pageModel = 
			resourceManager.listResourceInPage(resourceType,parentResourceId);
		
		request.setAttribute("parentResource", parentResource);
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listResource");
	}

	public ActionForward updateResourcePrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		
		long parentResourceId = actionForm.getParentResourceId();		
		Resource parentResource = resourceManager.loadResourceById(parentResourceId);
		
		long resourceId = actionForm.getResourceId();
		Resource resource = resourceManager.loadResourceById(resourceId);
		
		request.setAttribute("parentResource", parentResource);
		request.setAttribute("resource", resource);
		return mapping.findForward("updateResourcePrepare");
	}

	public ActionForward updateResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		actionForm.validateData();
		
		long resourceId = actionForm.getResourceId();
		Resource resource = resourceManager.loadResourceById(resourceId);
		
		resource.setResourceName(actionForm.getResourceName());
		resource.setResourceUrlPath(actionForm.getResourceUrlPath());
		resource.setPictureUrlPath(actionForm.getPictureUrlPath());
		
		resource.setOrderNumber(actionForm.getOrderNumber());
		
		resourceManager.updateResource(resource);
		
		int resourceType = actionForm.getResourceType();
		long parentResourceId = actionForm.getParentResourceId();
		
		String redirectUrlPath = 
			LIST_RESOURCE_PATH + "&resourceType=" 
			+ resourceType + "&parentResourceId=" + parentResourceId;
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrlPath);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward deleteResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ResourceActionForm actionForm = (ResourceActionForm)form;
		long[] resourceIdArr = actionForm.getSelectFlag();
		
		for(long resourceId:resourceIdArr){
			
			Resource resource = resourceManager.loadResourceById(resourceId);
			List<Resource> subResourceList = 
				resourceManager.getSubResourceListById(resourceId);
			if(subResourceList.size()>0){
				throw new RuntimeException(
						"资源:"+resource.getResourceName()+
						" 存在子资源，不允许删除，请先删除子资源后，在删除本资源");
			}
			
			//先删除资源对应的权限
			
			List<RoleResourceAuthority> authorityList = 
				roleResourceAuthorityManager.getAuthorityListByResourceId(resourceId);
			for(RoleResourceAuthority authority:authorityList){
				roleResourceAuthorityManager.deleteRoleResourceAuthority(authority);				
			}
			
			//在删除资源本身
			resourceManager.deleteResource(resource);
		}

		int resourceType = actionForm.getResourceType();
		long parentResourceId = actionForm.getParentResourceId();
		
		String redirectUrlPath = 
			LIST_RESOURCE_PATH + "&resourceType=" 
			+ resourceType + "&parentResourceId=" + parentResourceId;
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrlPath);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward viewResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ResourceActionForm actionForm = (ResourceActionForm)form;
		
		long parentResourceId = actionForm.getParentResourceId();		
		Resource parentResource = resourceManager.loadResourceById(parentResourceId);
		
		long resourceId = actionForm.getResourceId();
		Resource resource = resourceManager.loadResourceById(resourceId);
		
		request.setAttribute("parentResource", parentResource);
		request.setAttribute("resource", resource);
		return mapping.findForward("viewResource");
	}
	
}
