package com.ldp.security.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.UserManager;
import com.ldp.security.system.actionform.LoginActionForm;
import com.ldp.security.util.encrypt.EncryptUtils;

public class LoginAction extends DispatchAction{
	
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public ActionForward loginCheckPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("loginCheckPrepare");
	}

	public ActionForward loginCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LoginActionForm loginActionForm = (LoginActionForm)form;
		loginActionForm.validateData();
		
		User user = userManager.loadUserByUsername(loginActionForm.getUsername());

		String passwordEncHexStr = 
			EncryptUtils.encryptMD5TwiceToHexStr(loginActionForm.getPassword());
			
		ActionMessages errors = new ActionMessages();
		if(user == null){
			errors.add("userNotExist",new ActionMessage("error.message","用户不存在"));
		}else
			if(user.getPassword().equals(passwordEncHexStr)){
				
				HttpSession session = request.getSession();
				session.setAttribute(User.USER_SESSION_ID, user);
				
				int level = user.getDepartment().getLevel();
				String forwardPath = null;
				
				switch(level){
				
				case Department.LEVEL_COUNTRY:
					forwardPath = "countrySiteIndex";
					break;
				
				case Department.LEVEL_RAILWAY:
					forwardPath = "railwaySiteIndex";
					break;
					
				case Department.LEVEL_STATION:
					forwardPath = "stationSiteIndex";
					break;
					
				case Department.LEVEL_DEPARTMENT:
					forwardPath = "departmentSiteIndex";
					break;
					
				default:
					throw new RuntimeException("错误的部门级别");
				}
				
				return mapping.findForward(forwardPath);
			}else{
				errors.add("passwordWrong",new ActionMessage("error.message","密码错误"));
			}
		saveErrors(request, errors);
		
		return mapping.findForward("loginCheckPrepare");
	}


	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		session.removeAttribute(User.USER_SESSION_ID);
		session.invalidate();
		
		return mapping.findForward("loginCheckPrepare");
	}
	
}
