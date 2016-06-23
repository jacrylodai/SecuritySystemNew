package com.ldp.security.basedata.actionform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.action.ActionForm;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.validate.ClientValidate;

public class UserActionForm extends ActionForm {

	private static final Pattern checkPattern=
		Pattern.compile("[\\w]{4,}");
	
	//用户id
	private long userId;	
	
	//用户权限类型：V-查看。M-管理
	private int userAuthorityType;
	
	//用户部门id
	private long departmentId;
	
	//用户名
	private String username;
	
	//密码
	private String password;
	
	//确认密码
	private String affirmPassword;
	
	//责任人
	private String contactPeople;
	
	//手机
	private String mobilePhone;
	
	//办公电话
	private String officePhone;
	
	private long[] selectFlag;
		
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getUserAuthorityType() {
		return userAuthorityType;
	}

	public void setUserAuthorityType(int userAuthorityType) {
		this.userAuthorityType = userAuthorityType;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

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

	public String getAffirmPassword() {
		return affirmPassword;
	}

	public void setAffirmPassword(String affirmPassword) {
		this.affirmPassword = affirmPassword;
	}

	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public long[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(long[] selectFlag) {
		this.selectFlag = selectFlag;
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

		if(ClientValidate.isEmpty(affirmPassword)){
			throw new RuntimeException("确认密码不能为空");			
		}
		
		Matcher affirmPasswordM = checkPattern.matcher(affirmPassword);
		if(!affirmPasswordM.matches()){
			throw new RuntimeException("确认密码只能由字母或数字组成，至少为4位");			
		}
		
		if(!affirmPassword.equals(password)){
			throw new RuntimeException("密码和确认密码不一致");	
		}
		
		if(userAuthorityType != User.USER_AUTHORITY_TYPE_VIEWER
				&& userAuthorityType != User.USER_AUTHORITY_TYPE_MANAGER){
			throw new RuntimeException("用户权限类型没有选择");	
		}
	}
	
	public void validateDataUpdate(){
		
		if(userAuthorityType != User.USER_AUTHORITY_TYPE_VIEWER
				&& userAuthorityType != User.USER_AUTHORITY_TYPE_MANAGER){
			throw new RuntimeException("用户权限类型没有选择");	
		}
	}

	
	public void validateDataUpdatePassword(){
		
		if(ClientValidate.isEmpty(password)){
			throw new RuntimeException("密码不能为空");			
		}
		
		Matcher passwordM = checkPattern.matcher(password);
		if(!passwordM.matches()){
			throw new RuntimeException("密码只能由字母或数字组成，至少为4位");			
		}

		if(ClientValidate.isEmpty(affirmPassword)){
			throw new RuntimeException("确认密码不能为空");			
		}
		
		Matcher affirmPasswordM = checkPattern.matcher(affirmPassword);
		if(!affirmPasswordM.matches()){
			throw new RuntimeException("确认密码只能由字母或数字组成，至少为4位");			
		}
		
		if(!affirmPassword.equals(password)){
			throw new RuntimeException("密码和确认密码不一致");	
		}	
			
	}
	
}
