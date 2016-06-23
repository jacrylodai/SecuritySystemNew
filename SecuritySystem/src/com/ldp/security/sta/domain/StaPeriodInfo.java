package com.ldp.security.sta.domain;

import java.util.List;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;

/**
 * 统计周期类
 * @author Administrator
 *
 */
public class StaPeriodInfo {
	
	public static final int STA_TYPE_WEEK = 'W';

	public static final int STA_TYPE_MONTH = 'M';

	public static final int STA_TYPE_CUSTOM = 'C';
	
	//最长统计时间为1年的时间
	public static final int MAX_LAST_DAYS = 370;

	//统计周期id
	private long staPeriodInfoId;

	//统计用户
	private User staUser;
	
	//创建统计的部门
	private Department creatorDepartment;

	//统计类型 W 周统计类 M 月统计类 C 自定义统计
	private int staType;

	//起始日期
	private String startDateString;

	//终止日期
	private String endDateString;

	//统计天数
	private int lastDays;
	
	//统计时间
	private String staTimeString;
	
	//包含车站下所有车间的统计，以及分车间的统计
	private List<StaPeriodSecurity> staPeriodSecurityList;	

	public long getStaPeriodInfoId() {
		return staPeriodInfoId;
	}

	public void setStaPeriodInfoId(long staPeriodInfoId) {
		this.staPeriodInfoId = staPeriodInfoId;
	}

	public User getStaUser() {
		return staUser;
	}

	public void setStaUser(User staUser) {
		this.staUser = staUser;
	}

	public Department getCreatorDepartment() {
		return creatorDepartment;
	}

	public void setCreatorDepartment(Department creatorDepartment) {
		this.creatorDepartment = creatorDepartment;
	}

	public int getStaType() {
		return staType;
	}

	public void setStaType(int staType) {
		this.staType = staType;
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

	public String getStaTimeString() {
		return staTimeString;
	}

	public void setStaTimeString(String staTimeString) {
		this.staTimeString = staTimeString;
	}

	public List<StaPeriodSecurity> getStaPeriodSecurityList() {
		return staPeriodSecurityList;
	}

	public void setStaPeriodSecurityList(
			List<StaPeriodSecurity> staPeriodSecurityList) {
		this.staPeriodSecurityList = staPeriodSecurityList;
	}
	
}
