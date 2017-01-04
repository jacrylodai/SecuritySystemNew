package com.ldp.security.util.business.excel.securityFormProperty;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 验证验票
 * @author Administrator
 *
 */
public class CheckTicketIdentityProperty {
	
	public static final String CHECK_TICKET_IDENTITY_BASE_POSITION_KEY = 
		"CHECK_TICKET_IDENTITY_BASE_POSITION";

	public static final String SPECIAL_CODE_PEOPLE_NUM_FIRST = "SPECIAL_CODE_PEOPLE_NUM_FIRST";
	
	private static final int SPECIAL_CODE_PEOPLE_NUM_COL_DISTANCE = 3;

	private static final int SPECIAL_CODE_PEOPLE_NUM_ROW_DISTANCE = 1;
		
	public static List<ContentPosition> getSpecialCodePeopleNumPositionList(
			int specialCodeItemCount,int itemFirstCol,int itemFirstRow){
		
		List<ContentPosition> specialCodePeopleNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<specialCodeItemCount;i++){
			
			int colIndex = i%2;
			int rowIndex = i/2;
			int currCol = itemFirstCol 
				+ colIndex*SPECIAL_CODE_PEOPLE_NUM_COL_DISTANCE;
			int currRow = itemFirstRow + 
				rowIndex*SPECIAL_CODE_PEOPLE_NUM_ROW_DISTANCE;
			
			specialCodePeopleNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return specialCodePeopleNumPositionList;
	}
	
	public static final String YANZHENG_USING_FAKE_PAPER_NUM = "YANZHENG_USING_FAKE_PAPER_NUM";

	public static final String ARREST_PEOPLE_NUM = "ARREST_PEOPLE_NUM";

	public static final String FINE_PEOPLE_NUM = "FINE_PEOPLE_NUM";

	public static final String STUDY_PEOPLE_NUM = "STUDY_PEOPLE_NUM";
		
}
