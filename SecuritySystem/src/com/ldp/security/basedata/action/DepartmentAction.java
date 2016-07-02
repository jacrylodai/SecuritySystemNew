package com.ldp.security.basedata.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.actionform.DepartmentActionForm;
import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.basedata.manager.RoleManager;
import com.ldp.security.basedata.manager.UserManager;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.manager.SecurityFormManager;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.sta.manager.StaPeriodInfoManager;
import com.ldp.security.sta.manager.StaPeriodSecurityManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.compress.ZipUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FileNameConstants;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.file.DownloadUtil;
import com.ldp.security.util.file.FileUtil;

public class DepartmentAction extends BaseAction {

	private static final String LIST_DEPARTMENT_PATH = 
		"basedata/department/departmentFunc.do?command=listDepartment";
	
	private DepartmentManager departmentManager;
	
	private StaPeriodInfoManager staPeriodInfoManager;
	
	private StaPeriodSecurityManager staPeriodSecurityManager;
	
	private SecurityFormManager securityFormManager;
	
	private UserManager userManager;
	
	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setStaPeriodInfoManager(StaPeriodInfoManager staPeriodInfoManager) {
		this.staPeriodInfoManager = staPeriodInfoManager;
	}

	public void setStaPeriodSecurityManager(
			StaPeriodSecurityManager staPeriodSecurityManager) {
		this.staPeriodSecurityManager = staPeriodSecurityManager;
	}

	public void setSecurityFormManager(SecurityFormManager securityFormManager) {
		this.securityFormManager = securityFormManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * 转到添加部门页面，传递的参数为parentId
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDepartmentPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		Long parentId = departmentActionForm.getParentId();
				
		Department parentDepartment = departmentManager.loadDepartmentById(parentId);
		
		if(!userDepartment.equals(parentDepartment) &&
				!departmentManager.compareDepartmentIsParentOfDepartment(
						userDepartment, parentDepartment)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		request.setAttribute("parentDepartment", parentDepartment);	
		return mapping.findForward("saveDepartmentPrepare");
	}

	public ActionForward saveDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		Long parentId = departmentActionForm.getParentId();
				
		Department parentDepartment = departmentManager.loadDepartmentById(parentId);
		
		if(!userDepartment.equals(parentDepartment) &&
				!departmentManager.compareDepartmentIsParentOfDepartment(
						userDepartment, parentDepartment)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		departmentActionForm.validateData();
		
		String departmentName = departmentActionForm.getDepartmentName();
				
		Department department = new Department();
		department.setParentDepartment(parentDepartment);
		department.setLevel(parentDepartment.getLevel()+1);
		department.setIsDelete(Constants.VALUE_NO);
		department.setDepartmentName(departmentName);
		
		departmentManager.saveDepartment(department);
		
		createDepartmentExcelTemplate(request, department);

		createWholeYearDepartmentExcelTemplate(request, department);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		
		String redirectUrl = LIST_DEPARTMENT_PATH + "&parentId=" + parentId;
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}

	private void createWholeYearDepartmentExcelTemplate(
			HttpServletRequest request, Department department) {

		//如果是车间，就创建车间的反恐填报表模板
		if(department.getLevel() == Department.LEVEL_DEPARTMENT){

			String sourceTemplateFileFolderPath = 
				request.getSession().getServletContext()
					.getRealPath("/securitySystemData/templateFile");
			File sourceTemplateFile = new File(sourceTemplateFileFolderPath,
					FileNameConstants.SOURCE_DEPARTMENT_TEMPLATE_FILE_NAME);
			
			long departmentId = department.getDepartmentId();
			String wholeYearExcelTemplateFolderPath = 
				departmentManager.getWholeYearExcelTemplateFolderPath(departmentId);
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
			
			departmentManager.createWholeYearDepartmentExcelTemplate(
					department,currDepartmentWholeYearFolderPath,sourceTemplateFile);
			
			ZipUtil.zipEntry(currDepartmentWholeYearZipFilePath, ""
					, currDepartmentWholeYearFolderPath);
			FileUtil.deleteFolder(new File(currDepartmentWholeYearFolderPath));
			
		}
	}

	public ActionForward listDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();
		int level = user.getDepartment().getLevel();

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		Long parentId = departmentActionForm.getParentId();
		
		if(parentId == null){
			
			if(level == Department.LEVEL_DEPARTMENT){
				throw new RuntimeException("车间用户不能查看部门列表");
			}
			
			parentId = userDepartment.getDepartmentId();
		}else{
			
			Department parentDepartment = departmentManager.loadDepartmentById(parentId);
			
			if(!userDepartment.equals(parentDepartment) &&
					!departmentManager.compareDepartmentIsParentOfDepartment(
							userDepartment, parentDepartment)){
				throw new RuntimeException("你没有权限访问当前部门数据");
			}
		}		
		
		Department parentDepartment = departmentManager.loadDepartmentById(parentId);
		
		PageModel<Department> pageModel = 
			departmentManager.listDepartmentInPage(parentId);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("parentDepartment", parentDepartment);
		
		return mapping.findForward("listDepartment");
		
	}

	public ActionForward updateDepartmentPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		long departmentId = departmentActionForm.getDepartmentId();
		
		Department department = departmentManager.loadDepartmentById(departmentId);				
		Department parentDepartment = department.getParentDepartment();
		
		if(!userDepartment.equals(parentDepartment) &&
				!departmentManager.compareDepartmentIsParentOfDepartment(
						userDepartment, parentDepartment)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		request.setAttribute("department", department);
		
		return mapping.findForward("updateDepartmentPrepare");
	}

	public ActionForward updateDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		long departmentId = departmentActionForm.getDepartmentId();
		
		Department department = departmentManager.loadDepartmentById(departmentId);				
		Department parentDepartment = department.getParentDepartment();
		
		if(!userDepartment.equals(parentDepartment) &&
				!departmentManager.compareDepartmentIsParentOfDepartment(
						userDepartment, parentDepartment)){
			throw new RuntimeException("你没有权限访问当前部门数据");
		}
		
		departmentActionForm.validateData();
		
		String departmentName = departmentActionForm.getDepartmentName();
		department.setDepartmentName(departmentName);
		
		departmentManager.updateDepartment(department);
		
		createDepartmentExcelTemplate(request,department);

		createWholeYearDepartmentExcelTemplate(request, department);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);

		String redirectUrl = LIST_DEPARTMENT_PATH + "&parentId=" 
			+ department.getParentDepartment().getDepartmentId();
		request.setAttribute(Constants.REDIRECT_URL_KEY, redirectUrl);
		
		return mapping.findForward("showMessage");
	}

	/**
	 * 创建部门的反恐报表模板，只有车间才能创建，在添加和修改时都需要创建
	 * @param request
	 * @param department
	 */
	private void createDepartmentExcelTemplate(HttpServletRequest request,
			Department department) {
		
		//如果是车间，就创建车间的反恐填报表模板
		if(department.getLevel() == Department.LEVEL_DEPARTMENT){

			String sourceTemplateFileFolderPath = 
				request.getSession().getServletContext()
					.getRealPath("/securitySystemData/templateFile");
			File sourceTemplateFile = new File(sourceTemplateFileFolderPath,
					FileNameConstants.SOURCE_DEPARTMENT_TEMPLATE_FILE_NAME);
			
			long departmentId = department.getDepartmentId();
			String excelTemplateFilePath = 
				departmentManager.getExcelTemplateFilePath(departmentId);
			
			File excelTemplateFile = new File(excelTemplateFilePath);
			
			File excelTemplateFolder = excelTemplateFile.getParentFile();
			excelTemplateFolder.mkdirs();
			
			excelTemplateFile.delete();
			
			departmentManager.createDepartmentExcelTemplate(
					department,sourceTemplateFile,excelTemplateFile);
		
		}
	}

	/**
	 * 下载部门反恐报表模板
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadDepartmentExcelTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		long departmentId = departmentActionForm.getDepartmentId();
		
		Department department = departmentManager.loadDepartmentById(departmentId);
		if(department.getLevel()!= Department.LEVEL_DEPARTMENT){
			throw new RuntimeException("只有车间才能下载反恐填报表模板");
		}
		
		//得到excel文件存放目录
		String excelTemplateFilePath = 
			departmentManager.getExcelTemplateFilePath(departmentId);
		
		// 创建file对象
		File sourceFile = new File(excelTemplateFilePath);
		String fileName = 
			department.getDepartmentName() + 
			'_' +"反恐填报表模板" +'_' + departmentId + ".xls";

		DownloadUtil.downloadFileFromServer(response, sourceFile, fileName);
		return null;
	}

	/**
	 * 下载整年反恐报表模板
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadWholeYearDepartmentExcelTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		long departmentId = departmentActionForm.getDepartmentId();
		
		Department department = departmentManager.loadDepartmentById(departmentId);
		if(department.getLevel()!= Department.LEVEL_DEPARTMENT){
			throw new RuntimeException("只有车间才能下载反恐填报表模板");
		}
		
		String wholeYearExcelTemplateFolderPath = 
			departmentManager.getWholeYearExcelTemplateFolderPath(departmentId);

		String currDepartmentWholeYearZipFilePath = 
			wholeYearExcelTemplateFolderPath + '/'
			+ "wholeYearExcelTemplateZipFile_" + department.getDepartmentId() +".zip";
		File currDepartmentWholeYearZipFile = new File(currDepartmentWholeYearZipFilePath);
		
		String fileName = 
			department.getDepartmentName() +"_整年_反恐报表模板.zip";

		DownloadUtil.downloadFileFromServer(response, currDepartmentWholeYearZipFile
				, fileName);
		return null;
	}
	
	/**
	 * 删除部门
	 * 部门删除时，可以批量删除，如果有子部门数据，无法删除
	 * ，删除时并会删除部门关联的所有用户，会删除部门反恐填报表模板
	 * ，再删除部门关联的所有反恐报表，以及统计报表，删除时修改删除标志
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department userDepartment = user.getDepartment();
		
		DepartmentActionForm departmentActionForm = (DepartmentActionForm)form;
		long[] departmentIdArr = departmentActionForm.getSelectFlag();
		
		for(long departmentId:departmentIdArr){
			
			Department department = departmentManager.loadDepartmentById(departmentId);
			if(!departmentManager.compareDepartmentIsParentOfDepartment(
					userDepartment, department)){
				throw new RuntimeException("你没有权限删除当前的部门");
			}
			
			List<Department> subDepartmentList = 
				departmentManager.getSubDepartmentListById(departmentId);
			if(subDepartmentList.size()>0){
				throw new RuntimeException("无法删除，当前的部门下面有子部门，请先删除子部门");
			}
			
			//开始删除
			
			//删除统计周期类数据
			List<StaPeriodInfo> staPeriodInfoList = 
				staPeriodInfoManager.getStaPeriodInfoListByCreatorDepartmentId(departmentId);
			for(StaPeriodInfo staPeriodInfo:staPeriodInfoList){
				staPeriodSecurityManager.deleteStaPeriodSecurityAndInfoByStaPeriodInfoId(
						staPeriodInfo.getStaPeriodInfoId());
			}
			
			//删除反恐统计类数据
			List<StaPeriodSecurity> staPeriodSecurityList = 
				staPeriodSecurityManager.getStaPeriodSecurityListByStaDepartmentId(
						departmentId);
			for(StaPeriodSecurity staPeriodSecurity:staPeriodSecurityList){
				staPeriodSecurityManager.deleteStaPeriodSecurity(staPeriodSecurity);
			}
						
			//删除反恐填报表数据
			List<SecurityForm> securityFormList = 
				securityFormManager.getSecurityFormListByParam(
						null, departmentId, null, null, null);
			for(SecurityForm securityForm:securityFormList){
				securityFormManager.deleteSecurityFormByEraseDataById(
						securityForm.getSecurityFormId());
			}
			
			//删除用户
			List<User> userList = 
				userManager.getUserListByDepartmentId(departmentId);
			for(User tempUser:userList){
				userManager.deleteUserById(tempUser.getUserId());
			}
			
			//如果是车间，还要删除反恐报表模板
			if(department.getLevel() == Department.LEVEL_DEPARTMENT){

				//得到excel文件存放目录
				String excelTemplateFilePath = 
					departmentManager.getExcelTemplateFilePath(departmentId);
				
				// 创建file对象
				File excelTemplateFile = new File(excelTemplateFilePath);
				excelTemplateFile.delete();
			}
			
			//如果是车间，还要删除整年反恐统计模板
			if(department.getLevel() == Department.LEVEL_DEPARTMENT){
				
				String wholeYearExcelTemplateFolderPath = 
					departmentManager.getWholeYearExcelTemplateFolderPath(departmentId);

				String currDepartmentWholeYearZipFilePath = 
					wholeYearExcelTemplateFolderPath + '/'
					+ "wholeYearExcelTemplateZipFile_" + department.getDepartmentId() +".zip";
				File currDepartmentWholeYearZipFile = new File(currDepartmentWholeYearZipFilePath);
				if(currDepartmentWholeYearZipFile.exists()){
					currDepartmentWholeYearZipFile.delete();
				}
			}
			
			//删除部门
			departmentManager.deleteDepartmentById(departmentId);
		}
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_PATH);
		
		return mapping.findForward("showMessage");
	}
	
}
