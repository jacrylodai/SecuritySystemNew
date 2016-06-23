package com.ldp.security.sta.actionform;

import org.apache.struts.action.ActionForm;

import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.validate.ClientValidate;

public class StaPeriodSecurityActionForm extends ActionForm{

	//反恐统计表id
	private long staPeriodSecurityId;
	
	//统计周期类id
	private long staPeriodInfoId;
	
	//被统计的部门id
	private long staDepartmentId;
	
	//查看统计详情时，主被统计部门id
	private long staParentDepartmentId;

	//起始日期
	private String startDateString;

	//终止日期
	private String endDateString;
	
	private long[] selectFlag;

	public long getStaPeriodInfoId() {
		return staPeriodInfoId;
	}

	public void setStaPeriodInfoId(long staPeriodInfoId) {
		this.staPeriodInfoId = staPeriodInfoId;
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

	public long getStaPeriodSecurityId() {
		return staPeriodSecurityId;
	}

	public void setStaPeriodSecurityId(long staPeriodSecurityId) {
		this.staPeriodSecurityId = staPeriodSecurityId;
	}

	public long getStaDepartmentId() {
		return staDepartmentId;
	}

	public void setStaDepartmentId(long staDepartmentId) {
		this.staDepartmentId = staDepartmentId;
	}

	public long getStaParentDepartmentId() {
		return staParentDepartmentId;
	}

	public void setStaParentDepartmentId(long staParentDepartmentId) {
		this.staParentDepartmentId = staParentDepartmentId;
	}

	public long[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(long[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public void validateData() {

		if(!ClientValidate.isDate(startDateString)){
			throw new RuntimeException("统计起始日期格式错误");
		}

		if(!ClientValidate.isDate(endDateString)){
			throw new RuntimeException("统计终止日期格式错误");
		}
		
		if(DateUtil.compareTowDate(startDateString, endDateString) == 1){
			throw new RuntimeException("统计起始日期必须小于或等于统计终止日期");
		}
		
	}
	
}
