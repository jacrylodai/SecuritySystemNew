package com.ldp.security.basedata.domain;

public class Role {
	
	public static final String ROLE_TYPE_DATA_DICT_CATEGORY = "RY";

	private long roleId;

	//角色名
	private String roleName;
	
	//角色类型
	private DataDict roleType;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public DataDict getRoleType() {
		return roleType;
	}

	public void setRoleType(DataDict roleType) {
		this.roleType = roleType;
	}
	
}
