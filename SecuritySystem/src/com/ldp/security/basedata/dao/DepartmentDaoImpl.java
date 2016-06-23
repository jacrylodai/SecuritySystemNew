package com.ldp.security.basedata.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.util.HibernateSessionHolder;

public class DepartmentDaoImpl implements DepartmentDao{

	public Department loadDepartmentById(long departmentId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		Department department = (Department) conn.get(Department.class, departmentId);
		return department;
	}

	public void saveDepartment(Department department) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(department);
	}

	public void updateDepartment(Department department) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(department);
	}

}
