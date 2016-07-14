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
	
	public void testDaysAfter(){
		
		String date = "2016-07-14";
		int daysAfter = 3;
		String estimateDate = "2016-07-17";
		
		String dateAfter = DateUtil.getSpecifiedDayAfter(date, daysAfter);
		assertEquals(estimateDate, dateAfter);
	}
	
}
