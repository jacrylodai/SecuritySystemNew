package com.ldp.security.util.business.excel.departmentTemplateWithDayInfoOut;

import jxl.format.Colour;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.util.business.excel.common.ExcelWriteFuncUtil;

/**
 * 生成部门反恐报表模板
 * @author Administrator
 *
 */
public class DepartmentTemplateWithDayInfoExcelUtil {

	public static void writeDepartmentTemplateToExcelWithDayInfo(
			WritableSheet sheet,Department department,int year,int month,int day
			) throws RowsExceededException, WriteException{
		
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.REPORT_DEPARTMENT
				, department.getDepartmentName());
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, TitlePosition.REPORT_DEPARTMENT_ID
				, department.getDepartmentId());
		
//		WritableFont font = new WritableFont(WritableFont.ARIAL, 12); 
//		WritableCellFormat unLockedCell = new WritableCellFormat(font);
//		unLockedCell.setLocked(true);
//		unLockedCell.setBackground(Colour.WHITE);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.START_DATE_YEAR,year);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.START_DATE_MONTH,month);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.START_DATE_DAY,day);

		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.END_DATE_YEAR,year);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.END_DATE_MONTH,month);
		
		ExcelWriteFuncUtil.addDoubleCell(sheet,TitlePosition.END_DATE_DAY,day);
		
	}
}
