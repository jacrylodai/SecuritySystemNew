package com.ldp.security.util.business.excel.common;

import com.ldp.security.util.validate.ClientValidate;

import jxl.Cell;
import jxl.Sheet;

public class ExcelReadFuncUtil {

	public static String readStringFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		Cell cell = sheet.getCell(contentPosition.getCol(),contentPosition.getRow());
		return cell.getContents();
	}


	public static int readIntFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		String valueStr = readStringFromSheet(sheet,contentPosition);
		int value = 0;
		if(!ClientValidate.isEmpty(valueStr)){
			value = Integer.parseInt(valueStr);
		}
		return value;
	}


	public static long readLongFromSheet(Sheet sheet,ContentPosition contentPosition){
		
		String valueStr = readStringFromSheet(sheet,contentPosition);
		long value = 0L;
		if(!ClientValidate.isEmpty(valueStr)){
			value = Long.parseLong(valueStr);
		}
		
		return value;
	}
	
}
