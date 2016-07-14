package com.ldp.security.sta.manager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.report.domain.CommonForm;
import com.ldp.security.report.domain.KeyunForm;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.manager.SecurityFormManager;
import com.ldp.security.sta.dao.CommonStaDao;
import com.ldp.security.sta.dao.KeyunStaDao;
import com.ldp.security.sta.dao.StaPeriodSecurityDao;
import com.ldp.security.sta.domain.CommonCaculateObject;
import com.ldp.security.sta.domain.CommonSta;
import com.ldp.security.sta.domain.KeyunCaculateObject;
import com.ldp.security.sta.domain.KeyunSta;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.business.excel.departmentStaOutput.DepartmentStaExcelUtil;
import com.ldp.security.util.database.ParameterObject;
import com.ldp.security.util.file.FileUtil;
import com.ldp.security.util.validate.ClientValidate;
import com.ldp.security.util.xml.XMLConfigReader;

public class StaPeriodSecurityManagerImpl extends AbstractManager<StaPeriodSecurity>
	implements StaPeriodSecurityManager{

	private StaPeriodSecurityDao staPeriodSecurityDao;
	
	private CommonStaDao commonStaDao;
	
	private KeyunStaDao keyunStaDao;
	
	private DepartmentManager departmentManager;
	
	private SecurityFormManager securityFormManager;
	
	private StaPeriodInfoManager staPeriodInfoManager;
	
	public void setStaPeriodSecurityDao(StaPeriodSecurityDao staPeriodSecurityDao) {
		this.staPeriodSecurityDao = staPeriodSecurityDao;
	}

	public void setCommonStaDao(CommonStaDao commonStaDao) {
		this.commonStaDao = commonStaDao;
	}

	public void setKeyunStaDao(KeyunStaDao keyunStaDao) {
		this.keyunStaDao = keyunStaDao;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setSecurityFormManager(SecurityFormManager securityFormManager) {
		this.securityFormManager = securityFormManager;
	}

	public StaPeriodSecurity loadStaPeriodSecurityById(long staPeriodSecurityId) {
		
		return staPeriodSecurityDao.loadById(staPeriodSecurityId);
	}

	public void setStaPeriodInfoManager(StaPeriodInfoManager staPeriodInfoManager) {
		this.staPeriodInfoManager = staPeriodInfoManager;
	}

	public void saveStaPeriodSecurity(
			StaPeriodSecurity staPeriodSecurity) {

		commonStaDao.save(staPeriodSecurity.getCommonSta());
		keyunStaDao.save(staPeriodSecurity.getKeyunSta());
		staPeriodSecurityDao.save(staPeriodSecurity);
	}

	public StaPeriodSecurity processDepartmentSta(StaPeriodInfo staPeriodInfo,
			Department department) {

		if(department.getLevel() == Department.LEVEL_DEPARTMENT){
			
			//如果部门是车间，就对车间进行统计
			//取出在这个区间内的反恐填报表列表，
			//用于统计，要求通过审核
			
			Long departmentId = department.getDepartmentId();
			String startDateString = staPeriodInfo.getStartDateString();
			String endDateString = staPeriodInfo.getEndDateString();
			
			List<SecurityForm> securityFormList = 
				securityFormManager.getSecurityFormListByParam(
						null, departmentId, startDateString,endDateString
						, SecurityForm.STATE_VERIFY);
			
			StaPeriodSecurity staPeriodSecurity = 
				caculateSecurityFormList(securityFormList);
			
			staPeriodSecurity.setStaDepartment(department);
			
			staPeriodSecurity.setStaPeriodInfo(staPeriodInfo);
			staPeriodSecurity.setEstimateStaDays(staPeriodInfo.getLastDays());
			
			saveStaPeriodSecurity(staPeriodSecurity);
			return staPeriodSecurity;
		}else{
			
			//如果部门不是车间，那就先统计出其下子部门的数据
			//，最后对子部门的数据求和就得到当前部门的数据
			List<Department> subDepartmentList = 
				departmentManager.getSubDepartmentListById(department.getDepartmentId());
			
			List<StaPeriodSecurity> subStaPeriodSecurityList = new ArrayList<StaPeriodSecurity>();
			for(Department subDepartment:subDepartmentList){
				StaPeriodSecurity subStaPeriodSecurity =
					processDepartmentSta(staPeriodInfo,subDepartment);
				subStaPeriodSecurityList.add(subStaPeriodSecurity);
			}
			
			StaPeriodSecurity staPeriodSecurity = 
				caculateStaPeriodSecurityList(subStaPeriodSecurityList);
			
			//自动安检仪检查人数这个统计，只有车间有意义，对于车站，路局没有意义
			//所以把数值设置为0
			int securityMachineMaxCount = 
				XMLConfigReader.getInstance().getSystemConfig()
				.getSecurityMachineMaxCount();
			
			List<Integer> emptyCheckNumList = 
				new ArrayList<Integer>();
			CaculateUtil.initialDataList(securityMachineMaxCount, emptyCheckNumList);
			
			CommonSta commonSta = staPeriodSecurity.getCommonSta();
			commonSta.setSecurityMachineCheckNumList(emptyCheckNumList);
			//end
			
			staPeriodSecurity.setStaDepartment(department);
			staPeriodSecurity.setStaPeriodInfo(staPeriodInfo);
			
			saveStaPeriodSecurity(staPeriodSecurity);
			return staPeriodSecurity;
		}
	}

	/**
	 * 对反恐统计类列表进行汇总，并返回结果
	 * @param staPeriodSecurityList
	 * @return
	 */
	private StaPeriodSecurity caculateStaPeriodSecurityList(
			List<StaPeriodSecurity> staPeriodSecurityList) {
		
		List<CommonCaculateObject> commonCacuObjList = new ArrayList<CommonCaculateObject>();
		List<KeyunCaculateObject> keyunCacuObjList = new ArrayList<KeyunCaculateObject>();
		int actualStaDays = 0;
		int estimateStaDays = 0;
		
		for(StaPeriodSecurity staPeriodSecurity:staPeriodSecurityList){
			
			CommonSta commonSta = staPeriodSecurity.getCommonSta();
			CommonCaculateObject commonCacuObj = new CommonCaculateObject();
			actualStaDays += staPeriodSecurity.getActualStaDays();
			estimateStaDays += staPeriodSecurity.getEstimateStaDays();
			
			KeyunSta keyunSta = staPeriodSecurity.getKeyunSta();
			KeyunCaculateObject keyunCacuObj = new KeyunCaculateObject();
			try {
				BeanUtils.copyProperties(commonCacuObj, commonSta);
				BeanUtils.copyProperties(keyunCacuObj, keyunSta);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			commonCacuObjList.add(commonCacuObj);
			keyunCacuObjList.add(keyunCacuObj);
		}
		
		CommonCaculateObject resultCommCacuObj = 
			CommonCaculateObject.caculateCommonCaculateObjectList(commonCacuObjList);
		CommonSta resultCommonSta = new CommonSta();
		
		KeyunCaculateObject resultKeyunCacuObj = 
			KeyunCaculateObject.caculateKeyunCaculateObjectList(keyunCacuObjList);
		KeyunSta resultKeyunSta = new KeyunSta();
		try {
			BeanUtils.copyProperties(resultCommonSta, resultCommCacuObj);
			BeanUtils.copyProperties(resultKeyunSta, resultKeyunCacuObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		StaPeriodSecurity staPeriodSecurity = new StaPeriodSecurity();
		staPeriodSecurity.setCommonSta(resultCommonSta);
		staPeriodSecurity.setKeyunSta(resultKeyunSta);
		staPeriodSecurity.setActualStaDays(actualStaDays);
		staPeriodSecurity.setEstimateStaDays(estimateStaDays);
		
		return staPeriodSecurity;
	}

	/**
	 * 对指定区间的反恐统计表的数值求和，得到反恐统计表
	 * @param securityFormList
	 * @return
	 */
	private StaPeriodSecurity caculateSecurityFormList(
			List<SecurityForm> securityFormList) {


		List<CommonCaculateObject> commonCacuObjList = new ArrayList<CommonCaculateObject>();
		List<KeyunCaculateObject> keyunCacuObjList = new ArrayList<KeyunCaculateObject>();
		int actualStaDays = 0;
		
		for(SecurityForm securityForm:securityFormList){
			
			CommonForm commonForm = securityForm.getCommonForm();
			CommonCaculateObject commonCacuObj = new CommonCaculateObject();
			actualStaDays += securityForm.getLastDays();
			
			KeyunForm keyunForm = securityForm.getKeyunForm();
			KeyunCaculateObject keyunCacuObj = new KeyunCaculateObject();
			try {
				BeanUtils.copyProperties(commonCacuObj, commonForm);
				BeanUtils.copyProperties(keyunCacuObj, keyunForm);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			commonCacuObjList.add(commonCacuObj);
			keyunCacuObjList.add(keyunCacuObj);
		}
		
		CommonCaculateObject resultCommCacuObj = 
			CommonCaculateObject.caculateCommonCaculateObjectList(commonCacuObjList);
		CommonSta resultCommonSta = new CommonSta();
		
		KeyunCaculateObject resultKeyunCacuObj = 
			KeyunCaculateObject.caculateKeyunCaculateObjectList(keyunCacuObjList);
		KeyunSta resultKeyunSta = new KeyunSta();
		try {
			BeanUtils.copyProperties(resultCommonSta, resultCommCacuObj);
			BeanUtils.copyProperties(resultKeyunSta, resultKeyunCacuObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		StaPeriodSecurity staPeriodSecurity = new StaPeriodSecurity();
		staPeriodSecurity.setCommonSta(resultCommonSta);
		staPeriodSecurity.setKeyunSta(resultKeyunSta);
		staPeriodSecurity.setActualStaDays(actualStaDays);
		
		return staPeriodSecurity;
	}

	public PageModel<StaPeriodSecurity> listMainStaPeriodSecurityInPage(
			String startDateString, String endDateString, int staType,
			long staDepartmentId) {
		
		StringBuffer sb = new StringBuffer();
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		sb.append("from StaPeriodSecurity staPeriodSecurity" +
				" where staPeriodSecurity.staDepartment.departmentId=:staDepartmentId" +
				" and staPeriodSecurity.staPeriodInfo.staType=:staType");
		paraObjList.add(new ParameterObject("staDepartmentId",staDepartmentId));
		paraObjList.add(new ParameterObject("staType",staType));
		
		if(!ClientValidate.isEmpty(startDateString)){
			
			sb.append(" and staPeriodSecurity.staPeriodInfo.startDateString>=:startDateString");
			paraObjList.add(new ParameterObject("startDateString",startDateString));
		}

		if(!ClientValidate.isEmpty(endDateString)){
			
			sb.append(" and staPeriodSecurity.staPeriodInfo.endDateString<=:endDateString");
			paraObjList.add(new ParameterObject("endDateString",endDateString));
		}
		
		sb.append(" order by staPeriodSecurity.staPeriodInfo.staTimeString desc");
	
		return findDataByHqlParameterListInPage(sb.toString(), paraObjList);
	}

	public StaPeriodSecurity getStaPeriodSecurityByStaPeriodInfoIdStaDepartmentId(
			long staPeriodInfoId, long staDepartmentId) {

		String hql = 
			"from StaPeriodSecurity staPeriodSecurity" +
			" where staPeriodSecurity.staPeriodInfo.staPeriodInfoId=:staPeriodInfoId" +
			" and staPeriodSecurity.staDepartment.departmentId=:staDepartmentId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("staPeriodInfoId",staPeriodInfoId));
		paraObjList.add(new ParameterObject("staDepartmentId",staDepartmentId));
		
		List<StaPeriodSecurity> staPeriodSecurityList = 
			findDataByHqlParameterListInList(hql, paraObjList);
		
		if(staPeriodSecurityList.size()==0){
			throw new RuntimeException("没有找到对应的反恐周期统计表");
		}

		if(staPeriodSecurityList.size()>1){
			throw new RuntimeException("有多余的反恐周期统计表");
		}
			
		StaPeriodSecurity staPeriodSecurity = staPeriodSecurityList.get(0);
		return staPeriodSecurity;
	}

	public PageModel<StaPeriodSecurity> listSubStaPeriodSecurityInPage(
			long staPeriodInfoId,long staParentDepartmentId) {

		StringBuffer sb = new StringBuffer();
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		sb.append("from StaPeriodSecurity staPeriodSecurity" +
				" where staPeriodSecurity.staPeriodInfo.staPeriodInfoId=:staPeriodInfoId" +
				" and staPeriodSecurity.staDepartment.parentDepartment.departmentId=:staParentDepartmentId" +
				" order by staPeriodSecurity.staDepartment.departmentId asc");
		
		paraObjList.add(new ParameterObject("staPeriodInfoId",staPeriodInfoId));
		paraObjList.add(new ParameterObject("staParentDepartmentId",staParentDepartmentId));
		
		return findDataByHqlParameterListInPage(sb.toString(), paraObjList);
	}

	public void deleteStaPeriodSecurityAndInfoByStaPeriodInfoId(
			long staPeriodInfoId) {
		
		String hql = 
			"from StaPeriodSecurity staPeriodSecurity" +
			" where staPeriodSecurity.staPeriodInfo.staPeriodInfoId=:staPeriodInfoId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("staPeriodInfoId",staPeriodInfoId));
		
		List<StaPeriodSecurity> staPeriodSecurityList = 
			findDataByHqlParameterListInList(hql, paraObjList);
		
		for(StaPeriodSecurity staPeriodSecurity:staPeriodSecurityList){
			deleteStaPeriodSecurity(staPeriodSecurity);
		}
		
		staPeriodInfoManager.deleteStaPeriodInfoById(staPeriodInfoId);
	}

	/**
	 * 删除反恐统计结果
	 * @param staPeriodSecurity
	 */
	public void deleteStaPeriodSecurity(StaPeriodSecurity staPeriodSecurity) {

		commonStaDao.delete(staPeriodSecurity.getCommonSta());
		keyunStaDao.delete(staPeriodSecurity.getKeyunSta());
		staPeriodSecurityDao.delete(staPeriodSecurity);
	}

	public void processDepartmentStaExcelReport(StaPeriodInfo staPeriodInfo,
			Department department,File sourceExcelReportTemplateFile
			, String currExcelReportFolderPath) {
		
		long staPeriodInfoId = staPeriodInfo.getStaPeriodInfoId();
		long departmentId = department.getDepartmentId();

		//取出统计结果
		StaPeriodSecurity staPeriodSecurity = 
			getStaPeriodSecurityByStaPeriodInfoIdStaDepartmentId(
					staPeriodInfoId, departmentId);
		
		//写入excel文件
		String excelReportName = department.getDepartmentName()
			+"_反恐统计表_"+departmentId+".xls";
		String currExcelReportPath = currExcelReportFolderPath + '/' + excelReportName;
		FileUtil.buildFolder(currExcelReportPath, false);
		File currExcelReportFile = new File(currExcelReportPath);
		
		Workbook sourceWorkbook = null;
		WritableWorkbook destWorkbook = null;
		
		try {
			sourceWorkbook = Workbook.getWorkbook(sourceExcelReportTemplateFile);
			destWorkbook = Workbook.createWorkbook(
					currExcelReportFile, sourceWorkbook);
			
			WritableSheet sheet = destWorkbook.getSheet(0);
			
			DepartmentStaExcelUtil.writeDepartmentStaToExcel(sheet, staPeriodSecurity);
			
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

		if(department.getLevel() != Department.LEVEL_DEPARTMENT){
			
			List<Department> subDepartmentList = 
				departmentManager.getSubDepartmentListById(departmentId);
			
			String currSubExcelReportFolderPath = 
				currExcelReportFolderPath + "/" 
				+ department.getDepartmentName() + "_下属部门统计结果_" + departmentId;
			
			for(Department subDepartment:subDepartmentList){
				processDepartmentStaExcelReport(staPeriodInfo, subDepartment
						,sourceExcelReportTemplateFile, currSubExcelReportFolderPath);
			}
		}
	}

	public List<StaPeriodSecurity> getStaPeriodSecurityListByStaDepartmentId(
			long staDepartmentId) {
		
		String hql = "from StaPeriodSecurity staPeriodSecurity" +
				" where staPeriodSecurity.staDepartment.departmentId=:staDepartmentId";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("staDepartmentId",staDepartmentId));
		return findDataByHqlParameterListInList(hql, paraObjList);
	}
}
