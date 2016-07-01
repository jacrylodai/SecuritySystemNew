package com.ldp.security.basedata.domain;

import com.ldp.security.util.constants.Constants;

/**
 * 用户表
 * @author Administrator
 *
 */
public class User {
	
	public static final String USER_SESSION_ID = "currUser";
	
	//用户id
	private long userId;
	
	//是否删除
	private int isDelete;
		
	//用户部门
	private Department department;
	
	//角色
	private Role role;
	
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
		
}
