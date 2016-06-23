package com.ldp.security.util.business.excel.common;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelWriteFuncUtil {

	/**
	 * 添加一个字符串单元格
	 * @param sheet
	 * @param col
	 * @param row
	 * @param labelName
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void addLabelCell(WritableSheet sheet
			,ContentPosition contentPosition,String labelName
			) throws RowsExceededException, WriteException{
		
		Label label = new Label(contentPosition.getCol(),
				contentPosition.getRow(),labelName);
		sheet.addCell(label);
	}

	/**
	 * 添加一个字符串单元格
	 * @param sheet
	 * @param col
	 * @param row
	 * @param labelName
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void addLabelCell(WritableSheet sheet
			,ContentPosition contentPosition,String labelName
			,WritableCellFormat format) throws RowsExceededException, WriteException{
		
		Label label = new Label(contentPosition.getCol(),contentPosition.getRow()
				,labelName,format);
		sheet.addCell(label);
	}
	
	/**
	 * 添加一个整数单元格
	 * @param sheet
	 * @param col
	 * @param row
	 * @param value
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void addDoubleCell(WritableSheet sheet
			,ContentPosition contentPosition,double value
			) throws RowsExceededException, WriteException{
		
		Number valueN = new Number(contentPosition.getCol(),contentPosition.getRow()
				,value);
		sheet.addCell(valueN);
	}
	
	/**
	 * 添加一个整数单元格
	 * @param sheet
	 * @param contentPosition
	 * @param value
	 * @param format
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void addDoubleCell(WritableSheet sheet
			,ContentPosition contentPosition,double value
			,WritableCellFormat format) throws RowsExceededException, WriteException{
		
		Number valueN = new Number(contentPosition.getCol(),contentPosition.getRow()
				,value,format);
		sheet.addCell(valueN);
	}
	
}
