package com.ldp.security.report.domain;

import java.text.DecimalFormat;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.validate.ClientValidate;

/**
 * 反恐填报表
 * @author Administrator
 *
 */
public class SecurityForm {
	
	public static final int NOMAL_TEXT_SIZE = 500;
	
	public static final int LONG_TEXT_SIZE = 1000;
	
	/**
	 * 导入自excel的填报表
	 */
	public static final int STATE_IMPORT = 'I';
	
	/**
	 * 未上报
	 */
	public static final int STATE_NOT_CONFIRM = 'N';
	
	/**
	 * 已上报
	 */
	public static final int STATE_CONFIRM = 'C';
		
	/**
	 * 已审核
	 */
	public static final int STATE_VERIFY = 'V';

	//报表统计天数最多为31天
	public static final int MAX_LAST_DAYS = 31;

	//反恐填报表id
	private long securityFormId;
	
	//车间
	private Department department;
	
	//车站
	private Department station;
	
	//创建用户
	private User reportUser;

	//状态 N-未确认 C-已确认 V-已审核
	private int state;
	
	//统计起始日期串
	private String startDateString;
	
	//统计结束日期串	
	private String endDateString;
	
	//统计天数
	private int lastDays;
	
	//统计报表创建时间串
	private String reportTimeString;
	
	//公共填报表
	private CommonForm commonForm;
	
	//客运填报表
	private KeyunForm keyunForm;
	
	//错误信息，在反恐填报表导入时使用
	private String errorMessage;

	public long getSecurityFormId() {
		return securityFormId;
	}

	public void setSecurityFormId(long securityFormId) {
		this.securityFormId = securityFormId;
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

	public User getReportUser() {
		return reportUser;
	}

	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public String getReportTimeString() {
		return reportTimeString;
	}

	public void setReportTimeString(String reportTimeString) {
		this.reportTimeString = reportTimeString;
	}

	public CommonForm getCommonForm() {
		return commonForm;
	}

	public void setCommonForm(CommonForm commonForm) {
		this.commonForm = commonForm;
	}

	public KeyunForm getKeyunForm() {
		return keyunForm;
	}

	public void setKeyunForm(KeyunForm keyunForm) {
		this.keyunForm = keyunForm;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SecurityForm Info:\nid:"+securityFormId);
		sb.append("\ndepartmentId:"+department.getDepartmentId());
		sb.append("\nstartDateString:"+startDateString);
		sb.append("\nendDateString:"+endDateString);
		return sb.toString();
	}
	
	public void validateData() {

		if(!ClientValidate.isDate(startDateString)){
			throw new RuntimeException("报表起始日期格式错误");
		}

		if(!ClientValidate.isDate(endDateString)){
			throw new RuntimeException("报表终止日期格式错误");
		}
		
		if(DateUtil.compareTowDate(startDateString, endDateString) == 1){
			throw new RuntimeException("报表起始日期必须小于或等于报表终止日期");
		}
		
		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		if(lastDays>SecurityForm.MAX_LAST_DAYS){
			throw new RuntimeException(
					"统计天数最多只能是"+SecurityForm.MAX_LAST_DAYS+"天");
		}
		
	}

	/**
	 * 安检违禁品总数量占旅客发送人数比例
	 * @return
	 */
	public String getCheckDangerousObjectTotalNumOnPeriodNumOfPassengers() {
		
		int checkDangerousObjectTotalNum = 
			commonForm.getCheckDangerousObjectTotalNum();
		long periodNumOfPassengers = keyunForm.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				checkDangerousObjectTotalNum, periodNumOfPassengers);
	}

	/**
	 * 预检违禁品总数量占旅客发送人数比例
	 * @return
	 */
	public String getSquareDangerousObjectTotalNumOnPeriodNumOfPassengers() {
		
		int squareDangerousObjectTotalNum = 
			keyunForm.getSquareDangerousObjectTotalNum();
		long periodNumOfPassengers = keyunForm.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				squareDangerousObjectTotalNum, periodNumOfPassengers);
	}

	/**
	 * 查缉一体总人数占旅客发送人数比例
	 * @return
	 */
	public String getSpecialCodePeopleTotalNumOnPeriodNumOfPassengers() {
		
		int specialCodePeopleTotalNum = 
			keyunForm.getSpecialCodePeopleTotalNum();
		long periodNumOfPassengers = keyunForm.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				specialCodePeopleTotalNum, periodNumOfPassengers);
	}
	
}
