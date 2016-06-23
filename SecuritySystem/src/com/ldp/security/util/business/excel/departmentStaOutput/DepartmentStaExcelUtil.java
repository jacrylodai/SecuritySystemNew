
package com.ldp.security.util.business.excel.departmentStaOutput;

import java.util.List;

import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.sta.domain.CommonSta;
import com.ldp.security.sta.domain.KeyunSta;
import com.ldp.security.sta.domain.SecurityMachineCheckInfo;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.util.business.excel.common.ContentPosition;
import com.ldp.security.util.business.excel.common.ExcelWriteFuncUtil;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 导出报表统计内容到excel文件
 * @author Administrator
 *
 */
public class DepartmentStaExcelUtil {

	public static void writeDepartmentStaToExcel(
			WritableSheet sheet,StaPeriodSecurity staPeriodSecurity
			) throws RowsExceededException, WriteException{
		
		WritableFont font = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD); 
		WritableCellFormat cellFormat = new WritableCellFormat(font);
		cellFormat.setAlignment(Alignment.LEFT);
		cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		
		Department staDepartment = staPeriodSecurity.getStaDepartment();
		ExcelWriteFuncUtil.addLabelCell(sheet
				,TitlePosition.DEPARTMENT_NAME,staDepartment.getDepartmentName(),cellFormat);
		
		StaPeriodInfo staPeriodInfo = staPeriodSecurity.getStaPeriodInfo();
		
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.STA_START_DATE_STRING
				, staPeriodInfo.getStartDateString(),cellFormat);		
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.STA_END_DATE_STRING
				, staPeriodInfo.getEndDateString(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, TitlePosition.STA_LAST_DAYS
				, staPeriodInfo.getLastDays(),cellFormat);
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.STA_TIME_STRING
				, staPeriodInfo.getStaTimeString());
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.STA_USER_USERNAME
				, staPeriodInfo.getStaUser().getUsername(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, TitlePosition.ESTIMATE_STA_DAYS
				, staPeriodSecurity.getEstimateStaDays(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, TitlePosition.ACTUAL_STA_DAYS
				, staPeriodSecurity.getActualStaDays(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(
				sheet
				, CheckDangerousPosition.CHECK_DANGEROUS_OBJECT_TOTAL_NUM_ON_PERIOD_OF_PASSENGERS
				, Double.valueOf(
						staPeriodSecurity.
						getCheckDangerousObjectTotalNumOnPeriodNumOfPassengers())
				, cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(
				sheet
				, SquareCheckPosition.SQUARE_DANGEROUS_OBJECT_TOTAL_NUM_ON_NUMBER_OF_PASSENGERS
				, Double.valueOf(
						staPeriodSecurity.
						getSquareDangerousObjectTotalNumOnPeriodNumOfPassengers())
				, cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(
				sheet
				, CheckTicketIdentityPosition.SPECIAL_CODE_PEOPLE_TOTAL_NUM_ON_NUMBER_OF_PASSENGERS
				, Double.valueOf(
						staPeriodSecurity
						.getSpecialCodePeopleTotalNumOnPeriodNumOfPassengers())
				, cellFormat);
		
		CommonSta commonSta = staPeriodSecurity.getCommonSta();
		writeCommonStaToExcel(sheet,commonSta,cellFormat);
		
		KeyunSta keyunSta = staPeriodSecurity.getKeyunSta();
		writeKeyunStaToExcel(sheet,keyunSta,cellFormat);
	}

	private static void writeKeyunStaToExcel(WritableSheet sheet,
			KeyunSta keyunSta,WritableCellFormat cellFormat) 
			throws RowsExceededException, WriteException {

		ExcelWriteFuncUtil.addDoubleCell(sheet
				, SquareCheckPosition.SQUARE_CHECK_PEOPLE_NUM
				, keyunSta.getSquareCheckPeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, SquareCheckPosition.SQUARE_SPECIAL_PEOPLE_NUM
				, keyunSta.getSquareSpecialPeopleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, SquareCheckPosition.SQUARE_DANGEROUS_OBJECT_TOTAL_NUM
				, keyunSta.getSquareDangerousObjectTotalNum(),cellFormat);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig()
			.getDangerousObjectItemCount();
		List<ContentPosition> squareDangerousObjectNumPositionList = 
			SquareCheckPosition.getSquareDangerousObjectNumPositionList(
					dangerousObjectItemCount);
		List<Integer> squareDangerousObjectNumList = 
			keyunSta.getSquareDangerousObjectNumList();
		for(int i=0;i<dangerousObjectItemCount;i++){
			int squareDangerousObjectNum = squareDangerousObjectNumList.get(i);
			ContentPosition contentPosition = 
				squareDangerousObjectNumPositionList.get(i);
			ExcelWriteFuncUtil.addDoubleCell(sheet, contentPosition
					, squareDangerousObjectNum,cellFormat);
		}
		
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, CheckTicketIdentityPosition.SPECIAL_CODE_PEOPLE_TOTAL_NUM
				, keyunSta.getSpecialCodePeopleTotalNum(),cellFormat);
		
		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig().getSpecialCodeItemCount();
		List<ContentPosition> specialCodePeopleNumPositionList = 
			CheckTicketIdentityPosition.getSpecialCodePeopleNumPositionList(specialCodeItemCount);
		List<Integer> specialCodePeopleNumList = keyunSta.getSpecialCodePeopleNumList();
		for(int i=0;i<specialCodeItemCount;i++){
			int specialCodePeopleNum = specialCodePeopleNumList.get(i);
			ContentPosition contentPosition = specialCodePeopleNumPositionList.get(i);
			ExcelWriteFuncUtil.addDoubleCell(sheet, contentPosition
					, specialCodePeopleNum,cellFormat);
		}
		
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, CheckTicketIdentityPosition.YANZHENG_USING_FAKE_PAPER_NUM
				, keyunSta.getYanzhengUsingFakePaperNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, CheckTicketIdentityPosition.ARREST_PEOPLE_NUM
				, keyunSta.getArrestPeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, CheckTicketIdentityPosition.FINE_PEOPLE_NUM
				, keyunSta.getFinePeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, CheckTicketIdentityPosition.STUDY_PEOPLE_NUM
				, keyunSta.getStudyPeopleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, SaleTicketPosition.SALE_USING_FAKE_PAPER_NUM
				, keyunSta.getSaleUsingFakePaperNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, SaleTicketPosition.SALE_SPECIAL_PEOPLE_NUM
				, keyunSta.getSaleSpecialPeopleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, WaitingHallCheckPosition.PERIOD_NUMBER_OF_PASSENGERS
				, keyunSta.getPeriodNumberOfPassengers(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, WaitingHallCheckPosition.WAITING_HALL_CHECK_PEOPLE_NUM
				, keyunSta.getWaitingHallCheckPeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, WaitingHallCheckPosition.WAITING_HALL_XIANZA_PEOPLE_NUM
				, keyunSta.getWaitingHallXianzaPeopleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, JianpiaoPosition.JIANPIAO_WEISUI_PEOPLE_NUM
				, keyunSta.getJianpiaoWeisuiPeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, JianpiaoPosition.SPECIAL_TRAIN_IDENTITY_WRONG_PEOPLE_NUM
				, keyunSta.getSpecialTrainIdentityWrongPeopleNum(),cellFormat);
		
	}

	private static void writeCommonStaToExcel(WritableSheet sheet,
			CommonSta commonSta,WritableCellFormat cellFormat) 
			throws RowsExceededException, WriteException {
		
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, CheckDangerousPosition.SECURITY_MACHINE_TROUBLE_NUM
				, commonSta.getSecurityMachineTroubleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet
				, CheckDangerousPosition.CHECK_DANGEROUS_OBJECT_TOTAL_NUM
				, commonSta.getCheckDangerousObjectTotalNum(),cellFormat);
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig()
			.getDangerousObjectItemCount();
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			CheckDangerousPosition.getCheckDangerousObjectNumPositionList(
				dangerousObjectItemCount);
		List<Integer> checkDangerousObjectNumList = 
			commonSta.getCheckDangerousObjectNumList();
		for(int i=0;i<dangerousObjectItemCount;i++){
			int checkDangerousObjectNum = checkDangerousObjectNumList.get(i);
			ContentPosition contentPosition = 
				checkDangerousObjectNumPositionList.get(i);
			ExcelWriteFuncUtil.addDoubleCell(sheet, contentPosition
					, checkDangerousObjectNum,cellFormat);
		}
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, ZhanquPosition.ZHANQU_CHECK_NUM
				, commonSta.getZhanquCheckNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, CCTVPosition.CCTV_CHECK_NUM
				, commonSta.getCctvCheckNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, CCTVPosition.CCTV_TROUBLE_NUM
				, commonSta.getCctvTroubleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, EquipmentPosition.EQUIPMENT_CHECK_NUM
				, commonSta.getEquipmentCheckNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, EquipmentPosition.EQUIPMENT_TROUBLE_NUM
				, commonSta.getEquipmentTroubleNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, MilitiamanPosition.MILITIAMAN_CHECK_NUM
				, commonSta.getMilitiamanCheckNum(),cellFormat);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, ImportantJobPosition.TRAINNING_PEOPLE_NUM
				, commonSta.getTrainningPeopleNum(),cellFormat);
		ExcelWriteFuncUtil.addDoubleCell(sheet, ImportantJobPosition.PRACTICE_PEOPLE_NUM
				, commonSta.getPracticePeopleNum(),cellFormat);
		
		List<SecurityMachineCheckInfo> securityMachineCheckInfoList = 
			commonSta.getSecurityMachineCheckInfoList();
		for(int i=0;i<securityMachineCheckInfoList.size();i++){
			
			SecurityMachineCheckInfo checkInfo = securityMachineCheckInfoList.get(i);
			ContentPosition machineNamePosition = 
				CheckDangerousSecurityMachinePosition.getSecurityMachineCheckInfoNamePosition(i);
			ContentPosition machineCheckNumPosition = 
				CheckDangerousSecurityMachinePosition.getSecurityMachineCheckInfoCheckNumPosition(i);
			
			ExcelWriteFuncUtil.addLabelCell(sheet, machineNamePosition, checkInfo.getName());
			ExcelWriteFuncUtil.addDoubleCell(sheet, machineCheckNumPosition, checkInfo.getCheckNum(),cellFormat);
		}
		
	}
	
}
