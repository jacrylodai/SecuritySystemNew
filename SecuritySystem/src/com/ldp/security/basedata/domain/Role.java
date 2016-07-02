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

	public static String getAvailableRoleTypeByUserDepartmentLevel(
			int departmentLevel) {
		
		String availableRoleType = null;
		switch (departmentLevel) {
		case Department.LEVEL_ROOT:
			
			availableRoleType = "RY000";
			break;
			
		case Department.LEVEL_COUNTRY:

			availableRoleType = "RY001";
			break;

		case Department.LEVEL_RAILWAY:

			availableRoleType = "RY002";
			break;

		case Department.LEVEL_STATION:

			availableRoleType = "RY003";
			break;

		case Department.LEVEL_DEPARTMENT:

			availableRoleType = "RY004";
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
