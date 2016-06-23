package com.ldp.security.util.file;

import java.util.Random;

public class FileNameUtil {

	/**
	 * 得到文件的后缀，如exceldata.xls,后缀是xls
	 * @param fileName
	 * @return
	 */
	public static final String getFileNameSuffix(String fileName){
		
		if(fileName == null || fileName.length() == 0){
			return "";
		}
		
		int dotIndex = fileName.lastIndexOf('.');
		if(dotIndex == -1){
			return "";
		}
		return fileName.substring(dotIndex+1);
	}
	
	/**
	 * 得到一个随机的文件名
	 * @return
	 */
	public static final String getRandomFileName(){
		
		Random random = new Random(System.currentTimeMillis());
		String tempFileName = System.currentTimeMillis()
			+"_"+random.nextInt(10000000);
		return tempFileName;
	}
	
}
