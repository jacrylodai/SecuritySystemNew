package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class WaitingHallCheckPosition {

	protected static final int WAITING_HALL_CHECK_ROW = 
		SaleTicketPosition.SALE_TICKET_ROW + SaleTicketPosition.SALE_TICKET_HEIGHT;
	
	protected static final int WAITING_HALL_CHECK_HEIGHT = 5;
	
	protected static final ContentPosition PERIOD_NUMBER_OF_PASSENGERS = 
		new ContentPosition(1,WAITING_HALL_CHECK_ROW + 2);
	
	protected static final ContentPosition WAITING_HALL_CHECK_PEOPLE_NUM = 
		new ContentPosition(3,WAITING_HALL_CHECK_ROW + 2);

	protected static final ContentPosition WAITING_HALL_XIANZA_PEOPLE_NUM = 
		new ContentPosition(5,WAITING_HALL_CHECK_ROW + 2);
	
}
