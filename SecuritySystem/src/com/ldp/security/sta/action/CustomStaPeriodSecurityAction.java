package com.ldp.security.sta.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.sta.actionform.StaPeriodSecurityActionForm;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.sta.manager.StaPeriodInfoManager;
import com.ldp.security.sta.manager.StaPeriodSecurityManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.compress.ZipUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FileNameConstants;
import com.ldp.security.util.constants.FolderConstants;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.file.DownloadUtil;
import com.ldp.security.util.file.FileUtil;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 自定义统计功能
 * @author Administrator
 *
 */
public class CustomStaPeriodSecurityAction extends BaseAction{

	private static final String LIST_CUSTOM_STA_PERIOD_INFO_PATH = 
		"sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomStaPeriodInfo";

	private StaPeriodInfoManager staPeriodInfoManager;
	
	private StaPeriodSecurityManager staPeriodSecurityManager;
	
	public void setStaPeriodInfoManager(StaPeriodInfoManager staPeriodInfoManager) {
		this.staPeriodInfoManager = staPeriodInfoManager;
	}

	public void setStaPeriodSecurityManager(
			StaPeriodSecurityManager staPeriodSecurityManager) {
		this.staPeriodSecurityManager = staPeriodSecurityManager;
	}

	public ActionForward saveCustomStaPeriodInfoPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		
		request.setAttribute("staUser", currUser);
		request.setAttribute("department", currUser.getDepartment());
		
		return mapping.findForward("saveCustomStaPeriodInfoPrepare");
	}
	
	public ActionForward saveCustomStaPeriodInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StaPeriodSecurityActionForm actionForm = (StaPeriodSecurityActionForm)form;
		actionForm.validateData();

		StaPeriodInfo staPeriodInfo = new StaPeriodInfo();
		
		User currUser = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Department department = currUser.getDepartment();
		
		staPeriodInfo.setStaUser(currUser);
		staPeriodInfo.setCreatorDepartment(department);
		
		staPeriodInfo.setStaType(StaPeriodInfo.STA_TYPE_CUSTOM);
		
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		staPeriodInfo.setStartDateString(startDateString);
		staPeriodInfo.setEndDateString(endDateString);

		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		if(lastDays>StaPeriodInfo.MAX_LAST_DAYS){
			throw new RuntimeException(
					"统计天数最多只能是"+StaPeriodInfo.MAX_LAST_DAYS+"天");
		}
		staPeriodInfo.setLastDays(lastDays);
		
		staPeriodInfo.setStaTimeString(DateUtil.getCurrentTimeString());
		
		staPeriodInfoManager.saveStaPeriodInfo(staPeriodInfo);
		
		//开始统计
		staPeriodSecurityManager.processDepartmentSta(staPeriodInfo,department);

		//输出excel统计报表
		String sourceTemplateFileFolderPath = 
			request.getSession().getServletContext()
			.getRealPath("/securitySystemData/templateFile");
		File sourceExcelReportTemplateFile = new File(sourceTemplateFileFolderPath,
				FileNameConstants.SOURCE_DEPARTMENT_STA_EXCEL_REPORT_TEMPLATE_FILE_NAME);
		
		String systemDataFolder = 
			XMLConfigReader.getInstance().getSystemConfig().getSystemDataFolder();
		String departmentStaExcelReportFolderPath = 
			systemDataFolder + "/" + FolderConstants.DEPARTMENT_STA_EXCEL_REPORT_FOLDER;
		String currExcelReportFolderPath = 
			departmentStaExcelReportFolderPath + "/" 
			+ "反恐统计报表_" + staPeriodInfo.getStaPeriodInfoId();
		
		staPeriodSecurityManager.processDepartmentStaExcelReport(
				staPeriodInfo,department,sourceExcelReportTemplateFile
				,currExcelReportFolderPath);
		//生成压缩文件
		String zipFileName = 
			departmentStaExcelReportFolderPath + "/" 
			+ "departmentStaExcelReportZipPack_" + staPeriodInfo.getStaPeriodInfoId()+".zip";
		ZipUtil.zipEntry(zipFileName, "", currExcelReportFolderPath);
		//删除临时统计报表，只留下zip压缩包，用于用户下载
		File currExcelReportFolderFile = new File(currExcelReportFolderPath);
		FileUtil.deleteFolder(currExcelReportFolderFile);

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_CUSTOM_STA_PERIOD_INFO_PATH);
		return mapping.findForward("showMessage");
	}

	public ActionForward listCustomStaPeriodInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User currUser = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Department department = currUser.getDepartment();
		long creatorDepartmentId = department.getDepartmentId();

		int staType = StaPeriodInfo.STA_TYPE_CUSTOM;
		String startDateString = null;
		String endDateString = null;
		
		PageModel<StaPeriodInfo> pageModel = 
			staPeriodInfoManager.listStaPeriodInfoInPage(
					staType,creatorDepartmentId,startDateString,endDateString);
		
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("department", department);
		
		return mapping.findForward("listCustomStaPeriodInfo");
	}

	public ActionForward listCustomSubStaPeriodSecurity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StaPeriodSecurityActionForm actionForm = (StaPeriodSecurityActionForm)form;
		
		long staPeriodInfoId = actionForm.getStaPeriodInfoId();
		long staParentDepartmentId = actionForm.getStaParentDepartmentId();
		
		StaPeriodInfo staPeriodInfo = 
			staPeriodInfoManager.loadStaPeriodInfoById(staPeriodInfoId);
		
		StaPeriodSecurity mainStaPeriodSecurity = 
			staPeriodSecurityManager.getStaPeriodSecurityByStaPeriodInfoIdStaDepartmentId(
					staPeriodInfoId, staParentDepartmentId);
		
		PageModel<StaPeriodSecurity> pageModel = 
			staPeriodSecurityManager.listSubStaPeriodSecurityInPage(
					staPeriodInfoId, staParentDepartmentId);
		
		request.setAttribute("staPeriodInfo", staPeriodInfo);
		request.setAttribute("mainStaPeriodSecurity", mainStaPeriodSecurity);	
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listCustomSubStaPeriodSecurity");
	}

	public ActionForward viewStaPeriodSecurity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StaPeriodSecurityActionForm actionForm = (StaPeriodSecurityActionForm)form;
		
		long staPeriodInfoId = actionForm.getStaPeriodInfoId();
		long staDepartmentId = actionForm.getStaDepartmentId();

		StaPeriodSecurity staPeriodSecurity = 
			staPeriodSecurityManager.getStaPeriodSecurityByStaPeriodInfoIdStaDepartmentId(
				staPeriodInfoId,staDepartmentId);
		StaPeriodInfo staPeriodInfo = staPeriodSecurity.getStaPeriodInfo();
		
		request.setAttribute("staPeriodSecurity", staPeriodSecurity);
		request.setAttribute("staPeriodInfo", staPeriodInfo);
		request.setAttribute("commonSta", staPeriodSecurity.getCommonSta());
		request.setAttribute("keyunSta", staPeriodSecurity.getKeyunSta());
		
		return mapping.findForward("viewStaPeriodSecurity");
	}
	
	public ActionForward deleteStaPeriodInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StaPeriodSecurityActionForm actionForm = (StaPeriodSecurityActionForm)form;
		long[] staPeriodInfoIdList = actionForm.getSelectFlag();
		
		String systemDataFolder = 
			XMLConfigReader.getInstance().getSystemConfig().getSystemDataFolder();
		String departmentStaExcelReportFolderPath = 
			systemDataFolder + "/" + FolderConstants.DEPARTMENT_STA_EXCEL_REPORT_FOLDER;

		
		for(long staPeriodInfoId:staPeriodInfoIdList){
			staPeriodSecurityManager.deleteStaPeriodSecurityAndInfoByStaPeriodInfoId(
					staPeriodInfoId);
			
			String zipFileName = 
				departmentStaExcelReportFolderPath + "/" 
				+ "departmentStaExcelReportZipPack_" + staPeriodInfoId +".zip";
			File zipFile = new File(zipFileName);
			zipFile.delete();
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_CUSTOM_STA_PERIOD_INFO_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward downloadDepartmentStaExcelReport(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StaPeriodSecurityActionForm actionForm = (StaPeriodSecurityActionForm)form;
		long staPeriodInfoId = actionForm.getStaPeriodInfoId();

		String systemDataFolder = 
			XMLConfigReader.getInstance().getSystemConfig().getSystemDataFolder();
		String departmentStaExcelReportFolderPath = 
			systemDataFolder + "/" + FolderConstants.DEPARTMENT_STA_EXCEL_REPORT_FOLDER;

		String zipFileName = 
			departmentStaExcelReportFolderPath + "/" 
			+ "departmentStaExcelReportZipPack_" + staPeriodInfoId +".zip";
		File sourceFile = new File(zipFileName);
		
		String fileName = "反恐统计报表_"+ staPeriodInfoId +".zip";
		
		DownloadUtil.downloadFileFromServer(response, sourceFile, fileName);
		return null;
	}
	
}
