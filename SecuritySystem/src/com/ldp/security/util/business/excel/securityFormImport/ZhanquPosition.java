package com.ldp.security.util.business.excel.securityFormImport;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 站区封闭
 * @author Administrator
 *
 */
public class ZhanquPosition {

	protected static final int ZHANQU_ROW = 
		CheckDangerousPosition.CHECK_DANGEROUS_ROW 
		+ CheckDangerousPosition.CHECK_DANGEROUS_HEIGHT;
	
	protected static final int ZHANQU_HEIGHT = 5;
	
	protected static final ContentPosition ZHANQU_CHECK_NUM = 
		new ContentPosition(1,ZHANQU_ROW+2);
}
