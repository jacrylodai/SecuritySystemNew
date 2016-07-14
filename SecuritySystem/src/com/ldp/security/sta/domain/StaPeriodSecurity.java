package com.ldp.security.sta.domain;

import java.util.List;

import com.google.gson.Gson;
import com.ldp.security.basedata.domain.Department;
import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.gson.GsonUtil;

/**
 * 反恐周期统计类
 * @author Administrator
 *
 */
public class StaPeriodSecurity {

	private static final Gson gson = new Gson();

	//id
	private long staPeriodSecurityId;
	
	//被统计的部门
	private Department staDepartment;

	//统计周期类
	private StaPeriodInfo staPeriodInfo;

	//
	private CommonSta commonSta;
	
	//
	private KeyunSta keyunSta;
	
	//预期统计天数
	private int estimateStaDays;
	
	//实际统计天数
	private int actualStaDays;
	
	//缺失的日期串
	private String absentDaysString;
	
	//缺失的日期列表
	private List<String> absentDaysList;

	public long getStaPeriodSecurityId() {
		return staPeriodSecurityId;
	}

	public void setStaPeriodSecurityId(long staPeriodSecurityId) {
		this.staPeriodSecurityId = staPeriodSecurityId;
	}

	public Department getStaDepartment() {
		return staDepartment;
	}

	public void setStaDepartment(Department staDepartment) {
		this.staDepartment = staDepartment;
	}

	public StaPeriodInfo getStaPeriodInfo() {
		return staPeriodInfo;
	}

	public void setStaPeriodInfo(StaPeriodInfo staPeriodInfo) {
		this.staPeriodInfo = staPeriodInfo;
	}

	public CommonSta getCommonSta() {
		return commonSta;
	}

	public void setCommonSta(CommonSta commonSta) {
		this.commonSta = commonSta;
	}

	public KeyunSta getKeyunSta() {
		return keyunSta;
	}

	public void setKeyunSta(KeyunSta keyunSta) {
		this.keyunSta = keyunSta;
	}

	public int getEstimateStaDays() {
		return estimateStaDays;
	}

	public void setEstimateStaDays(int estimateStaDays) {
		this.estimateStaDays = estimateStaDays;
	}

	public int getActualStaDays() {
		return actualStaDays;
	}

	public void setActualStaDays(int actualStaDays) {
		this.actualStaDays = actualStaDays;
	}
	
	public String getAbsentDaysString() {
		return absentDaysString;
	}

	public void setAbsentDaysString(String absentDaysString) {
		this.absentDaysString = absentDaysString;
		absentDaysList = GsonUtil.convertFromGsonToListString(absentDaysString);
	}

	public List<String> getAbsentDaysList() {
		return absentDaysList;
	}

	public void setAbsentDaysList(List<String> absentDaysList) {
		this.absentDaysList = absentDaysList;
		absentDaysString = gson.toJson(absentDaysList);
	}

	/**
	 * 安检违禁品总数量占旅客发送人数比例
	 * @return
	 */
	public String getCheckDangerousObjectTotalNumOnPeriodNumOfPassengers() {
		
		int checkDangerousObjectTotalNum = 
			commonSta.getCheckDangerousObjectTotalNum();
		long periodNumOfPassengers = keyunSta.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				checkDangerousObjectTotalNum, periodNumOfPassengers);
	}

	/**
	 * 预检违禁品总数量占旅客发送人数比例
	 * @return
	 */
	public String getSquareDangerousObjectTotalNumOnPeriodNumOfPassengers() {
		
		int squareDangerousObjectTotalNum = 
			keyunSta.getSquareDangerousObjectTotalNum();
		long periodNumOfPassengers = keyunSta.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				squareDangerousObjectTotalNum, periodNumOfPassengers);
	}

	/**
	 * 查缉一体总人数占旅客发送人数比例
	 * @return
	 */
	public String getSpecialCodePeopleTotalNumOnPeriodNumOfPassengers() {
		
		int specialCodePeopleTotalNum = 
			keyunSta.getSpecialCodePeopleTotalNum();
		long periodNumOfPassengers = keyunSta.getPeriodNumberOfPassengers();
		return CaculateUtil.caculateNumAOnNumB(
				specialCodePeopleTotalNum, periodNumOfPassengers);
	}
	
	
}
