package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class EquipmentPosition {

	protected static final int EQUIPMENT_ROW = 
		CCTVPosition.CCTV_ROW + CCTVPosition.CCTV_HEIGHT;
	
	protected static final int EQUIPMENT_HEIGHT = 5;

	protected static final ContentPosition EQUIPMENT_CHECK_NUM = 
		new ContentPosition(1,EQUIPMENT_ROW+2);
	
	protected static final ContentPosition EQUIPMENT_TROUBLE_NUM = 
		new ContentPosition(3,EQUIPMENT_ROW+2);
}
