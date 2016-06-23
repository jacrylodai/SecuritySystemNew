package com.ldp.security.report.actionform;

import org.apache.struts.action.ActionForm;

/**
 * 重点关注人员信息表
 * @author Administrator
 *
 */
public class SpecialPeopleInfoActionForm extends ActionForm{

	//重点关注人员信息id
	private long specialPeopleInfoId;
	
	//购票时间
	private String buyTicketTimeString;

	//发站
	private String startLocation;

	//到站
	private String destination;

	//车次
	private String trainNumber;

	//开车时间
	private String trainStartTimeString;

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

	public void validateData(){
		
	}
	
}
