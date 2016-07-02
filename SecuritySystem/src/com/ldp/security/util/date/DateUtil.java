package com.ldp.security.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 时间格式
	 */
	public static final String TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:ss:mm";

	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	
	private static final Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 得到当前时间的字符串
	 * @return
	 */
	public static String getCurrentTimeString() {

		Date date = new Date();
		SimpleDateFormat dateF = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return dateF.format(date);
	}

	/**
	 * 取得指定日期之前几天的日期
	 * @param specifiedDay
	 * @param days
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int days) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		if (days < 0) {
			throw new RuntimeException("指定的天数必须大于或等于0");
		}
		if (days == 0) {
			return specifiedDay;
		}

		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_PATTERN)
					.parse(specifiedDay);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - days);

		String dayBefore = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后几天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, int days) {

		if (days < 0) {
			throw new RuntimeException("指定的天数必须大于或等于0");
		}
		if (days == 0) {
			return specifiedDay;
		}
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_PATTERN)
					.parse(specifiedDay);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + days);

		String dayAfter = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(c
				.getTime());
		return dayAfter;
	}

	/**
	 * 比较2个日期，date1小于date2返回-1
	 * 
	 * @param dateString1
	 * @param dateString2
	 * @return
	 */
	public static int compareTowDate(String dateString1, String dateString2) {

		SimpleDateFormat dateF = new SimpleDateFormat(DATE_FORMAT_PATTERN);

		if (dateString1.equals(dateString2)) {
			return 0;
		}

		Date date1;
		Date date2;
		try {
			date1 = dateF.parse(dateString1);
			date2 = dateF.parse(dateString2);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}

		if (date1.before(date2)) {
			return -1;
		}
		if (date1.after(date2)) {
			return 1;
		}
		return 0;
	}

	/**
	 * 取得2个日期之间间隔的天数
	 * @param startDateString
	 * @param endDateString
	 * @return
	 */
	public static int getIntervalDaysByDateString(String startDateString,
			String endDateString) {

		SimpleDateFormat dateF = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		Date startDate;
		Date endDate;

		try {
			startDate = dateF.parse(startDateString);
			endDate = dateF.parse(endDateString);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}

		return getIntervalDays(startDate, endDate);
	}

	/**
	 * 取得2个日期之间间隔的天数，都转化为毫秒值，在把相差的毫秒值转化为天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {

		if (null == startDate || null == endDate) {
			return -1;
		}

		long intervalMilli = endDate.getTime() - startDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * 把时间字符串转换为时间类
	 * @param timeString
	 * @return
	 */
	public static Date parseTimeFromTimeString(String timeString){
		
		SimpleDateFormat dateF = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		Date time = null;
		try {
			time = dateF.parse(timeString);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
		return time;
	}
	
	public static Date parseDateFromDateString(String dateString){
		
		SimpleDateFormat dateF = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		Date date = null;
		try {
			date = dateF.parse(dateString);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
		return date;
	}
	
	public static String formatDateToString(Date date){
		
		SimpleDateFormat dateF = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return dateF.format(date);
	}
	
	public static String formatTimeToString(Date time){
		
		SimpleDateFormat dateF = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return dateF.format(time);
	}

	/**
	 * 根据条件得到相应的日期
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return
	 */
	public static Date getDateFromYearMonthDay(int year,int month,int day){
		
		if(year<1800 || year>2200){
			throw new RuntimeException("年的值范围错误，当前值：");
		}
		if(month<1 || month>12){
			throw new RuntimeException("月的值应介于1-12，当前值："+month);
		}
		if(day<1 || day>31){
			throw new RuntimeException("日的值应介于1-31，当前值："+day);
		}
		
		String dateStr = year+"-"+month+"-"+day;
		return parseDateFromDateString(dateStr);
	}
	
	public static int getCurrentYear(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		
		int currYear = calendar.get(Calendar.YEAR);
		return currYear;
	}

}
