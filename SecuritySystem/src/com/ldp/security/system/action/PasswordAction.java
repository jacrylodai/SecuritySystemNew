package com.ldp.security.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.UserManager;
import com.ldp.security.system.actionform.PasswordActionForm;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.encrypt.EncryptUtils;

public class PasswordAction extends DispatchAction {
	
	private static final String UPDATE_PASSWORD_PATH = 
		"system/password/passwordFunc.do?command=updatePasswordPrepare";

	private UserManager userManager;
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public ActionForward updatePasswordPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("updatePasswordPrepare");
	}

	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PasswordActionForm actionForm = (PasswordActionForm)form;
		actionForm.validateData();
		
		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		long userId = user.getUserId();
		
		String oldPassword = actionForm.getOldPassword();
		boolean isCorrect = userManager.checkIsUserPasswordCorrect(userId, oldPassword);
		
		if(!isCorrect){
			throw new RuntimeException("原密码错误，请重新输入");
		}
		
		String newPassword = actionForm.getNewPassword();
		String newPasswordHexStr = EncryptUtils.encryptMD5TwiceToHexStr(newPassword);
		
		userManager.updateUserPassword(userId, newPasswordHexStr);

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, UPDATE_PASSWORD_PATH);
		return mapping.findForward("showMessage");
	}
	
}
