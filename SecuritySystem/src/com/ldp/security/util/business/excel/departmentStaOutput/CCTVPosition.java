package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class CCTVPosition {

	protected static final int CCTV_ROW = 
		ZhanquPosition.ZHANQU_ROW + ZhanquPosition.ZHANQU_HEIGHT;
	
	protected static final int CCTV_HEIGHT = 5;
	
	protected static final ContentPosition CCTV_CHECK_NUM = 
		new ContentPosition(1,CCTV_ROW+2);
	
	protected static final ContentPosition CCTV_TROUBLE_NUM = 
		new ContentPosition(3,CCTV_ROW+2);
	
}
