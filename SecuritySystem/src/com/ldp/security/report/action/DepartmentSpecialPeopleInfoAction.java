package com.ldp.security.report.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.report.actionform.SpecialPeopleInfoActionForm;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.domain.SpecialPeopleInfo;
import com.ldp.security.report.manager.SecurityFormManager;
import com.ldp.security.report.manager.SpecialPeopleInfoManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.validate.ClientValidate;

public class DepartmentSpecialPeopleInfoAction extends DispatchAction {

	public static final String LIST_DEPARTMENT_NOT_CONFIRM_SPECIAL_PEOPLE_INFO_PATH = 
		"report/specialPeopleInfo/departmentSpecialPeopleInfoFunc.do?command=listDepartmentNotConfirmSpecialPeopleInfo";

	private SpecialPeopleInfoManager specialPeopleInfoManager;
	
	private DepartmentManager departmentManager;
	
	public void setSpecialPeopleInfoManager(
			SpecialPeopleInfoManager specialPeopleInfoManager) {
		this.specialPeopleInfoManager = specialPeopleInfoManager;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public ActionForward saveDepartmentSpecialPeopleInfoPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
				
		request.setAttribute("department", user.getDepartment());
		request.setAttribute("reportUser", user);
		
		return mapping.findForward("saveDepartmentSpecialPeopleInfoPrepare");
	}

	public ActionForward saveDepartmentSpecialPeopleInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Department department = user.getDepartment();
		Department station = departmentManager.loadDepartmentById(
				department.getParentDepartment().getDepartmentId());
		
		SpecialPeopleInfoActionForm actionForm = (SpecialPeopleInfoActionForm)form;
		
		String trainNumber = actionForm.getTrainNumber();
		trainNumber = trainNumber.toUpperCase();
		
		String startLocation = actionForm.getStartLocation();
		String destination = actionForm.getDestination();
		String buyTicketTimeString = actionForm.getBuyTicketTimeString();
		String trainStartTimeString = actionForm.getTrainStartTimeString();
		
		List<SpecialPeopleInfo> specialPeopleInfoList =
			new ArrayList<SpecialPeopleInfo>(); 
			
		for(int i=0;i<10;i++){
			
			String inputFlagVar = "inputFlag_"+i;
			String inputFlagValue = request.getParameter(inputFlagVar);
			if(!ClientValidate.isEmpty(inputFlagValue)){
				
				String passengerNameVar = "passengerName_"+i;
				String passengerIdentityVar = "passengerIdentity_"+i;
				String ticketNumberVar = "ticketNumber_"+i;
				String sitNumberVar = "sitNumber_"+i;
				
				String passengerNameValue = request.getParameter(passengerNameVar);
				String passengerIdentityValue = request.getParameter(passengerIdentityVar);
				String ticketNumberValue = request.getParameter(ticketNumberVar);
				String sitNumberValue = request.getParameter(sitNumberVar);
				
				SpecialPeopleInfo specialPeopleInfo = new SpecialPeopleInfo();
				specialPeopleInfo.setReportUser(user);
				specialPeopleInfo.setDepartment(department);
				specialPeopleInfo.setStation(station);
				
				specialPeopleInfo.setState(SpecialPeopleInfo.STATE_NOT_CONFRIM);
				specialPeopleInfo.setBuyTicketTimeString(buyTicketTimeString);
				
				Date buyTicketTime = DateUtil.parseTimeFromTimeString(buyTicketTimeString);
				String staDateString = DateUtil.formatDateToString(buyTicketTime);
				specialPeopleInfo.setStaDateString(staDateString);
				
				specialPeopleInfo.setStartLocation(startLocation);
				specialPeopleInfo.setDestination(destination);
				specialPeopleInfo.setTrainNumber(trainNumber);
				specialPeopleInfo.setTrainStartTimeString(trainStartTimeString);
				
				specialPeopleInfo.setPassengerName(passengerNameValue);
				specialPeopleInfo.setPassengerIdentity(passengerIdentityValue);
				specialPeopleInfo.setTicketNumber(ticketNumberValue);
				specialPeopleInfo.setSitNumber(sitNumberValue);
				
				specialPeopleInfo.setReportTimeString(DateUtil.getCurrentTimeString());
				specialPeopleInfoList.add(specialPeopleInfo);
			}				
		}
		
		for(SpecialPeopleInfo specialPeopleInfo:specialPeopleInfoList){
			specialPeopleInfoManager.saveSpecialPeopleInfo(specialPeopleInfo);			
		}
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_NOT_CONFIRM_SPECIAL_PEOPLE_INFO_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward listDepartmentNotConfirmSpecialPeopleInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long departmentId = user.getDepartment().getDepartmentId();
		
		PageModel<SpecialPeopleInfo> pageModel =
			specialPeopleInfoManager.listDepartmentSpecialPeopleInfo(
					departmentId,SpecialPeopleInfo.STATE_NOT_CONFRIM);

		request.setAttribute("department", user.getDepartment());
		request.setAttribute("reportUser", user);
		request.setAttribute("pageModel", pageModel);
		return mapping.findForward("listDepartmentNotConfirmSpecialPeopleInfo");
	}
}
