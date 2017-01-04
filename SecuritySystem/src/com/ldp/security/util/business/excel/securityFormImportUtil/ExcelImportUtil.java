package com.ldp.security.util.business.excel.securityFormImportUtil;

import jxl.Sheet;

import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.business.excel.common.FormVersion;

public abstract class ExcelImportUtil {

	public abstract SecurityFormSheet readSecurityFormSheetFromExcel(
			Sheet sheet,FormVersion formVersion);
	
}
