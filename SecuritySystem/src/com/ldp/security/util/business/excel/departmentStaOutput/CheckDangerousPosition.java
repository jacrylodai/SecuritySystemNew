package com.ldp.security.util.business.excel.departmentStaOutput;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class CheckDangerousPosition {

	protected static final int CHECK_DANGEROUS_ROW = 
		TitlePosition.TITLE_ROW + TitlePosition.TITLE_HEIGHT;
	
	protected static final int CHECK_DANGEROUS_HEIGHT = 23;

	protected static final ContentPosition ALL_SECURITY_MACHINE_CHECK_NUM = 
		new ContentPosition(2,CHECK_DANGEROUS_ROW+1);
			
	protected static final ContentPosition SECURITY_MACHINE_TROUBLE_NUM = 
		new ContentPosition(1,CHECK_DANGEROUS_ROW+4);

	protected static final ContentPosition CHECK_DANGEROUS_OBJECT_TOTAL_NUM = 
		new ContentPosition(2,CHECK_DANGEROUS_ROW+7);

	protected static final ContentPosition CHECK_DANGEROUS_OBJECT_TOTAL_NUM_ON_PERIOD_OF_PASSENGERS = 
		new ContentPosition(3,CHECK_DANGEROUS_ROW+8);
	
	protected static final int CHECK_DANGEROUS_OBJECT_NUM_COL = 5;

	protected static final int CHECK_DANGEROUS_OBJECT_NUM_FIRST_ROW = 
		CHECK_DANGEROUS_ROW+10;
	
	protected static final int CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE = 1;
	
	protected static List<ContentPosition> getCheckDangerousObjectNumPositionList(
			int dangerousObjectItemCount){
		
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			int rowIndex = i;
			
			int currCol = CHECK_DANGEROUS_OBJECT_NUM_COL;
			int currRow = CHECK_DANGEROUS_OBJECT_NUM_FIRST_ROW + 
				rowIndex*CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE;
			
			checkDangerousObjectNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return checkDangerousObjectNumPositionList;
	}
}
