package com.ldp.security.util.business.excel.securityFormProperty;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 安检查危位置信息，位于表中公共区域
 * @author Administrator
 *
 */
public class CheckDangerousProperty {
	
	public static final String CHECK_DANGEROUS_BASE_POSITION_KEY = 
		"CHECK_DANGEROUS_BASE_POSITION";

	public static final String SECURITY_MACHINE_TROUBLE_NUM = 
		"SECURITY_MACHINE_TROUBLE_NUM";
	
	public static final String CHECK_DANGEROUS_OBJECT_NUM_FIRST = 
		"CHECK_DANGEROUS_OBJECT_NUM_FIRST";
	
	private static final int CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE = 1;
	
	public static List<ContentPosition> getCheckDangerousObjectNumPositionList(
			int dangerousObjectItemCount,int itemFirstCol,int itemFirstRow){
		
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			int rowIndex = i;
			
			int currCol = itemFirstCol;
			int currRow = itemFirstRow + 
				rowIndex*CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE;
			
			checkDangerousObjectNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return checkDangerousObjectNumPositionList;
	}
}
