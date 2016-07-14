package com.ldp.security.report.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import com.ldp.security.util.compress.ZipUtil;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.constants.FolderConstants;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.file.FileNameUtil;
import com.ldp.security.util.file.FileUtil;
import com.ldp.security.util.validate.ClientValidate;
import com.ldp.security.util.xml.XMLConfigReader;

public class StationSecurityFormAction extends BaseAction{
	
	private static final Logger logger = Logger.getLogger(StationSecurityFormAction.class);

	private static final String LIST_STATION_NOT_CONFIRM_SECURITY_FORM_PATH = 
		"report/securityForm/stationSecurityFormFunc.do?command=listStationNotConfirmSecurityForm&startDateString=&endDateString=&departmentId=-1";

	private static final String LIST_STATION_CONFIRM_SECURITY_FORM_PATH = 
		"report/securityForm/stationSecurityFormFunc.do?command=listStationConfirmSecurityForm&startDateString=&endDateString=&departmentId=-1";

	private static final String LIST_STATION_VERIFY_SECURITY_FORM_PATH = 
		"report/securityForm/stationSecurityFormFunc.do?command=listStationVerifySecurityForm&startDateString=&endDateString=&departmentId=-1";

	private static final String LIST_STATION_IMPORT_SECURITY_FORM_PATH = 
		"report/securityForm/stationSecurityFormFunc.do?command=listStationImportSecurityForm&startDateString=&endDateString=&departmentId=-1";

	private DepartmentManager departmentManager;
	
	private SecurityFormManager securityFormManager;

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setSecurityFormManager(SecurityFormManager securityFormManager) {
		this.securityFormManager = securityFormManager;
	}

	public ActionForward listStationNotConfirmSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Long stationId = user.getDepartment().getDepartmentId();
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		Long departmentId = actionForm.getDepartmentId();

		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(stationId
					,departmentId,startDateString,endDateString
					,SecurityForm.STATE_NOT_CONFIRM);

		List<Department> subDepartmentList = 
			departmentManager.getSubDepartmentListById(stationId);
		
		request.setAttribute("station", user.getDepartment());
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("subDepartmentList", subDepartmentList);
		
		return mapping.findForward("listStationNotConfirmSecurityForm");
	}
	
	public ActionForward listStationConfirmSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Long stationId = user.getDepartment().getDepartmentId();
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		Long departmentId = actionForm.getDepartmentId();

		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(stationId
					,departmentId,startDateString,endDateString
					,SecurityForm.STATE_CONFIRM);

		List<Department> subDepartmentList = 
			departmentManager.getSubDepartmentListById(stationId);
		
		request.setAttribute("station", user.getDepartment());
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("subDepartmentList", subDepartmentList);
		
		return mapping.findForward("listStationConfirmSecurityForm");
	}
	
	public ActionForward listStationVerifySecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		Long stationId = user.getDepartment().getDepartmentId();
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		
		String startDateString = actionForm.getStartDateString();
		String endDateString = actionForm.getEndDateString();
		Long departmentId = actionForm.getDepartmentId();

		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(stationId
					,departmentId,startDateString,endDateString
					,SecurityForm.STATE_VERIFY);

		List<Department> subDepartmentList = 
			departmentManager.getSubDepartmentListById(stationId);
		
		request.setAttribute("station", user.getDepartment());
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("subDepartmentList", subDepartmentList);
		
		return mapping.findForward("listStationVerifySecurityForm");
	}

	public ActionForward verifyStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){	
			securityFormManager.updateSecurityFormStateById(securityFormId
					, SecurityForm.STATE_CONFIRM, SecurityForm.STATE_VERIFY);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward confirmStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){	
			securityFormManager.updateSecurityFormStateById(securityFormId
					, SecurityForm.STATE_NOT_CONFIRM, SecurityForm.STATE_CONFIRM);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_NOT_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}
	
	public ActionForward cancelVerifyStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){	
			securityFormManager.updateSecurityFormStateById(securityFormId
					, SecurityForm.STATE_VERIFY, SecurityForm.STATE_CONFIRM);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_VERIFY_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward cancelConfirmStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){	
			securityFormManager.updateSecurityFormStateById(securityFormId
					, SecurityForm.STATE_CONFIRM, SecurityForm.STATE_NOT_CONFIRM);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_CONFIRM_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward updateStationSecurityFormPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long securityFormId = actionForm.getSecurityFormId();
		
		SecurityForm securityForm = securityFormManager.loadSecurityFormById(securityFormId);
		
		request.setAttribute("securityForm", securityForm);
		request.setAttribute("department", securityForm.getDepartment());
		request.setAttribute("reportUser", securityForm.getReportUser());
		
		request.setAttribute("commonForm", securityForm.getCommonForm());
		request.setAttribute("keyunForm", securityForm.getKeyunForm());
		
		return mapping.findForward("updateStationSecurityFormPrepare");
	}

	public ActionForward updateStationSecurityForm(ActionMapping mapping, ActionForm form,
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
		commonForm.setTrainningPeopleNum(actionForm.getTrainningPeopleNum());
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
		
		Integer listState = actionForm.getListState();
		switch(listState){

		case SecurityForm.STATE_NOT_CONFIRM:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_NOT_CONFIRM_SECURITY_FORM_PATH);
			break;
			
		case SecurityForm.STATE_CONFIRM:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_CONFIRM_SECURITY_FORM_PATH);
			break;
			
		case SecurityForm.STATE_IMPORT:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_IMPORT_SECURITY_FORM_PATH);
			break;

		case SecurityForm.STATE_VERIFY:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_VERIFY_SECURITY_FORM_PATH);
			break;
			
		default:
			throw new RuntimeException("listState是错误的值");
		}	
		
		return mapping.findForward("showMessage");
	}

	public ActionForward viewStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long securityFormId = actionForm.getSecurityFormId();
		
		SecurityForm securityForm = securityFormManager.loadSecurityFormById(securityFormId);
		
		request.setAttribute("securityForm", securityForm);
		request.setAttribute("commonForm", securityForm.getCommonForm());
		request.setAttribute("keyunForm", securityForm.getKeyunForm());
		
		return mapping.findForward("viewStationSecurityForm");
	}

	public ActionForward deleteStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();
		
		for(long securityFormId:securityFormIdList){			
			securityFormManager.deleteSecurityFormByEraseDataById(securityFormId);
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_DELETE_SUCCESS);

		Integer listState = actionForm.getListState();
		switch(listState){

		case SecurityForm.STATE_NOT_CONFIRM:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_NOT_CONFIRM_SECURITY_FORM_PATH);
			break;
			
		case SecurityForm.STATE_CONFIRM:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_CONFIRM_SECURITY_FORM_PATH);
			break;
			
		case SecurityForm.STATE_IMPORT:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_IMPORT_SECURITY_FORM_PATH);
			break;

		case SecurityForm.STATE_VERIFY:
			request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_VERIFY_SECURITY_FORM_PATH);
			break;
			
		default:
			throw new RuntimeException("listState是错误的值");
		}
		
		return mapping.findForward("showMessage");
	}

	public ActionForward listStationImportSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long stationId = user.getDepartment().getDepartmentId();

		PageModel<SecurityForm> pageModel = 
			securityFormManager.listSecurityFormByParam(
					stationId, null, null, null, SecurityForm.STATE_IMPORT);
		
		List<SecurityForm> securityFormList = pageModel.getData();
		
		for(SecurityForm securityForm:securityFormList){
			
			try{
				securityForm.validateData();
				
				long departmentId = securityForm.getDepartment().getDepartmentId();
				
				boolean isConflict = securityFormManager.checkConflictDateByDepartmentId(
						departmentId, securityForm.getStartDateString()
						, securityForm.getEndDateString());
				if(isConflict){
					throw new RuntimeException("无法上报，报表起始日期" +
							"，报表结束日期与系统中的数据冲突，请核对修改后再重新上报");
				}
			}catch(RuntimeException e){
				
				String errorMessage = e.getMessage();
				securityForm.setErrorMessage(errorMessage);
			}
		}
		
		request.setAttribute("station", user.getDepartment());
		request.setAttribute("pageModel", pageModel);
		
		return mapping.findForward("listStationImportSecurityForm");
	}

	public ActionForward verifyImportStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		long[] securityFormIdList = actionForm.getSelectFlag();

		for(long securityFormId:securityFormIdList){
			
			try{
				SecurityForm securityForm = 
					securityFormManager.loadSecurityFormById(securityFormId);
				securityForm.validateData();
				
				long departmentId = securityForm.getDepartment().getDepartmentId();
				
				boolean isConflict = securityFormManager.checkConflictDateByDepartmentId(
						departmentId, securityForm.getStartDateString()
						, securityForm.getEndDateString());
				if(isConflict){
					throw new RuntimeException("无法上报，报表起始日期" +
							"，报表结束日期与系统中的数据冲突，请核对修改后再重新上报");
				}
				
				securityFormManager.updateSecurityFormStateById(securityFormId
						, SecurityForm.STATE_IMPORT, SecurityForm.STATE_VERIFY);
			}catch(RuntimeException ex){
				logger.error("", ex);
			}
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_IMPORT_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}

	public ActionForward verifyImportAllStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(User.USER_SESSION_ID);
		long stationId = user.getDepartment().getDepartmentId();

		List<SecurityForm> securityFormList = 
			securityFormManager.getSecurityFormListByParam(
					stationId, null, null, null, SecurityForm.STATE_IMPORT);

		for(SecurityForm securityForm:securityFormList){
			
			try{				
				securityForm.validateData();
				
				long departmentId = securityForm.getDepartment().getDepartmentId();
				
				boolean isConflict = securityFormManager.checkConflictDateByDepartmentId(
						departmentId, securityForm.getStartDateString()
						, securityForm.getEndDateString());
				if(isConflict){
					throw new RuntimeException("无法上报，报表起始日期" +
							"，报表结束日期与系统中的数据冲突，请核对修改后再重新上报");
				}
				
				securityFormManager.updateSecurityFormStateById(
						securityForm.getSecurityFormId()
						, SecurityForm.STATE_IMPORT, SecurityForm.STATE_VERIFY);
			}catch(RuntimeException ex){
				logger.error("", ex);
			}
		}

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPDATE_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_IMPORT_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}
	
	public ActionForward uploadStationSecurityFormPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department department = user.getDepartment();
		
		request.setAttribute("reportUser", user);
		request.setAttribute("department", department);
		
		return mapping.findForward("uploadStationSecurityFormPrepare");
	}

	
	public ActionForward uploadStationSecurityForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = (User)request.getSession().getAttribute(User.USER_SESSION_ID);
		Department department = user.getDepartment();
		
		SecurityFormActionForm actionForm = (SecurityFormActionForm)form;
		FormFile securityFormFile = actionForm.getSecurityFormFile();
		
		String fileName = securityFormFile.getFileName();
		String suffix = FileNameUtil.getFileNameSuffix(fileName);
		if( !suffix.equals("xls") && !suffix.equals("zip")){
			throw new RuntimeException(
					"只能上传指定的文件格式，限定xls-电子表格文件，zip-压缩文件 格式");
		}
		
		int fileSize = securityFormFile.getFileSize();
		int maxFileSize = 10*1024*1024;
		if(fileSize>maxFileSize){
			throw new RuntimeException(
					"文件最大只能上传10M，超过指定的文件大小");
		}
		
		//取得临时目录
		String systemDataFolder = 
			request.getSession().getServletContext().getRealPath(
			"/securitySystemData");
		String tempFolder = systemDataFolder + '/' + FolderConstants.UPLOAD_FILE_TEMP_FOLDER;
			
		File tempFolderFile = new File(tempFolder);
		if(!tempFolderFile.exists()){
			tempFolderFile.mkdirs();
		}
		
		//把上传的文件保存到临时文件里
		String tempFileName = FileNameUtil.getRandomFileName()+'.'+suffix;
		
		File tempFile = new File(tempFolder,tempFileName);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
		
		BufferedInputStream in = new BufferedInputStream(securityFormFile.getInputStream());
		
		byte[] buff = new byte[4000];
		int length = 0;
		while( (length = in.read(buff)) !=-1){
			out.write(buff, 0, length);
		}
		out.flush();
		out.close();
		in.close();	
		
		securityFormFile.destroy();
		
		if(suffix.equals("xls")){
			
			securityFormManager.processSecurityFormImport(user, tempFile);
			tempFile.delete();
		}else
			if(suffix.equals("zip")){
				//建立临时目录
				String tempZipFolderPath = tempFolder + '/' + FileNameUtil.getRandomFileName();
				File tempZipFolderFile = new File(tempZipFolderPath);
				tempZipFolderFile.mkdirs();
				
				//解压zip文件
				ZipUtil.unzip(tempFile.getAbsolutePath(), tempZipFolderPath);
				
				//取得临时目录下所有的子文件
				List<File> subFileList = new ArrayList<File>();
				FileUtil.getAllSubFileListToDestList(subFileList, tempZipFolderFile);
				
				//判断之文件是否是xls文件并尝试导入，发生所有的错误不抛出
				//，继续处理，直到导入完所有的文件
				for(File subFile:subFileList){
					String subFileName = subFile.getName();
					String subFileNameSuffix = FileNameUtil.getFileNameSuffix(subFileName);
					if(subFileNameSuffix.equals("xls")){
						securityFormManager.processSecurityFormImport(user, subFile);
					}
				}
				//删除临时解压文件
				FileUtil.deleteFolder(tempZipFolderFile);
				tempFile.delete();
			}else
				throw new RuntimeException("不支持的文件格式");

		request.setAttribute(Constants.MESSAGE_KEY, Constants.MESSAGE_UPLOAD_SUCCESS);
		request.setAttribute(Constants.REDIRECT_URL_KEY, LIST_STATION_IMPORT_SECURITY_FORM_PATH);
		
		return mapping.findForward("showMessage");
	}
	
}
