package com.ldp.security.system.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FileNameConstants;

public class SystemConfigAction extends BaseAction{

	private static final String VIEW_SYSTEM_CONFIG_PATH = 
		"system/systemConfig/systemConfigFunc.do?command=viewSystemConfig";
	
	private DepartmentManager departmentManager;
	
	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public ActionForward viewSystemConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("viewSystemConfig");
	}

	public ActionForward regenerateDepartmentExcelTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Department> departmentLisit = 
			departmentManager.getAllLevelDepartmentDepartmentList();
		
		String sourceTemplateFileFolderPath = 
			request.getSession().getServletContext()
				.getRealPath("/securitySystemData/templateFile");
		File sourceTemplateFile = new File(sourceTemplateFileFolderPath,
				FileNameConstants.SOURCE_DEPARTMENT_TEMPLATE_FILE_NAME);
				
		for(Department department:departmentLisit){

			departmentManager.createDepartmentExcelTemplate(
					department,sourceTemplateFile);
			
			departmentManager.createWholeYearDepartmentExcelTemplate(
					department,sourceTemplateFile);
		}
		
		request.setAttribute(Constants.MESSAGE_KEY, "成功生成反恐报表模板文件");
		request.setAttribute(Constants.REDIRECT_URL_KEY, VIEW_SYSTEM_CONFIG_PATH);
		return mapping.findForward("showMessage");
	}
	
	
}
