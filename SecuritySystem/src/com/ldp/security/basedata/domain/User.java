package com.ldp.security.basedata.domain;

import com.ldp.security.util.constants.Constants;

/**
 * 用户表
 * @author Administrator
 *
 */
public class User {
	
	public static final String USER_SESSION_ID = "USER_SESSION_ID";
	
	//用户权限类型：V-查看。M-管理
	public static final int USER_AUTHORITY_TYPE_VIEWER = 'V';
	
	public static final int USER_AUTHORITY_TYPE_MANAGER = 'M';
	
	//用户id
	private long userId;
	
	//是否删除
	private int isDelete;
	
	//用户权限类型：V-查看。M-管理
	private int userAuthorityType;
	
	//用户部门
	private Department department;
	
	//用户名
	private String username;
	
	//密码：使用MD5加密2次保存
	private String password;
	
	//责任人
	private String contactPeople;
	
	//手机
	private String mobilePhone;
	
	//办公电话
	private String officePhone;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getUserAuthorityType() {
		return userAuthorityType;
	}

	public void setUserAuthorityType(int userAuthorityType) {
		this.userAuthorityType = userAuthorityType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
	
	public String getUserAuthorityTypeDesc(){
		
		String desc = null;
		
		switch (userAuthorityType) {
		case User.USER_AUTHORITY_TYPE_VIEWER:
			desc = "查看";
			break;
			
		case User.USER_AUTHORITY_TYPE_MANAGER:
			desc = "管理";
			break;

		default:
			throw new RuntimeException("用户权限类型值错误");
		}
		
		return desc;
	}
	
	public static boolean checkIsAccessResourceAllowed(
			String servletPath,String command,User user){
		
		if(servletPath.equals(Constants.LOGIN_FUNC) 
				|| servletPath.equals(Constants.SITE_INDEX_FUNC) 
				|| servletPath.equals(Constants.PASSWORD_FUNC)){

			return true;
		}
		
		Department department = user.getDepartment();
		int level = department.getLevel();
		
		switch (level) {
		case Department.LEVEL_COUNTRY:

			if(servletPath.equals(Constants.CUSTOM_STA_PERIOD_SECURITY_FUNC)
					|| servletPath.equals(Constants.DEPARTMENT_FUNC)
					|| servletPath.equals(Constants.USER_FUNC)){
				return true;
			}
			break;

		case Department.LEVEL_RAILWAY:

			if(servletPath.equals(Constants.CUSTOM_STA_PERIOD_SECURITY_FUNC)
					|| servletPath.equals(Constants.DEPARTMENT_FUNC)
					|| servletPath.equals(Constants.USER_FUNC)){
				return true;
			}
			break;

		case Department.LEVEL_STATION:
			//如果用户是管理员，拥有所有权限
			if(User.USER_AUTHORITY_TYPE_MANAGER == user.getUserAuthorityType()){
				
				if(servletPath.equals(Constants.STATION_SECURITY_FORM_FUNC) 
						|| servletPath.equals(Constants.CUSTOM_STA_PERIOD_SECURITY_FUNC)
						|| servletPath.equals(Constants.DEPARTMENT_FUNC)
						|| servletPath.equals(Constants.USER_FUNC)){
					return true;
				}
			}else
				//如果用户是查看员，拥有部分权限
				if(User.USER_AUTHORITY_TYPE_VIEWER == user.getUserAuthorityType()){
					
					if(servletPath.equals(Constants.STATION_SECURITY_FORM_FUNC)
							&& (command.equals("listStationVerifySecurityForm") || command.startsWith("view"))){
						return true;
					}
					if(servletPath.equals(Constants.CUSTOM_STA_PERIOD_SECURITY_FUNC)){
						return true;
					}
					if(servletPath.equals(Constants.DEPARTMENT_FUNC)
							&& (command.startsWith("list") || command.equals("downloadDepartmentExcelTemplate"))){
						return true;
					}
				}else{
					throw new RuntimeException("用户权限类型值错误");
				}
			
			break;

		case Department.LEVEL_DEPARTMENT:
			
			if(servletPath.equals(Constants.DEPARTMENT_SECURITY_FORM_FUNC)){
				return true;
			}
			break;

		default:
			throw new RuntimeException("部门级别错误");
		}
		
		return false;
	}
	
}
