package com.ldp.security.system.actionform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.action.ActionForm;

import com.ldp.security.util.validate.ClientValidate;

public class PasswordActionForm extends ActionForm {

	private static final Pattern checkPattern=
		Pattern.compile("[\\w]{4,}");
	
	private String oldPassword;
	
	private String newPassword;
	
	private String affirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getAffirmNewPassword() {
		return affirmNewPassword;
	}

	public void setAffirmNewPassword(String affirmNewPassword) {
		this.affirmNewPassword = affirmNewPassword;
	}
	
	public void validateData(){

		if(ClientValidate.isEmpty(oldPassword)){
			throw new RuntimeException("原密码不能为空");
		}

		Matcher oldPasswordM = checkPattern.matcher(oldPassword);
		if(!oldPasswordM.matches()){
			throw new RuntimeException("原密码只能由字母或数字组成，至少为4位");			
		}

		if(ClientValidate.isEmpty(newPassword)){
			throw new RuntimeException("新密码不能为空");
		}

		Matcher newPasswordM = checkPattern.matcher(newPassword);
		if(!newPasswordM.matches()){
			throw new RuntimeException("新密码只能由字母或数字组成，至少为4位");			
		}
		
		if(newPassword.equals(oldPassword)){
			throw new RuntimeException("新密码不能等于原密码");
		}

		if(ClientValidate.isEmpty(affirmNewPassword)){
			throw new RuntimeException("确认密码不能为空");
		}

		Matcher affirmNewPasswordM = checkPattern.matcher(affirmNewPassword);
		if(!affirmNewPasswordM.matches()){
			throw new RuntimeException("确认密码只能由字母或数字组成，至少为4位");			
		}
		
		if(!affirmNewPassword.equals(newPassword)){
			throw new RuntimeException("新密码和确认密码不一致");
		}
		
	}
	
}
