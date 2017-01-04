package com.ldp.security.util.business.excel.securityFormProperty;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 广场预检
 * @author Administrator
 *
 */
public class SquareCheckProperty {

	public static final String SQUARE_CHECK_BASE_POSITION_KEY = "SQUARE_CHECK_BASE_POSITION";
		
	public static final String SQUARE_CHECK_PEOPLE_NUM = "SQUARE_CHECK_PEOPLE_NUM";

	public static final String SQUARE_SPECIAL_PEOPLE_NUM = "SQUARE_SPECIAL_PEOPLE_NUM"; 

	public static final String SQUARE_DANGEROUS_OBJECT_NUM_FIRST = "SQUARE_DANGEROUS_OBJECT_NUM_FIRST"; 

	private static final int SQUARE_DANGEROUS_OBJECT_NUM_ROW_DISTANCE = 1;
	
	public static List<ContentPosition> getSquareDangerousObjectNumPositionList(
			int dangerousObjectItemCount,int itemFirstCol,int itemFirstRow){
		
		List<ContentPosition> squareDangerousObjectNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			int rowIndex = i;
			
			int currCol = itemFirstCol;
			int currRow = itemFirstRow + 
				rowIndex*SQUARE_DANGEROUS_OBJECT_NUM_ROW_DISTANCE;
			
			squareDangerousObjectNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return squareDangerousObjectNumPositionList;
	}
	
}
