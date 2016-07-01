package com.ldp.security.basedata.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

import com.ldp.security.basedata.dao.DepartmentDao;
import com.ldp.security.basedata.domain.Department;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.sta.manager.StaPeriodInfoManager;
import com.ldp.security.sta.manager.StaPeriodSecurityManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.business.excel.departmentTemplateOutput.DepartmentTemplateExcelUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FileNameConstants;
import com.ldp.security.util.constants.FolderConstants;
import com.ldp.security.util.xml.XMLConfigReader;

public class DepartmentManagerImpl extends AbstractManager<Department> implements
		DepartmentManager {
	
	private static final Logger logger = Logger.getLogger(DepartmentManagerImpl.class);
	
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public PageModel<Department> listDepartmentInPage(long parentId) {

		PageModel<Department> pageModel = 
			findDataByHqlInPage(
					"from Department department" +
					" where department.parentDepartment.departmentId=?" +
					" and department.isDelete=?" +
					" order by department.departmentName asc"
					, new Object[]{parentId,Constants.VALUE_NO} );
		return pageModel;
	}

	public Department loadDepartmentById(long departmentId) {
		
		Department department = departmentDao.loadDepartmentById(departmentId);
		if(department == null){
			throw new RuntimeException("车间id："+departmentId+" 所指向车间不存在");
		}
		return department;
	}

	public void saveDepartment(Department department) {

		departmentDao.saveDepartment(department);
	}

	public void updateDepartment(Department department) {

		departmentDao.updateDepartment(department);
	}

	public List<Department> getSubDepartmentListById(long departmentId) {
		
		return findDataByHqlInList(
				"from Department department" +
				" where department.parentDepartment.departmentId=?" +
				" and department.isDelete=?" +
				" order by department.departmentId asc"
				, new Object[]{ departmentId,Constants.VALUE_NO });
	}

	public void deleteDepartmentById(long departmentId) {

		Department department = departmentDao.loadDepartmentById(departmentId);

		department.setIsDelete(Constants.VALUE_YES);
		departmentDao.updateDepartment(department);
	}

	public boolean compareDepartmentIsParentOfDepartment(
			Department department, Department targetDepartment) {

		if(!department.higherLevelThan(targetDepartment)){
			return false;
		}
		
		Department parentDepartment = 
			departmentDao.loadDepartmentById(
					targetDepartment.getParentDepartment().getDepartmentId());
		if(department.equals(parentDepartment)){
			return true;
		}
		
		while(parentDepartment.getDepartmentId() != Department.ID_ROOT){
			parentDepartment = parentDepartment.getParentDepartment();
			if(department.equals(parentDepartment)){
				return true;
			}
		}
		
		return false;
	}

	public void createDepartmentExcelTemplate(Department department,
			File sourceTemplateFile, File excelTemplateFile){
			
		if(department.getLevel() != Department.LEVEL_DEPARTMENT){
			throw new RuntimeException("只有车间才能创建反恐填报表excel模板");
		}
		Workbook sourceWorkbook = null;
		WritableWorkbook destWorkbook = null;
		
		try {
			sourceWorkbook = Workbook.getWorkbook(sourceTemplateFile);
			destWorkbook = Workbook.createWorkbook(
					excelTemplateFile, sourceWorkbook);
			
			WritableSheet sheet = destWorkbook.getSheet(0);
			
			DepartmentTemplateExcelUtil.writeDepartmentTemplateToExcel(sheet, department);
			destWorkbook.write();
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (WriteException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (BiffException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			if(null!=destWorkbook){
				try {
					destWorkbook.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != sourceWorkbook){
				sourceWorkbook.close();
			}
		}
		
		
	}

	public String getExcelTemplateFilePath(long departmentId) {

		long folderIndex = departmentId % 100;
		
		String systemDataFolder = 
			XMLConfigReader.getInstance().getSystemConfig().getSystemDataFolder();
		
		String excelTemplateFolderPath = 
			systemDataFolder +'/' + FolderConstants.DEPARTMENT_EXCEL_TEMPLATE_FOLDER
			+ '/' + folderIndex;
		
		String excelTemplateFileName = 
			"excelTemplateFile_" + departmentId + ".xls";
		
		String excelTemplateFilePath = 
			excelTemplateFolderPath + '/'+ excelTemplateFileName;
		return excelTemplateFilePath;
		
	}

}
