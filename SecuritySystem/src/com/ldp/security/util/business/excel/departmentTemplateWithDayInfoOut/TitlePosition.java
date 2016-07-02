package com.ldp.security.util.business.excel.departmentTemplateWithDayInfoOut;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class TitlePosition {

	protected static final int TITLE_ROW = 0;
	
	protected static final int TITLE_HEIGHT = 9;
	
	protected static final ContentPosition REPORT_DEPARTMENT = 
		new ContentPosition(1,TITLE_ROW+2);

	protected static final ContentPosition REPORT_DEPARTMENT_ID = 
		new ContentPosition(1,TITLE_ROW+3);
	
	protected static final ContentPosition START_DATE_YEAR = 
		new ContentPosition(1,TITLE_ROW+5);

	protected static final ContentPosition START_DATE_MONTH = 
		new ContentPosition(2,TITLE_ROW+5);

	protected static final ContentPosition START_DATE_DAY = 
		new ContentPosition(3,TITLE_ROW+5);
	
	protected static final ContentPosition END_DATE_YEAR = 
		new ContentPosition(1,TITLE_ROW+6);

	protected static final ContentPosition END_DATE_MONTH = 
		new ContentPosition(2,TITLE_ROW+6);

	protected static final ContentPosition END_DATE_DAY = 
		new ContentPosition(3,TITLE_ROW+6);
}
