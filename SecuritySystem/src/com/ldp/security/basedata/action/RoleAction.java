package com.ldp.security.basedata.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.actionform.RoleActionForm;
import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.basedata.manager.ResourceManager;
import com.ldp.security.basedata.manager.RoleManager;
import com.ldp.security.basedata.manager.RoleResourceAuthorityManager;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.validate.ClientValidate;

public class RoleAction extends BaseAction{
	
	private static final String LIST_ROLE_PATH = 
		"basedata/role/roleFunc.do?command=listRole";

	private RoleManager roleManager;
	
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public ActionForward saveRolePrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<DataDict> roleTypeList = roleManager.getRoleTypeDataDictList();
		
		request.setAttribute("roleTypeList", roleTypeList);

		return mapping.findForward("saveRolePrepare");
	}

	public ActionForward saveRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoleActionForm actionForm = (RoleActionForm)form;
		actionForm.validateData();
		
		String roleName = actionForm.getRoleName();
		String roleTypeId = actionForm.getRoleTypeId();
		
		DataDict roleType = roleManager.getRoleTypeDataDictById(roleTypeId);
		
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleType(roleType);
		
		roleManager.saveRole(role);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_ROLE_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward listRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PageModel<Role> pageModel = roleManager.listRoleInPage();
		
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listRole");
	}

	public ActionForward updateMenuAuthorityPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoleActionForm actionForm = (RoleActionForm)form;
		long roleId = actionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		List<Resource> resourceTree = resourceManager.getAllMenuResourceTreeInList();
		
		List<RoleResourceAuthority> authorityList = 
			roleResourceAuthorityManager.getAuthorityListByRoleId(
					Resource.RESOURCE_TYPE_MENU,roleId);
		
		Map<Long, RoleResourceAuthority> authorityMap = 
			roleResourceAuthorityManager
			.getAuthorityMapByResourceTreeAuthorityList(resourceTree,authorityList);
		
		request.setAttribute("role", role);
		request.setAttribute("resourceTree", resourceTree);
		request.setAttribute("authorityMap", authorityMap);
		
		return mapping.findForward("updateMenuAuthorityPrepare");
	}

	public ActionForward updateMenuAuthority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoleActionForm actionForm = (RoleActionForm)form;
		long roleId = actionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		List<Resource> resourceTree = resourceManager.getAllMenuResourceTreeInList();
		
		List<RoleResourceAuthority> authorityList = 
			new ArrayList<RoleResourceAuthority>();
		
		for(Resource topResource:resourceTree){
			
			String topAuthorityIdName = 
				"resourceId_" + topResource.getResourceId() + "_authorityId";
			String topAuthorityIdValueStr = request.getParameter(topAuthorityIdName);
			long topAuthorityIdValue = Long.parseLong(topAuthorityIdValueStr);
			
			String topShowMenuName = "resourceId_" + topResource.getResourceId() + "_showMenu";
			String topShowMenuValue = request.getParameter(topShowMenuName);
			boolean topShowMenu = false;
			if(!ClientValidate.isEmpty(topShowMenuValue) 
					&& topShowMenuValue.equals("on")){
				topShowMenu = true;
			}
			
			if(topAuthorityIdValue != 0){
				
				RoleResourceAuthority topAuthority = 
					roleResourceAuthorityManager.loadRoleResourceAuthorityById(topAuthorityIdValue);
				
				topAuthority.setShowMenu(topShowMenu);				
				authorityList.add(topAuthority);
				
			}else{
				
				RoleResourceAuthority topAuthority = new RoleResourceAuthority();
				topAuthority.setRoleResourceAuthorityId(topAuthorityIdValue);
				topAuthority.setRole(role);
				topAuthority.setResource(topResource);
				topAuthority.setShowMenu(topShowMenu);
				
				authorityList.add(topAuthority);
			}
			
			for(Resource subResource:topResource.getSubResourceList()){

				String subAuthorityIdName = 
					"resourceId_" + subResource.getResourceId() + "_authorityId";
				String subAuthorityIdValueStr = request.getParameter(subAuthorityIdName);
				long subAuthorityIdValue = Long.parseLong(subAuthorityIdValueStr);
				
				String showMenuName = "resourceId_" + subResource.getResourceId() + "_showMenu";
				String showMenuValue = request.getParameter(showMenuName);
				boolean showMenu = false;
				if(!ClientValidate.isEmpty(showMenuValue) 
						&& showMenuValue.equals("on")){
					showMenu = true;
				}
				
				if(subAuthorityIdValue != 0){
					
					RoleResourceAuthority authority = 
						roleResourceAuthorityManager.loadRoleResourceAuthorityById(
								subAuthorityIdValue);
					
					authority.setShowMenu(showMenu);					
					authorityList.add(authority);
					
				}else{
								
					RoleResourceAuthority authority = new RoleResourceAuthority();
					authority.setRoleResourceAuthorityId(subAuthorityIdValue);
					authority.setRole(role);
					authority.setResource(subResource);
					authority.setShowMenu(showMenu);
					
					authorityList.add(authority);
				}
			}
		}
		
		roleResourceAuthorityManager.updateOrSaveAuthorityList(authorityList);

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_ROLE_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward updateActionAuthorityPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoleActionForm actionForm = (RoleActionForm)form;
		long roleId = actionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		List<Resource> resourceTree = resourceManager.getAllActionResourceTreeInList();
		
		List<RoleResourceAuthority> authorityList = 
			roleResourceAuthorityManager.getAuthorityListByRoleId(
					Resource.RESOURCE_TYPE_ACTION_RESOURCE,roleId);
		
		Map<Long, RoleResourceAuthority> authorityMap = 
			roleResourceAuthorityManager
			.getAuthorityMapByResourceTreeAuthorityList(resourceTree,authorityList);
		
		request.setAttribute("role", role);
		request.setAttribute("resourceTree", resourceTree);
		request.setAttribute("authorityMap", authorityMap);
		
		return mapping.findForward("updateActionAuthorityPrepare");
	}

	public ActionForward updateActionAuthority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoleActionForm actionForm = (RoleActionForm)form;
		long roleId = actionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		List<Resource> resourceTree = resourceManager.getAllActionResourceTreeInList();
		
		List<RoleResourceAuthority> authorityList = 
			new ArrayList<RoleResourceAuthority>();
		
		for(Resource topResource:resourceTree){
						
			for(Resource subResource:topResource.getSubResourceList()){

				String subAuthorityIdName = 
					"resourceId_" + subResource.getResourceId() + "_authorityId";
				String subAuthorityIdValueStr = request.getParameter(subAuthorityIdName);
				long subAuthorityIdValue = Long.parseLong(subAuthorityIdValueStr);
				
				String createName = "resourceId_" + subResource.getResourceId() + "_create";
				String createValue = request.getParameter(createName);
				boolean create = false;
				if(!ClientValidate.isEmpty(createValue) 
						&& createValue.equals("on")){
					create = true;
				}
				
				String retrieveName = "resourceId_" + subResource.getResourceId() + "_retrieve";
				String retrieveValue = request.getParameter(retrieveName);
				boolean retrieve = false;
				if(!ClientValidate.isEmpty(retrieveValue) 
						&& retrieveValue.equals("on")){
					retrieve = true;
				}
				
				String updateName = "resourceId_" + subResource.getResourceId() + "_update";
				String updateValue = request.getParameter(updateName);
				boolean update = false;
				if(!ClientValidate.isEmpty(updateValue) 
						&& updateValue.equals("on")){
					update = true;
				}
				
				String deleteName = "resourceId_" + subResource.getResourceId() + "_delete";
				String deleteValue = request.getParameter(deleteName);
				boolean delete = false;
				if(!ClientValidate.isEmpty(deleteValue) 
						&& deleteValue.equals("on")){
					delete = true;
				}

				String otherName = "resourceId_" + subResource.getResourceId() + "_other";
				String otherValue = request.getParameter(otherName);
				boolean other = false;
				if(!ClientValidate.isEmpty(otherValue) 
						&& otherValue.equals("on")){
					other = true;
				}
				
				if(subAuthorityIdValue != 0){
					RoleResourceAuthority authority = 
						roleResourceAuthorityManager.loadRoleResourceAuthorityById(subAuthorityIdValue);
					
					authority.setCreate(create);
					authority.setRetrieve(retrieve);
					authority.setUpdate(update);
					authority.setDelete(delete);
					authority.setOther(other);
					
					authorityList.add(authority);
				}else{
					
					RoleResourceAuthority authority = new RoleResourceAuthority();
					authority.setRole(role);
					authority.setResource(subResource);
					
					authority.setCreate(create);
					authority.setRetrieve(retrieve);
					authority.setUpdate(update);
					authority.setDelete(delete);
					authority.setOther(other);
					
					authorityList.add(authority);
				}
			}
		}
		
		roleResourceAuthorityManager.updateOrSaveAuthorityList(authorityList);

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_ROLE_PATH);
		
		return mapping.findForward("showMessage");
	}
	
}
