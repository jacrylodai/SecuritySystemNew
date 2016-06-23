package com.ldp.security.util.business.excel.securityFormImport;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 反恐重点工作开展情况
 * @author Administrator
 *
 */
public class ImportantJobPosition {

	protected static final int IMPORTANT_JOB_ROW = 
		MilitiamanPosition.MILITIAMAN_ROW + MilitiamanPosition.MILITIAMAN_HEIGHT;
	
	protected static final int IMPORTANT_JOB_HEIGHT = 5;
	
	protected static final ContentPosition TRAINNING_PEOPLE_NUM = 
		new ContentPosition(1,IMPORTANT_JOB_ROW+2);
	
	protected static final ContentPosition PRACTICE_PEOPLE_NUM = 
		new ContentPosition(3,IMPORTANT_JOB_ROW+2);
	
}
