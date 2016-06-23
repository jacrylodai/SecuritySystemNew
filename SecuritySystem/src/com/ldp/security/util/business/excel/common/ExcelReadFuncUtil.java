package com.ldp.security.util.business.excel.common;

import jxl.Cell;
import jxl.Sheet;

public class ExcelReadFuncUtil {

	public static String readStringFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		Cell cell = sheet.getCell(contentPosition.getCol(),contentPosition.getRow());
		return cell.getContents();
	}


	public static int readIntFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		int value = 
			Integer.parseInt(readStringFromSheet(sheet,contentPosition));
		return value;
	}


	public static long readLongFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		long value = 
			Long.parseLong(readStringFromSheet(sheet,contentPosition));
		return value;
	}
	
}
