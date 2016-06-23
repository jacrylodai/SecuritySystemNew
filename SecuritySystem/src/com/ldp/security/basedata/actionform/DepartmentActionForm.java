package com.ldp.security.basedata.actionform;

import org.apache.struts.action.ActionForm;

import com.ldp.security.util.validate.ClientValidate;

public class DepartmentActionForm extends ActionForm {

	private long departmentId;
	
	private Long parentId;
	
	private String departmentName;
	
	private long[] selectFlag;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public long[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(long[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public void validateData(){
		
		if(ClientValidate.isEmpty(departmentName)){
			throw new RuntimeException("车间名称不能为空");
		}
	}
}
