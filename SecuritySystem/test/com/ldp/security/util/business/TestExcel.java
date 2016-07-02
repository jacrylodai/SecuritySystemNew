package com.ldp.security.util.business;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.RGB;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TestExcel extends TestCase{

	public void testWrite(){
		
		File sourceTemplateFile = new File("H:/test/departmentExcelTemplate.xls");
		File excelTemplateFile = new File("H:/test/testOut1.xls");
		
		Workbook sourceWorkbook = null;
		WritableWorkbook destWorkbook = null;
		
		try {
			sourceWorkbook = Workbook.getWorkbook(sourceTemplateFile);
			destWorkbook = Workbook.createWorkbook(
					excelTemplateFile, sourceWorkbook);
			
			WritableSheet sheet = destWorkbook.getSheet(0);
			
			WritableCellFormat unLockedCell = new WritableCellFormat ();
			unLockedCell.setLocked(false);
			Colour c = Colour.PALE_BLUE;
			unLockedCell.setBackground(c);

			// Create the label, specifying content and format
//			Label label2 = new Label(3,7, "test", unLockedCell);
			Number number = new Number(3,6,21,unLockedCell);
			sheet.addCell(number); 
			
			destWorkbook.write();
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (WriteException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (BiffException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			if(null!=destWorkbook){
				try {
					destWorkbook.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != sourceWorkbook){
				sourceWorkbook.close();
			}
		}
		
	}

	public void testWrite2(){
		
		File excelTemplateFile = new File("H:/test/testOut2.xls");
		
		WritableWorkbook destWorkbook = null;
		
		try {
			destWorkbook = Workbook.createWorkbook(excelTemplateFile);
			
			WritableSheet sheet = destWorkbook.createSheet("test", 0);

			Colour[] colourArr = Colour.getAllColours();
			for(int i=0;i<colourArr.length;i++){
				Colour colour = colourArr[i];
				RGB rgb = colour.getDefaultRGB();
				System.out.println("Colour des:"+colour.getDescription());
				System.out.println("r:"+rgb.getRed() +".g:"+rgb.getGreen() 
						+ ".b:"+rgb.getBlue());

				WritableCellFormat colourCell = new WritableCellFormat ();
				colourCell.setBackground(colour);

				Label label = new Label(0,i, colour.getDescription(), colourCell);
				sheet.addCell(label); 
				
				label = new Label(1,i, String.valueOf(rgb.getRed()), colourCell);
				sheet.addCell(label); 
				label = new Label(2,i, String.valueOf(rgb.getGreen()), colourCell);
				sheet.addCell(label); 
				label = new Label(3,i, String.valueOf(rgb.getBlue()), colourCell);
				sheet.addCell(label); 
			}
			
			destWorkbook.write();
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (WriteException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			if(null!=destWorkbook){
				try {
					destWorkbook.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void testJExcelColour(){
		
		Colour[] colourArr = Colour.getAllColours();
		for(Colour colour:colourArr){
			RGB rgb = colour.getDefaultRGB();
			System.out.println("Colour des:"+colour.getDescription());
			System.out.println("r:"+rgb.getRed() +".g:"+rgb.getGreen() 
					+ ".b:"+rgb.getBlue());
		}
	}
	
}
