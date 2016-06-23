package com.ldp.security.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.ldp.security.report.domain.SecurityFormSheet;
import com.ldp.security.util.business.excel.securityFormImport.SecurityFormExcelImportUtil;

import junit.framework.TestCase;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestSecurityFormExcelImportUtil extends TestCase{
	
	public void testImport(){
		
		String sourceFilePath = "h:/testForm.xls";
		InputStream in = null;
		Workbook workbook = null;
		try {
			in = new FileInputStream(new File(sourceFilePath));
			workbook = Workbook.getWorkbook(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (BiffException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Sheet sheet = workbook.getSheet(0);
		SecurityFormSheet securityFormSheet = 
			SecurityFormExcelImportUtil.readSecurityFormSheetFromExcel(sheet);
		System.out.println("test");
	}

}
