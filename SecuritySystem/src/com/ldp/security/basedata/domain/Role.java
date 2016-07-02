package com.ldp.security.basedata.domain;

public class Role {
	
	/**
	 * 角色类型数据字典中的种类
	 */
	public static final String ROLE_TYPE_DATA_DICT_CATEGORY = "RY";
	
	/**
	 * 根节点角色
	 */
	public static final String ROLE_TYPE_ID_ROOT = "RY000";
	
	public static final String ROLE_TYPE_ID_COUNTRY = "RY001";
	
	public static final String ROLE_TYPE_ID_RAILWAY = "RY002";
	
	public static final String ROLE_TYPE_ID_STATION = "RY003";
	
	public static final String ROLE_TYPE_ID_DEPARTMENT = "RY004";

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

	public static String getAvailableRoleTypeByUserDepartmentLevel(
			int departmentLevel) {
		
		String availableRoleType = null;
		switch (departmentLevel) {
		case Department.LEVEL_ROOT:
			
			availableRoleType = ROLE_TYPE_ID_ROOT;
			break;
			
		case Department.LEVEL_COUNTRY:

			availableRoleType = ROLE_TYPE_ID_COUNTRY;
			break;

		case Department.LEVEL_RAILWAY:

			availableRoleType = ROLE_TYPE_ID_RAILWAY;
			break;

		case Department.LEVEL_STATION:

			availableRoleType = ROLE_TYPE_ID_STATION;
			break;

		case Department.LEVEL_DEPARTMENT:

			availableRoleType = ROLE_TYPE_ID_DEPARTMENT;
			break;

		default:
			break;
		}
		
		return availableRoleType;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(obj instanceof Role){
			
			Role targetObj = (Role) obj;
			long targetObjId = targetObj.getRoleId();
			return roleId == targetObjId;
			
		}else{
			return false;
		}
	}
	
}
