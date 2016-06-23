package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class TitlePosition {

	protected static final int TITLE_ROW = 0;
	
	protected static final int TITLE_HEIGHT = 13;
	
	protected static final ContentPosition DEPARTMENT_NAME = 
		new ContentPosition(1,TITLE_ROW + 2);
	
	protected static final ContentPosition STA_START_DATE_STRING = 
		new ContentPosition(1,TITLE_ROW + 6);
	
	protected static final ContentPosition STA_END_DATE_STRING = 
		new ContentPosition(3,TITLE_ROW + 6);
	
	protected static final ContentPosition STA_LAST_DAYS = 
		new ContentPosition(5,TITLE_ROW + 6);

	protected static final ContentPosition STA_TIME_STRING = 
		new ContentPosition(1,TITLE_ROW + 7);

	protected static final ContentPosition STA_USER_USERNAME = 
		new ContentPosition(3,TITLE_ROW + 7);

	protected static final ContentPosition ESTIMATE_STA_DAYS = 
		new ContentPosition(1,TITLE_ROW + 11);

	protected static final ContentPosition ACTUAL_STA_DAYS = 
		new ContentPosition(3,TITLE_ROW + 11);
	
}
