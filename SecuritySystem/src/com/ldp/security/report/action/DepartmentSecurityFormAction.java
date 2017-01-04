package com.ldp.security.report.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.DepartmentManager;
import com.ldp.security.common.action.BaseAction;
import com.ldp.security.report.actionform.SecurityFormActionForm;
import com.ldp.security.report.domain.CommonForm;
import com.ldp.security.report.domain.KeyunForm;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.manager.SecurityFormManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.validate.ClientValidate;
import com.ldp.security.util.xml.XMLConfigReader;

public class DepartmentSecurityFormAction extends BaseAction{
	
	private static final String LIST_DEPARTMENT_NOT_CONFIRM_SECURITY_FORM_PATH = 
		"report/securityForm/departmentSecurityFormFunc.do?command=listDepartmentNotConfirmSecurityForm";

	private DepartmentManager departmentManager;
	
	private SecurityFormManager securityFormManager;

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setSecurityFormManager(SecurityFormManager securityFormManager) {
		this.securityFormManager = securityFormManager;
	}

	public ActionForward saveDepartmentSecurityFormPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
				
		request.setAttribute("department", user.getDepartment());
		request.setAttribute("reportUser", user);
		
		return mapping.findForward("saveDepartmentSecurityFormPrepare");
	}

	public ActionForward saveDepartmentSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		actionForm.validateData();

		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);

		SecurityForm securityForm = new SecurityForm();
		securityForm.setDepartment(user.getDepartment());
		
		Department station = departmentManager.loadDepartmentById(
				user.getDepartment().getParentDepartment().getDepartmentId());
		securityForm.setStation(station);
		
		securityForm.setReportUser(user);
		securityForm.setState(SecurityForm.STATE_NOT_CONFIRM);
		
		long departmentId = user.getDepartment().getDepartmentId();
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		boolean isConflict = securityFormManager.checkConflictDateByDepartmentId(
				departmentId, startDateString, endDateString);
		if(isConflict){
			throw new RuntimeException("报表起始日期，报表结束日期与系统中的数据冲突，请重新输入");
		}
		
		securityForm.setStartDateString(startDateString);
		securityForm.setEndDateString(endDateString);
		
		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		if(lastDays>SecurityForm.MAX_LAST_DAYS){
			throw new RuntimeException(
					"报表持续天数最多只能是"+SecurityForm.MAX_LAST_DAYS+"天");
		}
		securityForm.setLastDays(lastDays);
		
		securityForm.setReportTimeString(DateUtil.getCurrentTimeString());
		
		CommonForm commonForm = new CommonForm();
		securityForm.setCommonForm(commonForm);
		
		List<Integer> securityMachineCheckNumList = new ArrayList<Integer>();
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig().getSecurityMachineMaxCount();
		for(int i=0;i<securityMachineMaxCount;i++){
			
			String securityMachineCheckNumVar = "securityMachineCheckNum_"+i;
			String numValueString = request.getParameter(securityMachineCheckNumVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			securityMachineCheckNumList.add(numValue);
		}
		commonForm.setSecurityMachineCheckNumList(securityMachineCheckNumList);
		
		commonForm.setSecurityMachineTroubleNum(actionForm.getSecurityMachineTroubleNum());
				
		List<Integer> checkDangerousObjectNumList = new ArrayList<Integer>();
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();			
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			String dangerousObjectNumVar = "dangerousObjectItemNum_"+i;
			String numValueString = request.getParameter(dangerousObjectNumVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			checkDangerousObjectNumList.add(numValue);
		}
		commonForm.setCheckDangerousObjectNumList(checkDangerousObjectNumList);
		
		commonForm.setZhanquCheckNum(actionForm.getZhanquCheckNum());
		commonForm.setCctvCheckNum(actionForm.getCctvCheckNum());
		commonForm.setCctvTroubleNum(actionForm.getCctvTroubleNum());
		commonForm.setEquipmentCheckNum(actionForm.getEquipmentCheckNum());
		commonForm.setEquipmentTroubleNum(actionForm.getEquipmentTroubleNum());
		commonForm.setMilitiamanCheckNum(actionForm.getMilitiamanCheckNum());
		
		commonForm.setTrainningCount(actionForm.getTrainningCount());
		commonForm.setTrainningPeopleNum(actionForm.getTrainningPeopleNum());
		
		commonForm.setPracticeCount(actionForm.getPracticeCount());
		commonForm.setPracticePeopleNum(actionForm.getPracticePeopleNum());
		
		commonForm.setOtherWorkInfo(actionForm.getOtherWorkInfo());
		
		KeyunForm keyunForm = new KeyunForm();
		securityForm.setKeyunForm(keyunForm);
		
		keyunForm.setSquareSpecialPeopleNum(actionForm.getSquareSpecialPeopleNum());
		keyunForm.setSquareCheckPeopleNum(actionForm.getSquareCheckPeopleNum());
		
		dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		List<Integer> squareDangerousObjectNumList = new ArrayList<Integer>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			String numVar = "squareDangerousObjectNum_"+i;
			String numValueString = request.getParameter(numVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			squareDangerousObjectNumList.add(numValue);
		}
		keyunForm.setSquareDangerousObjectNumList(squareDangerousObjectNumList);
		
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		List<Integer> specialCodePeopleNumList = new ArrayList<Integer>();
		
		for(int i=0;i<specialCodeItemCount;i++){
			
			String numVar = "specialCodePeopleNum_"+i;
			String numValueString = request.getParameter(numVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			specialCodePeopleNumList.add(numValue);
			
		}
		keyunForm.setSpecialCodePeopleNumList(specialCodePeopleNumList);
		
		keyunForm.setYanzhengUsingFakePaperNum(actionForm.getYanzhengUsingFakePaperNum());
		keyunForm.setArrestPeopleNum(actionForm.getArrestPeopleNum());
		keyunForm.setFinePeopleNum(actionForm.getFinePeopleNum());
		keyunForm.setStudyPeopleNum(actionForm.getStudyPeopleNum());
		keyunForm.setSaleUsingFakePaperNum(actionForm.getSaleUsingFakePaperNum());
		keyunForm.setSaleSpecialPeopleNum(actionForm.getSaleSpecialPeopleNum());
		keyunForm.setPeriodNumberOfPassengers(actionForm.getPeriodNumberOfPassengers());
		keyunForm.setWaitingHallCheckPeopleNum(actionForm.getWaitingHallCheckPeopleNum());
		keyunForm.setWaitingHallXianzaPeopleNum(actionForm.getWaitingHallXianzaPeopleNum());
		keyunForm.setJianpiaoWeisuiPeopleNum(actionForm.getJianpiaoWeisuiPeopleNum());
		keyunForm.setSpecialTrainIdentityWrongPeopleNum(
				actionForm.getSpecialTrainIdentityWrongPeopleNum());
		keyunForm.setSpecialTrainIdentityWrongInfo(
				actionForm.getSpecialTrainIdentityWrongInfo());
		
		securityFormManager.saveSecurityForm(securityForm);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_SAVE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_NOT_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward listDepartmentNotConfirmSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long departmentId = user.getDepartment().getDepartmentId();
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		
		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(
					null, departmentId, startDateString, endDateString
					, SecurityForm.STATE_NOT_CONFIRM);

		request.setAttribute("department", user.getDepartment());
		request.setAttribute("reportUser", user);
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listDepartmentNotConfirmSecurityForm");
	}

	public ActionForward deleteDepartmentSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();
		
		for(long securityFormId:securityFormIdList){			
			securityFormManager.deleteSecurityFormByEraseDataById(securityFormId);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_NOT_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward listDepartmentConfirmSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long departmentId = user.getDepartment().getDepartmentId();

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		
		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(
					null, departmentId, startDateString, endDateString
					, SecurityForm.STATE_CONFIRM);

		request.setAttribute("department", user.getDepartment());
		request.setAttribute("reportUser", user);
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listDepartmentConfirmSecurityForm");
	}

	public ActionForward listDepartmentVerifySecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long departmentId = user.getDepartment().getDepartmentId();

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		
		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(
					null, departmentId, startDateString, endDateString
					, SecurityForm.STATE_VERIFY);

		request.setAttribute("department", user.getDepartment());
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listDepartmentVerifySecurityForm");
	}

	public ActionForward confirmDepartmentSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){	
			securityFormManager.updateSecurityFormStateById(securityFormId
					, SecurityForm.STATE_NOT_CONFIRM, SecurityForm.STATE_CONFIRM);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_NOT_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward updateDepartmentSecurityFormPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long securityFormId = actionForm.getSecurityFormId();
		
		SecurityForm securityForm = securityFormManager.loadSecurityFormById(securityFormId);
		
		request.setAttribute("securityForm", securityForm);
		request.setAttribute("commonForm", securityForm.getCommonForm());
		request.setAttribute("keyunForm", securityForm.getKeyunForm());
		request.setAttribute("department", securityForm.getDepartment());
		request.setAttribute("reportUser", securityForm.getReportUser());
		
		return mapping.findForward("updateDepartmentSecurityFormPrepare");
	}

	public ActionForward updateDepartmentSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		actionForm.validateData();
		long securityFormId = actionForm.getSecurityFormId();

		SecurityForm securityForm = 
			securityFormManager.loadSecurityFormById(securityFormId);
		
		long departmentId = securityForm.getDepartment().getDepartmentId();
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		boolean isConflict = 
			securityFormManager.checkConflictDateWithoutMyselfByDepartmentId(
				securityFormId,departmentId, startDateString, endDateString);
		if(isConflict){
			throw new RuntimeException("报表起始日期，报表结束日期与系统中的数据冲突，请重新输入");
		}
		securityForm.setStartDateString(startDateString);
		securityForm.setEndDateString(endDateString);
		
		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		if(lastDays>SecurityForm.MAX_LAST_DAYS){
			throw new RuntimeException(
					"统计天数最多只能是"+SecurityForm.MAX_LAST_DAYS+"天");
		}
		securityForm.setLastDays(lastDays);
		
		CommonForm commonForm = securityForm.getCommonForm();
		
		List<Integer> securityMachineCheckNumList = new ArrayList<Integer>();
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig().getSecurityMachineMaxCount();
		for(int i=0;i<securityMachineMaxCount;i++){
			
			String securityMachineCheckNumVar = "securityMachineCheckNum_"+i;
			String numValueString = request.getParameter(securityMachineCheckNumVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			securityMachineCheckNumList.add(numValue);
		}
		commonForm.setSecurityMachineCheckNumList(securityMachineCheckNumList);
		
		commonForm.setSecurityMachineTroubleNum(actionForm.getSecurityMachineTroubleNum());
				
		List<Integer> checkDangerousObjectNumList = new ArrayList<Integer>();
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();			
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			String dangerousObjectNumVar = "dangerousObjectItemNum_"+i;
			String numValueString = request.getParameter(dangerousObjectNumVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			checkDangerousObjectNumList.add(numValue);
		}
		commonForm.setCheckDangerousObjectNumList(checkDangerousObjectNumList);
		
		commonForm.setZhanquCheckNum(actionForm.getZhanquCheckNum());
		commonForm.setCctvCheckNum(actionForm.getCctvCheckNum());
		commonForm.setCctvTroubleNum(actionForm.getCctvTroubleNum());
		commonForm.setEquipmentCheckNum(actionForm.getEquipmentCheckNum());
		commonForm.setEquipmentTroubleNum(actionForm.getEquipmentTroubleNum());
		commonForm.setMilitiamanCheckNum(actionForm.getMilitiamanCheckNum());
		
		commonForm.setTrainningCount(actionForm.getTrainningCount());
		commonForm.setTrainningPeopleNum(actionForm.getTrainningPeopleNum());
		
		commonForm.setPracticeCount(actionForm.getPracticeCount());
		commonForm.setPracticePeopleNum(actionForm.getPracticePeopleNum());
		commonForm.setOtherWorkInfo(actionForm.getOtherWorkInfo());
		
		KeyunForm keyunForm = securityForm.getKeyunForm();
		
		keyunForm.setSquareSpecialPeopleNum(actionForm.getSquareSpecialPeopleNum());
		keyunForm.setSquareCheckPeopleNum(actionForm.getSquareCheckPeopleNum());
		
		dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		List<Integer> squareDangerousObjectNumList = new ArrayList<Integer>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			String numVar = "squareDangerousObjectNum_"+i;
			String numValueString = request.getParameter(numVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			squareDangerousObjectNumList.add(numValue);
		}
		keyunForm.setSquareDangerousObjectNumList(squareDangerousObjectNumList);
		
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		List<Integer> specialCodePeopleNumList = new ArrayList<Integer>();
		
		for(int i=0;i<specialCodeItemCount;i++){
			
			String numVar = "specialCodePeopleNum_"+i;
			String numValueString = request.getParameter(numVar);
			if(ClientValidate.isEmpty(numValueString)){
				numValueString = "0";
			}
			Integer numValue = Integer.parseInt(numValueString);
			specialCodePeopleNumList.add(numValue);
		}
		keyunForm.setSpecialCodePeopleNumList(specialCodePeopleNumList);
		
		keyunForm.setYanzhengUsingFakePaperNum(actionForm.getYanzhengUsingFakePaperNum());
		keyunForm.setArrestPeopleNum(actionForm.getArrestPeopleNum());
		keyunForm.setFinePeopleNum(actionForm.getFinePeopleNum());
		keyunForm.setStudyPeopleNum(actionForm.getStudyPeopleNum());
		keyunForm.setSaleUsingFakePaperNum(actionForm.getSaleUsingFakePaperNum());
		keyunForm.setSaleSpecialPeopleNum(actionForm.getSaleSpecialPeopleNum());
		keyunForm.setPeriodNumberOfPassengers(actionForm.getPeriodNumberOfPassengers());
		keyunForm.setWaitingHallCheckPeopleNum(actionForm.getWaitingHallCheckPeopleNum());
		keyunForm.setWaitingHallXianzaPeopleNum(actionForm.getWaitingHallXianzaPeopleNum());
		keyunForm.setJianpiaoWeisuiPeopleNum(actionForm.getJianpiaoWeisuiPeopleNum());
		keyunForm.setSpecialTrainIdentityWrongPeopleNum(
				actionForm.getSpecialTrainIdentityWrongPeopleNum());
		keyunForm.setSpecialTrainIdentityWrongInfo(
				actionForm.getSpecialTrainIdentityWrongInfo());
		
		securityFormManager.updateSecurityForm(securityForm);
		
		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_DEPARTMENT_NOT_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward viewDepartmentSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long securityFormId = actionForm.getSecurityFormId();
		
		SecurityForm securityForm = securityFormManager.loadSecurityFormById(securityFormId);
		
		request.setAttribute("securityForm", securityForm);
		request.setAttribute("commonForm", securityForm.getCommonForm());
		request.setAttribute("keyunForm", securityForm.getKeyunForm());
		
		return mapping.findForward("viewDepartmentSecurityForm");
	}
	
}
