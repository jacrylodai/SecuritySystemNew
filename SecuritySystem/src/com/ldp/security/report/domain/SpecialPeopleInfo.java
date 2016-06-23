package com.ldp.security.report.domain;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;

/**
 * 重点关注人员信息表
 * @author Administrator
 *
 */
public class SpecialPeopleInfo {

	/**
	 * 未上报
	 */
	public static final int STATE_NOT_CONFRIM = 'N';
	
	/**
	 * 已上报
	 */
	public static final int STATE_CONFIRM = 'C';
	
	//重点关注人员信息id
	private long specialPeopleInfoId;

	//上报用户
	private User reportUser;

	//车间
	private Department department;

	//车站
	private Department station;
	
	//状态 N-未确认 C-已确认
	private int state;

	//购票时间
	private String buyTicketTimeString;
	
	//统计日期 统计日期为购票时间日期
	private String staDateString;

	//发站
	private String startLocation;

	//到站
	private String destination;

	//车次
	private String trainNumber;

	//开车时间
	private String trainStartTimeString;
	
	//旅客姓名
	private String passengerName;

	//旅客身份证
	private String passengerIdentity;

	//票号
	private String ticketNumber;
	
	//席位号
	private String sitNumber;
	
	//上报时间
	private String reportTimeString;

	public long getSpecialPeopleInfoId() {
		return specialPeopleInfoId;
	}

	public void setSpecialPeopleInfoId(long specialPeopleInfoId) {
		this.specialPeopleInfoId = specialPeopleInfoId;
	}

	public String getBuyTicketTimeString() {
		return buyTicketTimeString;
	}

	public void setBuyTicketTimeString(String buyTicketTimeString) {
		this.buyTicketTimeString = buyTicketTimeString;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainStartTimeString() {
		return trainStartTimeString;
	}

	public void setTrainStartTimeString(String trainStartTimeString) {
		this.trainStartTimeString = trainStartTimeString;
	}

	public User getReportUser() {
		return reportUser;
	}

	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getStation() {
		return station;
	}

	public void setStation(Department station) {
		this.station = station;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStaDateString() {
		return staDateString;
	}

	public void setStaDateString(String staDateString) {
		this.staDateString = staDateString;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerIdentity() {
		return passengerIdentity;
	}

	public void setPassengerIdentity(String passengerIdentity) {
		this.passengerIdentity = passengerIdentity;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getSitNumber() {
		return sitNumber;
	}

	public void setSitNumber(String sitNumber) {
		this.sitNumber = sitNumber;
	}

	public String getReportTimeString() {
		return reportTimeString;
	}

	public void setReportTimeString(String reportTimeString) {
		this.reportTimeString = reportTimeString;
	}
	
}
