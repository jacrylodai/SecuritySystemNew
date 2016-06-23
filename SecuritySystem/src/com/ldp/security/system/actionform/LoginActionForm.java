package com.ldp.security.system.actionform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.action.ActionForm;

import com.ldp.security.util.validate.ClientValidate;

public class LoginActionForm extends ActionForm{

	private static final Pattern checkPattern=
		Pattern.compile("[\\w]{4,}");
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void validateData(){

		if(ClientValidate.isEmpty(username)){
			throw new RuntimeException("用户名不能为空");
		}

		Matcher usernameM = checkPattern.matcher(username);
		if(!usernameM.matches()){
			throw new RuntimeException("用户名只能由字母或数字组成，至少为4位");			
		}
		
		if(ClientValidate.isEmpty(password)){
			throw new RuntimeException("密码不能为空");			
		}
		
		Matcher passwordM = checkPattern.matcher(password);
		if(!passwordM.matches()){
			throw new RuntimeException("密码只能由字母或数字组成，至少为4位");			
		}
	}
}
