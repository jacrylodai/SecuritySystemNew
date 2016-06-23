package com.ldp.security.util.business.excel.departmentStaOutput;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class CheckTicketIdentityPosition {

	protected static final int CHECK_TICKET_IDENTITY_ROW = 
		SquareCheckPosition.SQUARE_CHECK_ROW + SquareCheckPosition.SQUARE_CHECK_HEIGHT;

	protected static final int CHECK_TICKET_IDENTITY_HEIGHT = 25;
	
	protected static final ContentPosition SPECIAL_CODE_PEOPLE_TOTAL_NUM = 
		new ContentPosition(1,CHECK_TICKET_IDENTITY_ROW + 3);

	protected static final ContentPosition SPECIAL_CODE_PEOPLE_TOTAL_NUM_ON_NUMBER_OF_PASSENGERS = 
		new ContentPosition(3,CHECK_TICKET_IDENTITY_ROW + 4);
		
	protected static final int SPECIAL_CODE_PEOPLE_NUM_FIRST_COL = 2;

	protected static final int SPECIAL_CODE_PEOPLE_NUM_FIRST_ROW = 
		CHECK_TICKET_IDENTITY_ROW+6;
	
	protected static final int SPECIAL_CODE_PEOPLE_NUM_COL_DISTANCE = 3;

	protected static final int SPECIAL_CODE_PEOPLE_NUM_ROW_DISTANCE = 1;
		
	protected static List<ContentPosition> getSpecialCodePeopleNumPositionList(
			int specialCodeItemCount){
		
		List<ContentPosition> specialCodePeopleNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<specialCodeItemCount;i++){
			
			int colIndex = i%2;
			int rowIndex = i/2;
			int currCol = SPECIAL_CODE_PEOPLE_NUM_FIRST_COL 
				+ colIndex*SPECIAL_CODE_PEOPLE_NUM_COL_DISTANCE;
			int currRow = SPECIAL_CODE_PEOPLE_NUM_FIRST_ROW + 
				rowIndex*SPECIAL_CODE_PEOPLE_NUM_ROW_DISTANCE;
			
			specialCodePeopleNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return specialCodePeopleNumPositionList;
	}
	
	protected static final ContentPosition YANZHENG_USING_FAKE_PAPER_NUM = 
		new ContentPosition(1,CHECK_TICKET_IDENTITY_ROW+18);

	protected static final ContentPosition ARREST_PEOPLE_NUM = 
		new ContentPosition(1,CHECK_TICKET_IDENTITY_ROW+22);

	protected static final ContentPosition FINE_PEOPLE_NUM = 
		new ContentPosition(3,CHECK_TICKET_IDENTITY_ROW+22);

	protected static final ContentPosition STUDY_PEOPLE_NUM = 
		new ContentPosition(5,CHECK_TICKET_IDENTITY_ROW+22);
		
}
