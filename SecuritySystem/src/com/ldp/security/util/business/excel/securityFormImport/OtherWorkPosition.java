package com.ldp.security.util.business.excel.securityFormImport;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 其他需汇报的工作
 * @author Administrator
 *
 */
public class OtherWorkPosition {

	protected static final int OTHER_WORK_ROW = 
		ImportantJobPosition.IMPORTANT_JOB_ROW + ImportantJobPosition.IMPORTANT_JOB_HEIGHT;
	
	protected static final int OTHER_WORK_HEIGHT = 7;
		
	protected static final ContentPosition OTHER_WORK_INFO = 
		new ContentPosition(0,OTHER_WORK_ROW+2);
	
}
