package com.ldp.security.basedata.manager;

import java.io.File;
import java.util.List;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.util.PageModel;

public interface DepartmentManager {

	public void saveDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	/**
	 * 对父部门下的子部门进行分页显示
	 * @param parentId 父部门id
	 * @return
	 */
	public PageModel<Department> listDepartmentInPage(long parentId);
	
	public Department loadDepartmentById(long departmentId);

	/**
	 * 根据此部门id，取得此部门下面一级的子部门
	 * @param departmentId 部门id
	 * @return
	 */
	public List<Department> getSubDepartmentListById(long departmentId);
	
	public void deleteDepartmentById(long departmentId);

	/**
	 * 比较源部门是否是目标部门的父亲，只有源部门是目标部门的父亲
	 * ，源部门才能有权对目标部门的数据进行操纵
	 * @param department 源部门
	 * @param targetDepartment 目标部门
	 * @return
	 */
	public boolean compareDepartmentIsParentOfDepartment(
			Department department, Department targetDepartment);

	/**
	 * 创建车间的反恐填报表excel模板，用于填报数据
	 * @param department
	 * @param sourceTemplateFile
	 * @param excelTemplateFile
	 */
	public void createDepartmentExcelTemplate(Department department,
			File sourceTemplateFile, File excelTemplateFile);

	/**
	 * 根据参数得到反恐填报表模板的文件路径
	 * @param systemDataFolder 系统文件存放目录
	 * @param departmentId 部门id
	 * @return
	 */
	public String getExcelTemplateFilePath(long departmentId);

	public String getWholeYearExcelTemplateFolderPath(long departmentId);

	/**
	 * 生成整年反恐报表
	 * @param department
	 * @param currDepartmentWholeYearFolderPath
	 * @param sourceTemplateFile
	 */
	public void createWholeYearDepartmentExcelTemplate(Department department,
			String currDepartmentWholeYearFolderPath, File sourceTemplateFile);
	
}
