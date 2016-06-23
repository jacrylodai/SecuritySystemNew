package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class SaleTicketPosition {

	protected static final int SALE_TICKET_ROW = 
		CheckTicketIdentityPosition.CHECK_TICKET_IDENTITY_ROW 
		+ CheckTicketIdentityPosition.CHECK_TICKET_IDENTITY_HEIGHT;

	protected static final int SALE_TICKET_HEIGHT = 5;
	
	protected static final ContentPosition SALE_USING_FAKE_PAPER_NUM = 
		new ContentPosition(1,SALE_TICKET_ROW+2);
	
	protected static final ContentPosition SALE_SPECIAL_PEOPLE_NUM = 
		new ContentPosition(3,SALE_TICKET_ROW+2);
	
}
