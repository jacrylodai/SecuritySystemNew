package com.ldp.security.util.business.excel.departmentTemplateOutput;

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
public class DepartmentTemplateExcelUtil {

	public static void writeDepartmentTemplateToExcel(
			WritableSheet sheet,Department department
			) throws RowsExceededException, WriteException{
		
		ExcelWriteFuncUtil.addLabelCell(sheet, TitlePosition.REPORT_DEPARTMENT
				, department.getDepartmentName());
		
		ExcelWriteFuncUtil.addDoubleCell(sheet, TitlePosition.REPORT_DEPARTMENT_ID
				, department.getDepartmentId());
		
	}
}
