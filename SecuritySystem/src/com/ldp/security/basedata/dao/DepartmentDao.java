package com.ldp.security.basedata.dao;

import com.ldp.security.basedata.domain.Department;

public interface DepartmentDao {

	public void saveDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public Department loadDepartmentById(long departmentId);
	
}
