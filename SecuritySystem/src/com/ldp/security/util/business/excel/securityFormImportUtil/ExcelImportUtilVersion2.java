package com.ldp.security.util.business.excel.securityFormImportUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import com.ldp.security.report.domain.CommonForm;
import com.ldp.security.report.domain.KeyunForm;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.business.excel.common.BasePosition;
import com.ldp.security.util.business.excel.common.ContentPosition;
import com.ldp.security.util.business.excel.common.ExcelReadFuncUtil;
import com.ldp.security.util.business.excel.common.FormVersion;
import com.ldp.security.util.business.excel.securityFormProperty.CCTVProperty;
import com.ldp.security.util.business.excel.securityFormProperty.CheckDangerousMachineProperty;
import com.ldp.security.util.business.excel.securityFormProperty.CheckDangerousProperty;
import com.ldp.security.util.business.excel.securityFormProperty.CheckTicketIdentityProperty;
import com.ldp.security.util.business.excel.securityFormProperty.EquipmentProperty;
import com.ldp.security.util.business.excel.securityFormProperty.ImportantJobProperty;
import com.ldp.security.util.business.excel.securityFormProperty.JianpiaoProperty;
import com.ldp.security.util.business.excel.securityFormProperty.MilitiamanProperty;
import com.ldp.security.util.business.excel.securityFormProperty.OtherWorkProperty;
import com.ldp.security.util.business.excel.securityFormProperty.SaleTicketProperty;
import com.ldp.security.util.business.excel.securityFormProperty.SquareCheckProperty;
import com.ldp.security.util.business.excel.securityFormProperty.TitleProperty;
import com.ldp.security.util.business.excel.securityFormProperty.WaitingHallCheckProperty;
import com.ldp.security.util.business.excel.securityFormProperty.ZhanquProperty;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 导入EXCEL文档
 * 版本2
 * @author jacrylodai
 *
 */
public class ExcelImportUtilVersion2 extends ExcelImportUtil{

	@Override
	public SecurityFormSheet readSecurityFormSheetFromExcel(
			Sheet sheet,FormVersion formVersion) {
		
		SecurityFormSheet securityFormSheet = new SecurityFormSheet();
		
		Map<String,BasePosition> basePositionMap = formVersion.getBasePositionMap();
		
		BasePosition titleBasePosition = 
			basePositionMap.get(TitleProperty.TITLE_BASE_POSITION_KEY);
		
		ContentPosition reportDepaNamePosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.REPORT_DEPARTMENT_NAME);
		String reportDepartmentName = 
			ExcelReadFuncUtil.readStringFromSheet(sheet, reportDepaNamePosi);
		securityFormSheet.setReportDepartmentName(reportDepartmentName);
		
		ContentPosition reportDepaIdPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.REPORT_DEPARTMENT_ID);
		long reportDepartmentId = ExcelReadFuncUtil.readLongFromSheet(sheet, reportDepaIdPosi);
		securityFormSheet.setReportDepartmentId(reportDepartmentId);
		
		SecurityForm securityForm = new SecurityForm();
		securityForm.setState(SecurityForm.STATE_IMPORT);
		
		ContentPosition startDateYearPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.START_DATE_YEAR);
		int startDateYear = ExcelReadFuncUtil.readIntFromSheet(sheet, startDateYearPosi);

		ContentPosition startDateMonthPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.START_DATE_MONTH);
		int startDateMonth = ExcelReadFuncUtil.readIntFromSheet(sheet, startDateMonthPosi);

		ContentPosition startDateDayPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.START_DATE_DAY);
		int startDateDay = ExcelReadFuncUtil.readIntFromSheet(sheet, startDateDayPosi);

		Date startDate = DateUtil.getDateFromYearMonthDay(
				startDateYear, startDateMonth, startDateDay);
		String startDateString = DateUtil.formatDateToString(startDate);
		securityForm.setStartDateString(startDateString);
		
		ContentPosition endDateYearPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.END_DATE_YEAR);
		int endDateYear = ExcelReadFuncUtil.readIntFromSheet(sheet, endDateYearPosi);

		ContentPosition endDateMonthPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.END_DATE_MONTH);
		int endDateMonth = ExcelReadFuncUtil.readIntFromSheet(sheet, endDateMonthPosi);

		ContentPosition endDateDayPosi = 
			titleBasePosition.getContPosiBySubPosiName(TitleProperty.END_DATE_DAY);
		int endDateDay = ExcelReadFuncUtil.readIntFromSheet(sheet, endDateDayPosi);

		Date endDate = DateUtil.getDateFromYearMonthDay(
				endDateYear, endDateMonth, endDateDay);
		String endDateString = DateUtil.formatDateToString(endDate);
		securityForm.setEndDateString(endDateString);

		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		securityForm.setLastDays(lastDays);
		
		securityForm.setReportTimeString(DateUtil.getCurrentTimeString());
		
		CommonForm commonForm = readCommonFormFromExcel(sheet,basePositionMap);
		KeyunForm keyunForm = readKeyunFormFromExcel(sheet,basePositionMap);
		
		securityForm.setCommonForm(commonForm);
		securityForm.setKeyunForm(keyunForm);
		
		securityFormSheet.setSecurityForm(securityForm);
		
		return securityFormSheet;		
	}

	private CommonForm readCommonFormFromExcel(Sheet sheet,
			Map<String, BasePosition> basePositionMap) {
		
		CommonForm commonForm = new CommonForm();
		
		BasePosition checkDangerousBasePosition = 
			basePositionMap.get(CheckDangerousProperty.CHECK_DANGEROUS_BASE_POSITION_KEY);
		
		ContentPosition securityMachineTroubleNumPosition = 
			checkDangerousBasePosition.getContPosiBySubPosiName(
					CheckDangerousProperty.SECURITY_MACHINE_TROUBLE_NUM);
		int securityMachineTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, securityMachineTroubleNumPosition);
		commonForm.setSecurityMachineTroubleNum(securityMachineTroubleNum);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		ContentPosition checkDangObjNumFirstPosi = 
			checkDangerousBasePosition.getContPosiBySubPosiName(
					CheckDangerousProperty.CHECK_DANGEROUS_OBJECT_NUM_FIRST);
		int itemFirstCol = checkDangObjNumFirstPosi.getCol();
		int itemFirstRow = checkDangObjNumFirstPosi.getRow();
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			CheckDangerousProperty.getCheckDangerousObjectNumPositionList(
					dangerousObjectItemCount, itemFirstCol, itemFirstRow);
		List<Integer> checkDangerousObjectNumList = new ArrayList<Integer>();
		
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			ContentPosition currContentPosition = 
				checkDangerousObjectNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			checkDangerousObjectNumList.add(value);
		}
		commonForm.setCheckDangerousObjectNumList(checkDangerousObjectNumList);
		
		BasePosition zhanquBasePosition = 
			basePositionMap.get(ZhanquProperty.ZHANQU_BASE_POSITION_KEY);
		ContentPosition zhanquCheckNumPosi = 
			zhanquBasePosition.getContPosiBySubPosiName(ZhanquProperty.ZHANQU_CHECK_NUM);
		int zhanquCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, zhanquCheckNumPosi);
		commonForm.setZhanquCheckNum(zhanquCheckNum);

		BasePosition cctvBasePosition = 
			basePositionMap.get(CCTVProperty.CCTV_BASE_POSITION_KEY);
		ContentPosition cctvCheckNumPosi = 
			cctvBasePosition.getContPosiBySubPosiName(CCTVProperty.CCTV_CHECK_NUM);
		int cctvCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, cctvCheckNumPosi);
		commonForm.setCctvCheckNum(cctvCheckNum);
		
		ContentPosition cctvTroubleNumPosi = 
			cctvBasePosition.getContPosiBySubPosiName(CCTVProperty.CCTV_TROUBLE_NUM);
		int cctvTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, cctvTroubleNumPosi);
		commonForm.setCctvTroubleNum(cctvTroubleNum);
		
		BasePosition equipmentBasePosi = 
			basePositionMap.get(EquipmentProperty.EQUIPMENT_BASE_POSITION_KEY);
		ContentPosition equipmentCheckNumPosi = 
			equipmentBasePosi.getContPosiBySubPosiName(EquipmentProperty.EQUIPMENT_CHECK_NUM);
		int equipmentCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, equipmentCheckNumPosi);
		commonForm.setEquipmentCheckNum(equipmentCheckNum);
		
		ContentPosition equipmentTroubleNumPosi = 
			equipmentBasePosi.getContPosiBySubPosiName(EquipmentProperty.EQUIPMENT_TROUBLE_NUM);
		int equipmentTroubleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, equipmentTroubleNumPosi);
		commonForm.setEquipmentTroubleNum(equipmentTroubleNum);
		
		BasePosition militiamanBasePosition = 
			basePositionMap.get(MilitiamanProperty.MILITIAMAN_BASE_POSITION_KEY);
		ContentPosition militiamanCheckNumPosi = 
			militiamanBasePosition.getContPosiBySubPosiName(MilitiamanProperty.MILITIAMAN_CHECK_NUM);
		int militiamanCheckNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, militiamanCheckNumPosi);
		commonForm.setMilitiamanCheckNum(militiamanCheckNum);
		
		BasePosition importantJobBasePosition = 
			basePositionMap.get(ImportantJobProperty.IMPORTANT_JOB_BASE_POSITION_KEY);
		ContentPosition trainningCountPosi = 
			importantJobBasePosition.getContPosiBySubPosiName(
					ImportantJobProperty.TRAINNING_COUNT);
		int trainningCount = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, trainningCountPosi);
		commonForm.setTrainningCount(trainningCount);
		
		ContentPosition trainningPeopleNumPosi = 
			importantJobBasePosition.getContPosiBySubPosiName(ImportantJobProperty.TRAINNING_PEOPLE_NUM);
		int trainningPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, trainningPeopleNumPosi);
		commonForm.setTrainningPeopleNum(trainningPeopleNum);
		
		//如果有培训人数，那么证明至少培训过一次
		if(trainningPeopleNum>0 && trainningCount == 0){
			commonForm.setTrainningCount(1);
		}
		
		ContentPosition practiceCountPosi = 
			importantJobBasePosition.getContPosiBySubPosiName(
					ImportantJobProperty.PRACTICE_COUNT);
		int practiceCount = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, practiceCountPosi);
		commonForm.setPracticeCount(practiceCount);
		
		ContentPosition practicePeopleNumPosi = 
			importantJobBasePosition.getContPosiBySubPosiName(ImportantJobProperty.PRACTICE_PEOPLE_NUM);
		int practicePeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, practicePeopleNumPosi);
		commonForm.setPracticePeopleNum(practicePeopleNum);
		
		//如果有演练人数，那么至少演练了一次
		if(practicePeopleNum > 0 && practiceCount == 0){
			commonForm.setPracticeCount(1);
		}

		BasePosition otherWorkBasePosition = 
			basePositionMap.get(OtherWorkProperty.OTHER_WORK_BASE_POSITION_KEY);
		ContentPosition otherWorkInfoPosi = 
			otherWorkBasePosition.getContPosiBySubPosiName(OtherWorkProperty.OTHER_WORK_INFO);
		String otherWorkInfo = 
			ExcelReadFuncUtil.readStringFromSheet(sheet, otherWorkInfoPosi);
		if(otherWorkInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			otherWorkInfo = 
				otherWorkInfo.substring(0, SecurityForm.LONG_TEXT_SIZE);
		}
		commonForm.setOtherWorkInfo(otherWorkInfo);
		
		BasePosition checkDangerousMachineBasePosition = 
			basePositionMap.get(CheckDangerousMachineProperty.CHECK_DANGEROUS_MACHINE_BASE_POSITION_KEY);
		ContentPosition secuMachCheckNumFirstPosi = 
			checkDangerousMachineBasePosition.getContPosiBySubPosiName(
					CheckDangerousMachineProperty.SECU_MACH_CHECK_NUM_FIRST);
		int secuMachFirstCol = secuMachCheckNumFirstPosi.getCol();
		int secuMachFirstRow = secuMachCheckNumFirstPosi.getRow();
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig().getSecurityMachineMaxCount();
		List<ContentPosition> securityMachineCheckNumPositionList = 
			CheckDangerousMachineProperty.getSecurityMachineCheckNumPositionList(
					securityMachineMaxCount, secuMachFirstCol, secuMachFirstRow);	
		List<Integer> securityMachineCheckNumList = new ArrayList<Integer>();
		
		for(int i=0;i<securityMachineMaxCount;i++){
			
			ContentPosition currContentPosition = 
				securityMachineCheckNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			securityMachineCheckNumList.add(value);
		}
		commonForm.setSecurityMachineCheckNumList(securityMachineCheckNumList);
		
		return commonForm;
	}

	private KeyunForm readKeyunFormFromExcel(Sheet sheet,
			Map<String, BasePosition> basePositionMap) {

		KeyunForm keyunForm = new KeyunForm();
		
		BasePosition squareCheckBasePosition = 
			basePositionMap.get(SquareCheckProperty.SQUARE_CHECK_BASE_POSITION_KEY);
		ContentPosition squareCheckPeopleNumPosi = 
			squareCheckBasePosition.getContPosiBySubPosiName(
					SquareCheckProperty.SQUARE_CHECK_PEOPLE_NUM);
		int squareCheckPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, squareCheckPeopleNumPosi);
		keyunForm.setSquareCheckPeopleNum(squareCheckPeopleNum);
		
		ContentPosition squareSpecialPeopleNumPosi = 
			squareCheckBasePosition.getContPosiBySubPosiName(
					SquareCheckProperty.SQUARE_SPECIAL_PEOPLE_NUM);
		int squareSpecialPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet, squareSpecialPeopleNumPosi);
		keyunForm.setSquareSpecialPeopleNum(squareSpecialPeopleNum);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getDangerousObjectItemCount();
		ContentPosition squareDangerousObjectNumFirstPosi = 
			squareCheckBasePosition.getContPosiBySubPosiName(
					SquareCheckProperty.SQUARE_DANGEROUS_OBJECT_NUM_FIRST);
		int itemFirstCol = squareDangerousObjectNumFirstPosi.getCol();
		int itemFirstRow = squareDangerousObjectNumFirstPosi.getRow();
		List<ContentPosition> squareDangerousObjectNumPositionList = 
			SquareCheckProperty.getSquareDangerousObjectNumPositionList(
					dangerousObjectItemCount, itemFirstCol, itemFirstRow);
		List<Integer> squareDangerousObjectNumList = new ArrayList<Integer>();
		
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			ContentPosition currContentPosition = 
				squareDangerousObjectNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			squareDangerousObjectNumList.add(value);
		}
		keyunForm.setSquareDangerousObjectNumList(squareDangerousObjectNumList);
		
		BasePosition checkTicketIdentityBasePosition = 
			basePositionMap.get(CheckTicketIdentityProperty.CHECK_TICKET_IDENTITY_BASE_POSITION_KEY);
		ContentPosition specialCodePeopleNumFirstPosi = 
			checkTicketIdentityBasePosition.getContPosiBySubPosiName(
					CheckTicketIdentityProperty.SPECIAL_CODE_PEOPLE_NUM_FIRST);
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		int specCodeFirstCol = specialCodePeopleNumFirstPosi.getCol();
		int specCodeFirstRow = specialCodePeopleNumFirstPosi.getRow();
		List<ContentPosition> specialCodePeopleNumPositionList = 
			CheckTicketIdentityProperty.getSpecialCodePeopleNumPositionList(
					specialCodeItemCount, specCodeFirstCol, specCodeFirstRow);
		List<Integer> specialCodePeopleNumList = new ArrayList<Integer>();
		
		for(int i=0;i<specialCodeItemCount;i++){
			
			ContentPosition currContentPosition = 
				specialCodePeopleNumPositionList.get(i);
			int value = ExcelReadFuncUtil.readIntFromSheet(sheet, currContentPosition);
			specialCodePeopleNumList.add(value);
		}
		keyunForm.setSpecialCodePeopleNumList(specialCodePeopleNumList);
		
		ContentPosition yanzhengUsingFakePaperNumPosi = 
			checkTicketIdentityBasePosition.getContPosiBySubPosiName(
					CheckTicketIdentityProperty.YANZHENG_USING_FAKE_PAPER_NUM);
		int yanzhengUsingFakePaperNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,yanzhengUsingFakePaperNumPosi);
		keyunForm.setYanzhengUsingFakePaperNum(yanzhengUsingFakePaperNum);
		
		ContentPosition arrestPeopleNumPosi = 
			checkTicketIdentityBasePosition.getContPosiBySubPosiName(
					CheckTicketIdentityProperty.ARREST_PEOPLE_NUM);
		int arrestPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,arrestPeopleNumPosi);
		keyunForm.setArrestPeopleNum(arrestPeopleNum);
		
		ContentPosition finePeopleNumPosi = 
			checkTicketIdentityBasePosition.getContPosiBySubPosiName(
					CheckTicketIdentityProperty.FINE_PEOPLE_NUM);
		int finePeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,finePeopleNumPosi);
		keyunForm.setFinePeopleNum(finePeopleNum);
		
		ContentPosition studyPeopleNumPosi = 
			checkTicketIdentityBasePosition.getContPosiBySubPosiName(
					CheckTicketIdentityProperty.STUDY_PEOPLE_NUM);
		int studyPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,studyPeopleNumPosi);
		keyunForm.setStudyPeopleNum(studyPeopleNum);
		
		BasePosition saleTicketBasePosition = 
			basePositionMap.get(SaleTicketProperty.SALE_TICKET_BASE_POSITION_KEY);
		ContentPosition saleUsingFakePaperNumPosi = 
			saleTicketBasePosition.getContPosiBySubPosiName(
					SaleTicketProperty.SALE_USING_FAKE_PAPER_NUM);
		int saleUsingFakePaperNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,saleUsingFakePaperNumPosi);
		keyunForm.setSaleUsingFakePaperNum(saleUsingFakePaperNum);
		
		ContentPosition saleSpecialPeopleNumPosi = 
			saleTicketBasePosition.getContPosiBySubPosiName(
					SaleTicketProperty.SALE_SPECIAL_PEOPLE_NUM);
		int saleSpecialPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,saleSpecialPeopleNumPosi);
		keyunForm.setSaleSpecialPeopleNum(saleSpecialPeopleNum);
		
		BasePosition waitingHallCheckBasePosition = 
			basePositionMap.get(WaitingHallCheckProperty.WAITING_HALL_CHECK_BASE_POSITION_KEY);
		ContentPosition periodNumberOfPassengersPosi = 
			waitingHallCheckBasePosition.getContPosiBySubPosiName(
					WaitingHallCheckProperty.PERIOD_NUMBER_OF_PASSENGERS);
		int periodNumberOfPassengers = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,periodNumberOfPassengersPosi);
		keyunForm.setPeriodNumberOfPassengers(periodNumberOfPassengers);
		
		ContentPosition waitingHallCheckPeopleNumPosi = 
			waitingHallCheckBasePosition.getContPosiBySubPosiName(
					WaitingHallCheckProperty.WAITING_HALL_CHECK_PEOPLE_NUM);
		int waitingHallCheckPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,waitingHallCheckPeopleNumPosi);
		keyunForm.setWaitingHallCheckPeopleNum(waitingHallCheckPeopleNum);
		
		ContentPosition waitingHallXianzaPeopleNumPosi = 
			waitingHallCheckBasePosition.getContPosiBySubPosiName(
					WaitingHallCheckProperty.WAITING_HALL_XIANZA_PEOPLE_NUM);
		int waitingHallXianzaPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,waitingHallXianzaPeopleNumPosi);
		keyunForm.setWaitingHallXianzaPeopleNum(waitingHallXianzaPeopleNum);
		
		BasePosition jianpiaoBasePosition = 
			basePositionMap.get(JianpiaoProperty.JIANPIAO_BASE_POSITION_KEY);
		ContentPosition jianpiaoWeisuiPeopleNumPosi = 
			jianpiaoBasePosition.getContPosiBySubPosiName(
					JianpiaoProperty.JIANPIAO_WEISUI_PEOPLE_NUM);
		int jianpiaoWeisuiPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,jianpiaoWeisuiPeopleNumPosi);
		keyunForm.setJianpiaoWeisuiPeopleNum(jianpiaoWeisuiPeopleNum);
		
		ContentPosition specialTrainIdentityWrongPeopleNumPosi = 
			jianpiaoBasePosition.getContPosiBySubPosiName(
					JianpiaoProperty.SPECIAL_TRAIN_IDENTITY_WRONG_PEOPLE_NUM);
		int specialTrainIdentityWrongPeopleNum = 
			ExcelReadFuncUtil.readIntFromSheet(sheet,specialTrainIdentityWrongPeopleNumPosi);
		keyunForm.setSpecialTrainIdentityWrongPeopleNum(specialTrainIdentityWrongPeopleNum);
		
		ContentPosition specialTrainIdentityWrongInfoPosi = 
			jianpiaoBasePosition.getContPosiBySubPosiName(
					JianpiaoProperty.SPECIAL_TRAIN_IDENTITY_WRONG_INFO);
		String specialTrainIdentityWrongInfo = 
			ExcelReadFuncUtil.readStringFromSheet(sheet,specialTrainIdentityWrongInfoPosi);
		if(specialTrainIdentityWrongInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			specialTrainIdentityWrongInfo = 
				specialTrainIdentityWrongInfo.substring(0, SecurityForm.LONG_TEXT_SIZE);
		}
		keyunForm.setSpecialTrainIdentityWrongInfo(specialTrainIdentityWrongInfo);
		
		return keyunForm;
	}

}
