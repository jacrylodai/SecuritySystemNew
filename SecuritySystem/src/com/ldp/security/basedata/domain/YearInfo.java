package com.ldp.security.basedata.domain;

public class YearInfo {

	private long yearInfoId;
	
	private int year;
	
	private int staType;
	
	private int weekStaPoint;

	public long getYearInfoId() {
		return yearInfoId;
	}

	public void setYearInfoId(long yearInfoId) {
		this.yearInfoId = yearInfoId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStaType() {
		return staType;
	}

	public void setStaType(int staType) {
		this.staType = staType;
	}

	public int getWeekStaPoint() {
		return weekStaPoint;
	}

	public void setWeekStaPoint(int weekStaPoint) {
		this.weekStaPoint = weekStaPoint;
	}	
	
}
