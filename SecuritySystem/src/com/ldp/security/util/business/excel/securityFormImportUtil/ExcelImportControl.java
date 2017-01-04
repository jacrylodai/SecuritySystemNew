package com.ldp.security.util.business.excel.securityFormImportUtil;

import jxl.Sheet;

import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.business.excel.common.ContentPosition;
import com.ldp.security.util.business.excel.common.ExcelReadFuncUtil;
import com.ldp.security.util.business.excel.common.FormVersion;
import com.ldp.security.util.xml.XMLConfigReader;

public class ExcelImportControl {

	public static SecurityFormSheet readSecurityFormSheetFromExcel(Sheet sheet){
		
		ContentPosition versionCodePosi = 
			new ContentPosition(5,1);
		int versionCode = ExcelReadFuncUtil.readIntFromSheet(sheet, versionCodePosi);
		
		FormVersion formVersion = 
			XMLConfigReader.getInstance().getSystemConfig()
			.getFormVersionMap().get(versionCode);
		if(formVersion == null){
			throw new RuntimeException("当前文档的版本号在系统中不存在");
		}
		
		String versionClassPath = formVersion.getVersionClassPath();
		ExcelImportUtil excelImportUtil = null;
		try {
			excelImportUtil =
				(ExcelImportUtil)Class.forName(versionClassPath).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return excelImportUtil.readSecurityFormSheetFromExcel(sheet, formVersion);
	}
	
}
