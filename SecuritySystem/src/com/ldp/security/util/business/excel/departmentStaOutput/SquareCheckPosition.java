package com.ldp.security.util.business.excel.departmentStaOutput;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class SquareCheckPosition {

	protected static final int SQUARE_CHECK_ROW = 
		ImportantJobPosition.IMPORTANT_JOB_ROW + ImportantJobPosition.IMPORTANT_JOB_HEIGHT;

	protected static final int SQUARE_CHECK_HEIGHT = 21; 
		
	protected static final ContentPosition SQUARE_CHECK_PEOPLE_NUM = 
		new ContentPosition(1,SQUARE_CHECK_ROW + 2);

	protected static final ContentPosition SQUARE_SPECIAL_PEOPLE_NUM = 
		new ContentPosition(3,SQUARE_CHECK_ROW + 2);

	protected static final ContentPosition SQUARE_DANGEROUS_OBJECT_TOTAL_NUM = 
		new ContentPosition(2,SQUARE_CHECK_ROW + 5);

	protected static final ContentPosition SQUARE_DANGEROUS_OBJECT_TOTAL_NUM_ON_NUMBER_OF_PASSENGERS = 
		new ContentPosition(3,SQUARE_CHECK_ROW + 6);

	protected static final int SQUARE_DANGEROUS_OBJECT_NUM_COL = 5;

	protected static final int SQUARE_DANGEROUS_OBJECT_NUM_FIRST_ROW = 
		SQUARE_CHECK_ROW+8;
	
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
