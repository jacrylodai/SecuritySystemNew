package com.ldp.security.basedata.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.ldp.security.util.PageModel;
import com.ldp.security.util.business.excel.departmentTemplateOutput.DepartmentTemplateExcelUtil;
import com.ldp.security.util.business.excel.departmentTemplateWithDayInfoOut.DepartmentTemplateWithDayInfoExcelUtil;
import com.ldp.security.util.compress.ZipUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FolderConstants;
import com.ldp.security.util.database.ParameterObject;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.file.FileUtil;
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
			File sourceTemplateFile){
			
		if(department.getLevel() != Department.LEVEL_DEPARTMENT){
			throw new RuntimeException("只有车间才能创建反恐填报表excel模板");
		}

		long departmentId = department.getDepartmentId();
		String excelTemplateFilePath = getExcelTemplateFilePath(departmentId);
		
		File excelTemplateFile = new File(excelTemplateFilePath);
		
		File excelTemplateFolder = excelTemplateFile.getParentFile();
		excelTemplateFolder.mkdirs();
		
		excelTemplateFile.delete();
		
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

	public String getWholeYearExcelTemplateFolderPath(long departmentId) {

		long folderIndex = departmentId % 100;
		
		String systemDataFolder = 
			XMLConfigReader.getInstance().getSystemConfig().getSystemDataFolder();
		
		String excelTemplateFolderPath = 
			systemDataFolder +'/' 
			+ FolderConstants.DEPARTMENT_WHOLE_YEAR_EXCEL_TEMPLATE_FOLDER
			+ '/' + folderIndex;
		
		return excelTemplateFolderPath;
		
	}

	public void createWholeYearDepartmentExcelTemplate(Department department,
			File sourceTemplateFile) {

		if(department.getLevel() != Department.LEVEL_DEPARTMENT){
			throw new RuntimeException("只有车间才能创建反恐填报表excel模板");
		}

		long departmentId = department.getDepartmentId();
		String wholeYearExcelTemplateFolderPath = 
			getWholeYearExcelTemplateFolderPath(departmentId);
		FileUtil.buildFolder(wholeYearExcelTemplateFolderPath, true);

		String currDepartmentWholeYearZipFilePath = 
			wholeYearExcelTemplateFolderPath + '/'
			+ "wholeYearExcelTemplateZipFile_" + department.getDepartmentId() +".zip";
		File currDepartmentWholeYearZipFile = new File(currDepartmentWholeYearZipFilePath);
		if(currDepartmentWholeYearZipFile.exists()){
			currDepartmentWholeYearZipFile.delete();
		}
		
		int currYear = DateUtil.getCurrentYear();
		
		String currDepartmentWholeYearFolderPath = 
			wholeYearExcelTemplateFolderPath + '/' 
			+ department.getDepartmentName() +'_' +currYear+"年_反恐统计报表模板";
		
		int nextYear = currYear+1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(currYear, Calendar.JANUARY, 1);

		Workbook sourceWorkbook = null;
		WritableWorkbook destWorkbook = null;
		try{
			sourceWorkbook = Workbook.getWorkbook(sourceTemplateFile);
			
			int preYear = calendar.get(Calendar.YEAR);
			//得到的月份值是Calendar专用类型，如Calender.JANUARY,实际值为0
			//应此数值结果应该加1
			int preMonth = calendar.get(Calendar.MONTH)+1;
			int preDay = calendar.get(Calendar.DAY_OF_MONTH);
			
			while(preYear == currYear || ( preYear == nextYear && preMonth ==1) ){
				
				String currDepartmentExcelTemplateFolderPath = 
					currDepartmentWholeYearFolderPath + '/' + preYear + '-' + preMonth;
				FileUtil.buildFolder(currDepartmentExcelTemplateFolderPath, true);
				
				String currDepartmentExcelTemplateFilePath = 
					currDepartmentExcelTemplateFolderPath + '/' 
					+ preYear + '-' + preMonth+'-'+preDay+'_'
					+department.getDepartmentName()+'_'+"反恐报表.xls";
				File excelTemplateFile = new File(currDepartmentExcelTemplateFilePath);
								
				destWorkbook = Workbook.createWorkbook(
						excelTemplateFile, sourceWorkbook);
				
				WritableSheet sheet = destWorkbook.getSheet(0);
				
				DepartmentTemplateWithDayInfoExcelUtil
					.writeDepartmentTemplateToExcelWithDayInfo(
							sheet, department,preYear,preMonth,preDay);
				destWorkbook.write();
				destWorkbook.close();
							
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				preYear = calendar.get(Calendar.YEAR);
				preMonth = calendar.get(Calendar.MONTH)+1;
				preDay = calendar.get(Calendar.DAY_OF_MONTH);
			}
		} catch (BiffException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (RowsExceededException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (WriteException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(null != sourceWorkbook){
				sourceWorkbook.close();
			}
		}
			
		ZipUtil.zipEntry(currDepartmentWholeYearZipFilePath, ""
				, currDepartmentWholeYearFolderPath);
		FileUtil.deleteFolder(new File(currDepartmentWholeYearFolderPath));
		
	}

	public List<Department> getAllLevelDepartmentDepartmentList() {
		
		String hql = "from Department department where department.level=:level";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("level",Department.LEVEL_DEPARTMENT));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public void regenerateDepartmentExcelTemplate(
			List<Department> departmentLisit) {
		// TODO Auto-generated method stub
		
	}

}
