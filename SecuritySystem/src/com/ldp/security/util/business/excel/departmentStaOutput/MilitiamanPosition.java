package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class MilitiamanPosition {

	protected static final int MILITIAMAN_ROW = 
		EquipmentPosition.EQUIPMENT_ROW + EquipmentPosition.EQUIPMENT_HEIGHT;
	
	protected static final int MILITIAMAN_HEIGHT = 5;

	protected static final ContentPosition MILITIAMAN_CHECK_NUM = 
		new ContentPosition(1,MILITIAMAN_ROW+2);
	
}
