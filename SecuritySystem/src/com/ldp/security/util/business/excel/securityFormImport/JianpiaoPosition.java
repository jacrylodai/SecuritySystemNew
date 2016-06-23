package com.ldp.security.util.business.excel.securityFormImport;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 检票口
 * @author Administrator
 *
 */
public class JianpiaoPosition {

	protected static final int JIANPIAO_ROW = 
		WaitingHallCheckPosition.WAITING_HALL_CHECK_ROW 
		+ WaitingHallCheckPosition.WAITING_HALL_CHECK_HEIGHT;
	
	protected static final ContentPosition JIANPIAO_WEISUI_PEOPLE_NUM = 
		new ContentPosition(1,JIANPIAO_ROW + 2);

	protected static final ContentPosition SPECIAL_TRAIN_IDENTITY_WRONG_PEOPLE_NUM = 
		new ContentPosition(3,JIANPIAO_ROW + 2);

	protected static final ContentPosition SPECIAL_TRAIN_IDENTITY_WRONG_INFO = 
		new ContentPosition(0,JIANPIAO_ROW + 7);
	
}
