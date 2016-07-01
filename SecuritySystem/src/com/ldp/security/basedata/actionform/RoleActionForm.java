package com.ldp.security.basedata.actionform;

import org.apache.struts.action.ActionForm;

import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.util.validate.ClientValidate;

public class RoleActionForm extends ActionForm{

	private long roleId;

	//角色名
	private String roleName;
	
	//角色类型
	private String roleTypeId;

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

	public String getRoleTypeId() {
		return roleTypeId;
	}

	public void setRoleTypeId(String roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	public void validateData() {

		if(ClientValidate.isEmpty(roleName)){
			throw new RuntimeException("角色名称不能为空");
		}
		
		if(roleTypeId.equals("none")){
			throw new RuntimeException("请选择角色类型");
		}
	}
	
}
