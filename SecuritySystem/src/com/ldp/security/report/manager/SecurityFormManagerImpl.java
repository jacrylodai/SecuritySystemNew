package com.ldp.security.report.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.report.dao.CommonFormDao;
import com.ldp.security.report.dao.KeyunFormDao;
import com.ldp.security.report.dao.SecurityFormDao;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.business.excel.securityFormImport.SecurityFormExcelImportUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.database.ParameterObject;
import com.ldp.security.util.validate.ClientValidate;

public class SecurityFormManagerImpl extends AbstractManager<SecurityForm>
	implements SecurityFormManager {
	
	private SecurityFormDao securityFormDao;
	
	private CommonFormDao commonFormDao;
	
	private KeyunFormDao keyunFormDao;
	
	private DepartmentManager departmentManager;

	public void setSecurityFormDao(SecurityFormDao securityFormDao) {
		this.securityFormDao = securityFormDao;
	}

	public void setCommonFormDao(CommonFormDao commonFormDao) {
		this.commonFormDao = commonFormDao;
	}

	public void setKeyunFormDao(KeyunFormDao keyunFormDao) {
		this.keyunFormDao = keyunFormDao;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void saveSecurityForm(SecurityForm securityForm) {

		commonFormDao.saveCommonForm(securityForm.getCommonForm());
		keyunFormDao.saveKeyunForm(securityForm.getKeyunForm());	
		securityFormDao.saveSecurityForm(securityForm);	
	}

	public boolean checkConflictDateByDepartmentId(long departmentId,
			String startDateString, String endDateString) {

		String hql = 
			"from SecurityForm securityForm" +
			" where securityForm.department.departmentId=?" +
			" and securityForm.state<>?" +
			" and ? between securityForm.startDateString" +
			" and securityForm.endDateString";

		long count = 0;
		
		count = findDataCountByHql(hql, 
				new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,startDateString });		
		if(count>0){
			return true;
		}
		
		count = findDataCountByHql(hql, 
				new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,endDateString });
		if(count>0){
			return true;
		}
		
		String hqlIn = 
			"from SecurityForm securityForm" +
			" where securityForm.department.departmentId=?" +
			" and securityForm.state<>?" +
			" and ?<securityForm.startDateString and securityForm.endDateString<?";
		count = findDataCountByHql(hqlIn
				, new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,startDateString,endDateString });
		if(count>0){
			return true;
		}
		
		return false;
	}

	public SecurityForm loadSecurityFormById(long securityFormId) {

		return securityFormDao.loadSecurityFormById(securityFormId);
	}

	public void updateSecurityForm(SecurityForm securityForm) {

		securityFormDao.updateSecurityForm(securityForm);
		commonFormDao.updateCommonForm(securityForm.getCommonForm());
		keyunFormDao.updateKeyunForm(securityForm.getKeyunForm());
	}

	public void deleteSecurityFormByEraseDataById(long securityFormId) {
		
		SecurityForm securityForm = securityFormDao.loadSecurityFormById(securityFormId);
		
		commonFormDao.deleteCommonForm(securityForm.getCommonForm());
		keyunFormDao.deleteKeyunForm(securityForm.getKeyunForm());
		securityFormDao.deleteSecurityForm(securityForm);
	}

	public void updateSecurityFormStateById(long securityFormId
			,int originState,int newState) {

		SecurityForm securityForm = securityFormDao.loadSecurityFormById(securityFormId);
		if(securityForm.getState() == originState){
			securityForm.setState(newState);
			securityFormDao.updateSecurityForm(securityForm);
		}else{
			throw new RuntimeException("反恐填报表状态错误,id:"
					+securityForm.getSecurityFormId());
		}
	}

	public boolean checkConflictDateWithoutMyselfByDepartmentId(
			long securityFormId, long departmentId, String startDateString,
			String endDateString) {

		String hql = 
			"from SecurityForm securityForm" +
			" where securityForm.department.departmentId=?" +
			" and securityForm.state<>?" +
			" and securityForm.securityFormId<>?" +
			" and ? between securityForm.startDateString" +
			" and securityForm.endDateString";

		long count = 0;
		
		count = findDataCountByHql(hql
				, new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,securityFormId,startDateString });		
		if(count>0){
			return true;
		}
		
		count = findDataCountByHql(hql, 
				new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,securityFormId,endDateString });
		if(count>0){
			return true;
		}
		
		String hqlIn = 
			"from SecurityForm securityForm" +
			" where securityForm.department.departmentId=?" +
			" and securityForm.state<>?" +
			" and securityForm.securityFormId<>?" +
			" and ?<securityForm.startDateString and securityForm.endDateString<?";
		count = findDataCountByHql(hqlIn
				, new Object[]{ departmentId,SecurityForm.STATE_IMPORT
					,securityFormId,startDateString,endDateString });
		if(count>0){
			return true;
		}
		
		return false;
	}
	
	public void processSecurityFormImport(User reportUser,File sourceFile){
				
		InputStream in = null;
		Workbook workbook = null;
		try {
			in = new FileInputStream(sourceFile);
			workbook = Workbook.getWorkbook(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (BiffException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Sheet[] sheetArr = workbook.getSheets();
		for(Sheet sheet:sheetArr){
		
			try{
			
				SecurityFormSheet securityFormSheet = 
					SecurityFormExcelImportUtil.readSecurityFormSheetFromExcel(sheet);
				
				String reportDepartmentName = securityFormSheet.getReportDepartmentName();
				long reportDepartmentId = securityFormSheet.getReportDepartmentId();
				SecurityForm securityForm = securityFormSheet.getSecurityForm();
				
				
				Department department = departmentManager.loadDepartmentById(reportDepartmentId);
				if(department == null){
					throw new RuntimeException(
							"导入错误，Excel表里的上报单位编码，在系统中没有找到对于的单位" +
							"，请检查Excel表里的上报单位编码是否填写正确");
				}
				
				if(!department.getDepartmentName().equals(reportDepartmentName)){
					throw new RuntimeException(
							"导入错误，Excel表里的上报单位编码与上报单位名称不一致" +
							"，Excel表已经被破坏，请重新在系统中下载对应车间的Excel表");
				}
				
				if(department.getLevel()!=Department.LEVEL_DEPARTMENT){
					throw new RuntimeException("只能导入车间级别的反恐报表");
				}
				
				Department currReportUserDepartment = reportUser.getDepartment();
				if(!departmentManager.compareDepartmentIsParentOfDepartment(
						currReportUserDepartment, department)){
					throw new RuntimeException(
							"导入错误，当前用户只能导入其对应部门下面的子部门的数据");
				}
				
				Department station = department.getParentDepartment();
				
				securityForm.setDepartment(department);
				securityForm.setStation(station);
				securityForm.setReportUser(reportUser);
				
				saveSecurityForm(securityForm);
				
			} catch(RuntimeException ex){
				logger.error("", ex);
			}
		}

		workbook.close();
		
	}

	public PageModel<SecurityForm> listSecurityFormByParam(Long stationId,
			Long departmentId, String startDateString, String endDateString,
			Integer state) {
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("from SecurityForm securityForm");
		
		if(null != state){
			
			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.state=:state");
			paraObjList.add(new ParameterObject("state",state));
		}

		if(null != stationId){
			
			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.station.departmentId=:stationId");
			paraObjList.add(new ParameterObject("stationId",stationId));
		}

		if(null != departmentId && -1!=departmentId){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.department.departmentId=:departmentId");
			paraObjList.add(new ParameterObject("departmentId",departmentId));
		}

		if(!ClientValidate.isEmpty(startDateString)){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.startDateString>=:startDateString");
			paraObjList.add(new ParameterObject("startDateString",startDateString));
		}

		if(!ClientValidate.isEmpty(endDateString)){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.endDateString<=:endDateString");
			paraObjList.add(new ParameterObject("endDateString",endDateString));
		}
		
		sb.append(" order by securityForm.reportTimeString desc");
		
		return findDataByHqlParameterListInPage(sb.toString(), paraObjList);
	}

	public List<SecurityForm> getSecurityFormListByParam(Long stationId,
			Long departmentId, String startDateString, String endDateString,
			Integer state) {
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("from SecurityForm securityForm");
		
		if(null != state){
			
			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.state=:state");
			paraObjList.add(new ParameterObject("state",state));
		}

		if(null != stationId){
			
			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.station.departmentId=:stationId");
			paraObjList.add(new ParameterObject("stationId",stationId));
		}

		if(null != departmentId && -1!=departmentId){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.department.departmentId=:departmentId");
			paraObjList.add(new ParameterObject("departmentId",departmentId));
		}

		if(!ClientValidate.isEmpty(startDateString)){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.startDateString>=:startDateString");
			paraObjList.add(new ParameterObject("startDateString",startDateString));
		}

		if(!ClientValidate.isEmpty(endDateString)){

			sb.append(ParameterObject.getWhereOrAndByParaObjListSize(paraObjList));
			sb.append(" securityForm.endDateString<=:endDateString");
			paraObjList.add(new ParameterObject("endDateString",endDateString));
		}
		
		sb.append(" order by securityForm.reportTimeString desc");
		
		return findDataByHqlParameterListInList(sb.toString(), paraObjList);
	}
	
}
