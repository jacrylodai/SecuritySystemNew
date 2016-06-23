package com.ldp.security.basedata.domain;

public class PeriodInfo {

	private long periodInfoId;
	
	private YearInfo yearInfo;
	
	private String startDateString;
	
	private String endDateString;
	
	private int lastDays;

	public long getPeriodInfoId() {
		return periodInfoId;
	}

	public void setPeriodInfoId(long periodInfoId) {
		this.periodInfoId = periodInfoId;
	}

	public YearInfo getYearInfo() {
		return yearInfo;
	}

	public void setYearInfo(YearInfo yearInfo) {
		this.yearInfo = yearInfo;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public int getLastDays() {
		return lastDays;
	}

	public void setLastDays(int lastDays) {
		this.lastDays = lastDays;
	}
	
}
