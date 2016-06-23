package com.ldp.security.util.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.report.domain.DangerousObjectItem;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.domain.SpecialCodeItem;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 系统启动初始化数据
 * @author Administrator
 *
 */
public class InitSystemServlet implements Servlet {

	public void destroy() {

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig config) throws ServletException {

		ServletContext servletContext = config.getServletContext();
		
		List<DangerousObjectItem> dangerousObjectItemList = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemList();
		servletContext.setAttribute("dangerousObjectItemList", dangerousObjectItemList);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		servletContext.setAttribute("dangerousObjectItemCount",dangerousObjectItemCount);
		
		List<SpecialCodeItem> specialCodeItemList = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemList();
		servletContext.setAttribute("specialCodeItemList", specialCodeItemList);
		
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		servletContext.setAttribute("specialCodeItemCount",specialCodeItemCount);
		
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig().getSecurityMachineMaxCount();
		servletContext.setAttribute("securityMachineMaxCount", securityMachineMaxCount);

		servletContext.setAttribute("SecurityForm_STATE_NOT_CONFIRM"
				,SecurityForm.STATE_NOT_CONFIRM);
		
		servletContext.setAttribute("SecurityForm_STATE_IMPORT"
				,SecurityForm.STATE_IMPORT);

		servletContext.setAttribute("SecurityForm_STATE_CONFIRM"
				,SecurityForm.STATE_CONFIRM);
		
		servletContext.setAttribute("SecurityForm_STATE_VERIFY"
				, SecurityForm.STATE_VERIFY);
		
		servletContext.setAttribute("Department_LEVEL_DEPARTMENT"
				, Department.LEVEL_DEPARTMENT);

		servletContext.setAttribute("Department_LEVEL_STATION"
				, Department.LEVEL_STATION);
		
		servletContext.setAttribute("User_USER_AUTHORITY_TYPE_MANAGER"
				, User.USER_AUTHORITY_TYPE_MANAGER);
		
		servletContext.setAttribute("User_USER_AUTHORITY_TYPE_VIEWER"
				, User.USER_AUTHORITY_TYPE_VIEWER);
		
	}

	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {

	}

}
