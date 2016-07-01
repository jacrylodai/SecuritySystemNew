package com.ldp.security.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ldp.security.basedata.domain.Permission;
import com.ldp.security.basedata.domain.Resource;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.basedata.domain.RoleResourceAuthority;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.ResourceManager;
import com.ldp.security.basedata.manager.RoleResourceAuthorityManager;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.validate.ClientValidate;

public class BaseAction extends DispatchAction{
	
	protected RoleResourceAuthorityManager roleResourceAuthorityManager;
	
	protected ResourceManager resourceManager;

	public void setRoleResourceAuthorityManager(
			RoleResourceAuthorityManager roleResourceAuthorityManager) {
		this.roleResourceAuthorityManager = roleResourceAuthorityManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		
		if(user == null){
			//用户为空，只能访问登录模块
			if(servletPath.equals(Constants.LOGIN_FUNC)){
				return super.execute(mapping, form, request, response);
			}else{

				throw new RuntimeException(
						"你还没有登录，你没有权限访问当前的资源，resourceUrlPath:"+servletPath);
			}
			
		}else{
			//用户不为空，访问action资源时检查其权限
			
			String command = request.getParameter("command");
			if(ClientValidate.isEmpty(command)){
				throw new RuntimeException("命令参数不能为空");
			}
			
			//公共模块直接访问，包含登录模块，主页模块
			if(servletPath.equals(Constants.LOGIN_FUNC) 
					|| servletPath.equals(Constants.SITE_INDEX_FUNC)){
				return super.execute(mapping, form, request, response);
			}
			
			Role role = user.getRole();
			
			Resource resource = 
				resourceManager.getResourceByResourceUrlPathResourceType(
						servletPath,Resource.RESOURCE_TYPE_ACTION_RESOURCE);
			if(resource == null){
				throw new RuntimeException("你所访问的资源不存在或没有定义");
			}
			
			RoleResourceAuthority authority = 
				roleResourceAuthorityManager.getAuthorityByRoleIdResourceId(
						role.getRoleId(),resource.getResourceId());
			
			boolean allowAccess = false;
			
			if(authority == null){
				allowAccess = false;
			}else{
				int permission = Permission.getPermissionByRequestCommand(command);
				allowAccess = authority.isPermission(permission);
			}
			
			if(allowAccess){
				return super.execute(mapping, form, request, response);
			}else{
				throw new RuntimeException(
						"你没有权限访问当前的资源，resourceUrlPath:"+servletPath
						+".command:"+command);
			}
		}
	}
	
}
