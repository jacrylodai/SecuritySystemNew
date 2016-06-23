package com.ldp.security.util.business.excel.securityFormImport;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 验证验票
 * @author Administrator
 *
 */
public class CheckTicketIdentityPosition {

	protected static final int CHECK_TICKET_IDENTITY_ROW = 
		CheckDangerousMachinePosition.CHECK_DANGEROUS_MACHINE_ROW + 
		CheckDangerousMachinePosition.CHECK_DANGEROUS_MACHINE_HEIGHT;

	protected static final int CHECK_TICKET_IDENTITY_HEIGHT = 23;
		
	protected static final int SPECIAL_CODE_PEOPLE_NUM_FIRST_COL = 2;

	protected static final int SPECIAL_CODE_PEOPLE_NUM_FIRST_ROW = 
		CHECK_TICKET_IDENTITY_ROW+4;
	
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
		new ContentPosition(1,CHECK_TICKET_IDENTITY_ROW+16);

	protected static final ContentPosition ARREST_PEOPLE_NUM = 
		new ContentPosition(1,CHECK_TICKET_IDENTITY_ROW+20);

	protected static final ContentPosition FINE_PEOPLE_NUM = 
		new ContentPosition(3,CHECK_TICKET_IDENTITY_ROW+20);

	protected static final ContentPosition STUDY_PEOPLE_NUM = 
		new ContentPosition(5,CHECK_TICKET_IDENTITY_ROW+20);
		
}
