package com.ldp.security.util.business.excel.securityFormImport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;

import com.ldp.security.report.domain.CommonForm;
import com.ldp.security.report.domain.KeyunForm;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.business.excel.common.ContentPosition;
import com.ldp.security.util.business.excel.common.ExcelReadFuncUtil;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 导入车间填写的反恐报表
 * @author Administrator
 *
 */
public class SecurityFormExcelImportUtil {
	
	public static SecurityFormSheet readSecurityFormSheetFromExcel(Sheet sheet){
		
		SecurityFormSheet securityFormSheet = new SecurityFormSheet();
		
		String reportDepartmentName = 
			ExcelReadFuncUtil.readStringFromSheet(sheet, TitlePosition.REPORT_DEPARTMENT);
		securityFormSheet.setReportDepartmentName(reportDepartmentName);
		
		long reportDepartmentId = 
			ExcelReadFuncUtil.readLongFromSheet(sheet, TitlePosition.REPORT_DEPARTMENT_ID);
		securityFormSheet.setReportDepartmentId(reportDepartmentId);
		
		SecurityForm securityForm = new SecurityForm();
		
		securityForm.setState(SecurityForm.STATE_IMPORT);
		
		int startYear = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,TitlePosition.START_DATE_YEAR);
		int startMonth = ExcelReadFuncUtil.readIntFromSheet(sheet, TitlePosition.START_DATE_MONTH);
		int startDay = ExcelReadFuncUtil.readIntFromSheet(sheet, TitlePosition.START_DATE_DAY);
		
		Date startDate = DateUtil.getDateFromYearMonthDay(startYear, startMonth, startDay);
		String startDateString = DateUtil.formatDateToString(startDate);
		securityForm.setStartDateString(startDateString);
		
		int endYear = ExcelReadFuncUtil.readIntFromSheet(sheet,TitlePosition.END_DATE_YEAR);
		int endMonth = ExcelReadFuncUtil.readIntFromSheet(sheet, TitlePosition.END_DATE_MONTH);
		int endDay = ExcelReadFuncUtil.readIntFromSheet(sheet, TitlePosition.END_DATE_DAY);
		
		Date endDate = DateUtil.getDateFromYearMonthDay(endYear, endMonth, endDay);
		String endDateString = DateUtil.formatDateToString(endDate);
		securityForm.setEndDateString(endDateString);
		
		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		securityForm.setLastDays(lastDays);
		
		securityForm.setReportTimeString(DateUtil.getCurrentTimeString());
		
		CommonForm commonForm = readCommonFormFromExcel(sheet);
		KeyunForm keyunForm = readKeyunFormFromExcel(sheet);
		
		securityForm.setCommonForm(commonForm);
		securityForm.setKeyunForm(keyunForm);
		
		securityFormSheet.setSecurityForm(securityForm);
		
		return securityFormSheet;
	}

	private static KeyunForm readKeyunFormFromExcel(Sheet sheet) {
		
		KeyunForm keyunForm = new KeyunForm();
		
		int squareCheckPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, SquareCheckPosition.SQUARE_CHECK_PEOPLE_NUM);
		keyunForm.setSquareCheckPeopleNum(squareCheckPeopleNum);
		
		int squareSpecialPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, SquareCheckPosition.SQUARE_SPECIAL_PEOPLE_NUM);
		keyunForm.setSquareSpecialPeopleNum(squareSpecialPeopleNum);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		List<ContentPosition> squareDangerousObjectNumPositionList = 
			SquareCheckPosition.getSquareDangerousObjectNumPositionList(dangerousObjectItemCount);
		List<Integer> squareDangerousObjectNumList = new ArrayList<Integer>();
		
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			ContentPosition currContentPosition = 
				squareDangerousObjectNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			squareDangerousObjectNumList.add(value);
		}
		keyunForm.setSquareDangerousObjectNumList(squareDangerousObjectNumList);
		
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		List<ContentPosition> specialCodePeopleNumPositionList = 
			CheckTicketIdentityPosition.getSpecialCodePeopleNumPositionList(specialCodeItemCount);
		List<Integer> specialCodePeopleNumList = new ArrayList<Integer>();
		
		for(int i=0;i<specialCodeItemCount;i++){
			
			ContentPosition currContentPosition = 
				specialCodePeopleNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			specialCodePeopleNumList.add(value);
		}
		keyunForm.setSpecialCodePeopleNumList(specialCodePeopleNumList);
		
		int yanzhengUsingFakePaperNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CheckTicketIdentityPosition.YANZHENG_USING_FAKE_PAPER_NUM);
		keyunForm.setYanzhengUsingFakePaperNum(yanzhengUsingFakePaperNum);
		
		int arrestPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CheckTicketIdentityPosition.ARREST_PEOPLE_NUM);
		keyunForm.setArrestPeopleNum(arrestPeopleNum);
		
		int finePeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CheckTicketIdentityPosition.FINE_PEOPLE_NUM);
		keyunForm.setFinePeopleNum(finePeopleNum);
		
		int studyPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CheckTicketIdentityPosition.STUDY_PEOPLE_NUM);
		keyunForm.setStudyPeopleNum(studyPeopleNum);
		
		int saleUsingFakePaperNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, SaleTicketPosition.SALE_USING_FAKE_PAPER_NUM);
		keyunForm.setSaleUsingFakePaperNum(saleUsingFakePaperNum);
		
		int saleSpecialPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, SaleTicketPosition.SALE_SPECIAL_PEOPLE_NUM);
		keyunForm.setSaleSpecialPeopleNum(saleSpecialPeopleNum);
		
		int periodNumberOfPassengers = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, WaitingHallCheckPosition.PERIOD_NUMBER_OF_PASSENGERS);
		keyunForm.setPeriodNumberOfPassengers(periodNumberOfPassengers);
		
		int waitingHallCheckPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, WaitingHallCheckPosition.WAITING_HALL_CHECK_PEOPLE_NUM);
		keyunForm.setWaitingHallCheckPeopleNum(waitingHallCheckPeopleNum);
		
		int waitingHallXianzaPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, WaitingHallCheckPosition.WAITING_HALL_XIANZA_PEOPLE_NUM);
		keyunForm.setWaitingHallXianzaPeopleNum(waitingHallXianzaPeopleNum);
		
		int jianpiaoWeisuiPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, JianpiaoPosition.JIANPIAO_WEISUI_PEOPLE_NUM);
		keyunForm.setJianpiaoWeisuiPeopleNum(jianpiaoWeisuiPeopleNum);
		
		int specialTrainIdentityWrongPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, JianpiaoPosition.SPECIAL_TRAIN_IDENTITY_WRONG_PEOPLE_NUM);
		keyunForm.setSpecialTrainIdentityWrongPeopleNum(specialTrainIdentityWrongPeopleNum);
		
		String specialTrainIdentityWrongInfo = 
			ExcelReadFuncUtil.readStringFromSheet(sheet, JianpiaoPosition.SPECIAL_TRAIN_IDENTITY_WRONG_INFO);
		if(specialTrainIdentityWrongInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			specialTrainIdentityWrongInfo = 
				specialTrainIdentityWrongInfo.substring(0, SecurityForm.LONG_TEXT_SIZE);
		}
		keyunForm.setSpecialTrainIdentityWrongInfo(specialTrainIdentityWrongInfo);
		
		return keyunForm;
	}

	private static CommonForm readCommonFormFromExcel(Sheet sheet) {
		
		CommonForm commonForm = new CommonForm();
		
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig().getSecurityMachineMaxCount();
		List<ContentPosition> securityMachineCheckNumPositionList = 
			CheckDangerousMachinePosition.getSecurityMachineCheckNumPositionList(
					securityMachineMaxCount);		
		List<Integer> securityMachineCheckNumList = new ArrayList<Integer>();
		
		for(int i=0;i<securityMachineMaxCount;i++){
			
			ContentPosition currContentPosition = 
				securityMachineCheckNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			securityMachineCheckNumList.add(value);
		}
		commonForm.setSecurityMachineCheckNumList(securityMachineCheckNumList);
		
		int securityMachineTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CheckDangerousPosition.SECURITY_MACHINE_TROUBLE_NUM);
		commonForm.setSecurityMachineTroubleNum(securityMachineTroubleNum);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			CheckDangerousPosition.getCheckDangerousObjectNumPositionList(
					dangerousObjectItemCount);
		List<Integer> checkDangerousObjectNumList = new ArrayList<Integer>();
		
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			ContentPosition currContentPosition = 
				checkDangerousObjectNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			checkDangerousObjectNumList.add(value);
		}
		commonForm.setCheckDangerousObjectNumList(checkDangerousObjectNumList);
		
		int zhanquCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, ZhanquPosition.ZHANQU_CHECK_NUM);
		commonForm.setZhanquCheckNum(zhanquCheckNum);

		int cctvCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CCTVPosition.CCTV_CHECK_NUM);
		commonForm.setCctvCheckNum(cctvCheckNum);
		
		int cctvTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, CCTVPosition.CCTV_TROUBLE_NUM);
		commonForm.setCctvTroubleNum(cctvTroubleNum);
		
		int equipmentCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, EquipmentPosition.EQUIPMENT_CHECK_NUM);
		commonForm.setEquipmentCheckNum(equipmentCheckNum);
		
		int equipmentTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, EquipmentPosition.EQUIPMENT_TROUBLE_NUM);
		commonForm.setEquipmentTroubleNum(equipmentTroubleNum);
		
		int militiamanCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, MilitiamanPosition.MILITIAMAN_CHECK_NUM);
		commonForm.setMilitiamanCheckNum(militiamanCheckNum);
		
		int trainningPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, ImportantJobPosition.TRAINNING_PEOPLE_NUM);
		commonForm.setTrainningPeopleNum(trainningPeopleNum);
		
		int practicePeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, ImportantJobPosition.PRACTICE_PEOPLE_NUM);
		commonForm.setPracticePeopleNum(practicePeopleNum);
		
		String otherWorkInfo = 
			ExcelReadFuncUtil.readStringFromSheet(sheet, OtherWorkPosition.OTHER_WORK_INFO);
		if(otherWorkInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			otherWorkInfo = 
				otherWorkInfo.substring(0, SecurityForm.LONG_TEXT_SIZE);
		}
		commonForm.setOtherWorkInfo(otherWorkInfo);
		
		return commonForm;
	}
	
}
