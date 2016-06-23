package com.ldp.security.util.business.excel.securityFormImport;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 广场预检
 * @author Administrator
 *
 */
public class SquareCheckPosition {

	protected static final int SQUARE_CHECK_ROW = 
		OtherWorkPosition.OTHER_WORK_ROW + OtherWorkPosition.OTHER_WORK_HEIGHT;

	protected static final int SQUARE_CHECK_HEIGHT = 19; 
		
	protected static final ContentPosition SQUARE_CHECK_PEOPLE_NUM = 
		new ContentPosition(1,SQUARE_CHECK_ROW + 2);

	protected static final ContentPosition SQUARE_SPECIAL_PEOPLE_NUM = 
		new ContentPosition(3,SQUARE_CHECK_ROW + 2);

	protected static final int SQUARE_DANGEROUS_OBJECT_NUM_COL = 5;

	protected static final int SQUARE_DANGEROUS_OBJECT_NUM_FIRST_ROW = SQUARE_CHECK_ROW+6;
	
	protected static final int SQUARE_DANGEROUS_OBJECT_NUM_ROW_DISTANCE = 1;
	
	protected static List<ContentPosition> getSquareDangerousObjectNumPositionList(
			int dangerousObjectItemCount){
		
		List<ContentPosition> squareDangerousObjectNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			int rowIndex = i;
			
			int currCol = SQUARE_DANGEROUS_OBJECT_NUM_COL;
			int currRow = SQUARE_DANGEROUS_OBJECT_NUM_FIRST_ROW + 
				rowIndex*SQUARE_DANGEROUS_OBJECT_NUM_ROW_DISTANCE;
			
			squareDangerousObjectNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return squareDangerousObjectNumPositionList;
	}
	
}
