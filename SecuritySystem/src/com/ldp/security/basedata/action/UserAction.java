package com.ldp.security.basedata.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.actionform.UserActionForm;
import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.basedata.manager.RoleManager;
import com.ldp.security.basedata.manager.UserManager;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.encrypt.EncryptUtils;

public class UserAction extends BaseAction{

	private static final String LIST_USER_PATH = 
		"basedata/user/userFunc.do?command=listUser";
	
	private UserManager userManager;
	
	private DepartmentManager departmentManager;
	
	private RoleManager roleManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public ActionForward saveUserPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long departmentId = userActionForm.getDepartmentId();
		Department department = departmentManager.loadDepartmentById(departmentId);
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}

		int departmentLevel = department.getLevel();
		String availableRoleTypeId = 
			Role.getAvailableRoleTypeByUserDepartmentLevel(departmentLevel);
		List<Role> availableRoleList = 
			roleManager.getRoleListByRoleTypeId(availableRoleTypeId);
				
		request.setAttribute("department", department);
		request.setAttribute("availableRoleList", availableRoleList);
		
		return mapping.findForward("saveUserPrepare");
	}

	/**
	 * 同步方法，不允许添加重复的用户名
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public synchronized ActionForward saveUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = currUser.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long departmentId = userActionForm.getDepartmentId();
		Department department = departmentManager.loadDepartmentById(departmentId);
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		userActionForm.validateData();
		
		User user = new User();
		user.setIsDelete(Constants.VALUE_NO);
		
		//当前选择的角色
		long roleId = userActionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		//取得可用的角色列表
		int departmentLevel = department.getLevel();
		String availableRoleTypeId = 
			Role.getAvailableRoleTypeByUserDepartmentLevel(departmentLevel);
		List<Role> availableRoleList = 
			roleManager.getRoleListByRoleTypeId(availableRoleTypeId);
		
		//比较当前选择的角色是否在许可的角色以内
		if(!availableRoleList.contains(role)){
			throw new RuntimeException("你选择的角色不在许可的角色以内");
		}
		
		user.setRole(role);
		
		user.setDepartment(department);
		
		String username = userActionForm.getUsername();
		boolean isUsernameUnique = userManager.getIsUsernameUnique(username);
		if(!isUsernameUnique){
			throw new RuntimeException("用户名已经存在，请重新输入");
		}
		user.setUsername(username);
		
		//对密码进行2次MD5加密
		String passwordEncHexStr = 
			EncryptUtils.encryptMD5TwiceToHexStr(userActionForm.getPassword());
		user.setPassword(passwordEncHexStr);
		
		user.setContactPeople(userActionForm.getContactPeople());
		user.setMobilePhone(userActionForm.getMobilePhone());
		user.setOfficePhone(userActionForm.getOfficePhone());
		
		userManager.saveUser(user);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		
		String redirectUrl = LIST_USER_PATH + "&departmentId=" + departmentId;
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}


	public ActionForward listUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long departmentId = userActionForm.getDepartmentId();
		Department department = departmentManager.loadDepartmentById(departmentId);
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		PageModel<User> pageModel = userManager.listUserInPage(departmentId);
		request.setAttribute("pageModel", pageModel);
		
		request.setAttribute("department", department);
		
		return mapping.findForward("listUser");
	}


	public ActionForward updateUserPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = currUser.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long userId = userActionForm.getUserId();
		User user = userManager.loadUserById(userId);
		Department department = user.getDepartment();
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限对当前用户进行修改");
		}

		int departmentLevel = department.getLevel();
		String availableRoleTypeId = 
			Role.getAvailableRoleTypeByUserDepartmentLevel(departmentLevel);
		List<Role> availableRoleList = 
			roleManager.getRoleListByRoleTypeId(availableRoleTypeId);
		
		request.setAttribute("user", user);
		request.setAttribute("department", department);
		request.setAttribute("availableRoleList", availableRoleList);
		
		return mapping.findForward("updateUserPrepare");
	}
	
	/**
	 * 修改用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = currUser.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long userId = userActionForm.getUserId();
		
		User user = userManager.loadUserById(userId);
		Department department = user.getDepartment();
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限对当前用户进行修改");
		}
		
		userActionForm.validateDataUpdate();
		

		//当前选择的角色
		long roleId = userActionForm.getRoleId();
		Role role = roleManager.loadRoleById(roleId);
		
		//取得可用的角色列表
		int departmentLevel = department.getLevel();
		String availableRoleTypeId = 
			Role.getAvailableRoleTypeByUserDepartmentLevel(departmentLevel);
		List<Role> availableRoleList = 
			roleManager.getRoleListByRoleTypeId(availableRoleTypeId);
		
		//比较当前选择的角色是否在许可的角色以内
		if(!availableRoleList.contains(role)){
			throw new RuntimeException("你选择的角色不在许可的角色以内");
		}
		
		user.setRole(role);
		
		user.setContactPeople(userActionForm.getContactPeople());
		user.setMobilePhone(userActionForm.getMobilePhone());
		user.setOfficePhone(userActionForm.getOfficePhone());
		
		userManager.updateUser(user);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		
		String redirectUrl = LIST_USER_PATH + "&departmentId=" 
			+ user.getDepartment().getDepartmentId();
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward updateUserPasswordPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = currUser.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long userId = userActionForm.getUserId();
		User user = userManager.loadUserById(userId);
		Department department = user.getDepartment();
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限对当前用户进行修改");
		}
		
		request.setAttribute("user", user);
		
		return mapping.findForward("updateUserPasswordPrepare");
	}
	
	/**
	 * 修改用户密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = currUser.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long userId = userActionForm.getUserId();
		User user = userManager.loadUserById(userId);
		Department department = user.getDepartment();
		
		if(!departmentManager.compareDepartmentIsParentOfDepartment(
				userDepartment, department)){
			throw new RuntimeException("你没有权限对当前用户进行修改");
		}
		
		userActionForm.validateDataUpdatePassword();
		
		String password = userActionForm.getPassword();
			//对密码进行2次MD5加密
		String passwordEncHexStr = 
			EncryptUtils.encryptMD5TwiceToHexStr(password);
		user.setPassword(passwordEncHexStr);
				
		userManager.updateUser(user);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		
		String redirectUrl = LIST_USER_PATH + "&departmentId=" 
			+ user.getDepartment().getDepartmentId();
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();
		
		UserActionForm userActionForm = (UserActionForm)form;
		long[] userIdArr = userActionForm.getSelectFlag();
		long departmentId = userActionForm.getDepartmentId();
		
		for(long userId:userIdArr){
			
			User tempUser = userManager.loadUserById(userId);
			Department tempDepartment = tempUser.getDepartment();
			if(!departmentManager.compareDepartmentIsParentOfDepartment(
					userDepartment, tempDepartment)){
				throw new RuntimeException("你没有权限删除其他部门的用户");
			}
			
			userManager.deleteUserById(userId);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);
		
		String redirectUrl = LIST_USER_PATH + "&departmentId=" + departmentId;
		
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}
}
