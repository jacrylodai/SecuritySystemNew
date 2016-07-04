package com.ldp.security.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.common.action.BaseAction;

public class SiteIndexAction extends BaseAction{

	public ActionForward siteIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("siteIndex");
	}

	public ActionForward siteMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Role role = user.getRole();

		List<Resource> resourceTree = resourceManager.getAllMenuResourceTreeInList();
		
		List<RoleResourceAuthority> authorityList = 
			roleResourceAuthorityManager.getAuthorityListByResourceTypeRoleId(
					Resource.RESOURCE_TYPE_MENU,role.getRoleId());
		
		Map<Long, RoleResourceAuthority> authorityMap = 
			roleResourceAuthorityManager
			.getAuthorityMapByResourceTreeAuthorityList(resourceTree,authorityList);
		
		request.setAttribute("resourceTree", resourceTree);
		request.setAttribute("authorityMap", authorityMap);
				
		return mapping.findForward("siteMenu");
	}
	
}
