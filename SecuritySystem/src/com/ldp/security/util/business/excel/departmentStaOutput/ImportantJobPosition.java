package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class ImportantJobPosition {

	protected static final int IMPORTANT_JOB_ROW = 
		MilitiamanPosition.MILITIAMAN_ROW + MilitiamanPosition.MILITIAMAN_HEIGHT;
	
	protected static final int IMPORTANT_JOB_HEIGHT = 6;

	protected static final ContentPosition TRAINNING_COUNT = 
		new ContentPosition(1,IMPORTANT_JOB_ROW+2);
	
	protected static final ContentPosition TRAINNING_PEOPLE_NUM = 
		new ContentPosition(3,IMPORTANT_JOB_ROW+2);
	
	protected static final ContentPosition PRACTICE_COUNT = 
		new ContentPosition(1,IMPORTANT_JOB_ROW+3);
	
	protected static final ContentPosition PRACTICE_PEOPLE_NUM = 
		new ContentPosition(3,IMPORTANT_JOB_ROW+3);
	
}
