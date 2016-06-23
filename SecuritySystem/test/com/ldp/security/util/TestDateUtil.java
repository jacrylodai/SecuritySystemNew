package com.ldp.security.util;

import java.util.Date;

import junit.framework.TestCase;

import com.ldp.security.util.date.DateUtil;

public class TestDateUtil extends TestCase{

	
	public void testGetDateFromYearMonthDay(){
		
		Date date = DateUtil.getDateFromYearMonthDay(2016, 5, 23);
		String dateStr = DateUtil.formatDateToString(date);
		assertEquals("2016-05-23", dateStr);
	}
}
