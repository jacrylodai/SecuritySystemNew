package com.ldp.security.sta.manager;

import java.io.File;
import java.util.List;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.sta.domain.StaPeriodSecurity;
import com.ldp.security.util.PageModel;

public interface StaPeriodSecurityManager {

	public void saveStaPeriodSecurity(
			StaPeriodSecurity staPeriodSecurity);
	
	public StaPeriodSecurity loadStaPeriodSecurityById(long staPeriodSecurityId);

	/**
	 * 对指定的部门进行汇总统计，并级联统计其子部门，把结果保存进数据库中
	 * @param staPeriodInfo 统计周期类
	 * @param department 被统计的部门，如果部门是车间，就对车间进行统计，
	 * 		如果部门不是车间，那就先统计出其下子部门的数据，
	 * 		最后对子部门的数据求和就得到当前部门的数据
	 * @return
	 */
	public StaPeriodSecurity processDepartmentSta(StaPeriodInfo staPeriodInfo,
			Department department);

	/**
	 * 根据相应的条件，搜索出统计的主信息，
	 * 如重庆车站对其所有的自定义统计的主信息进行显示
	 * @param startDateString
	 * @param endDateString
	 * @param staType 统计方式
	 * @param creatorDepartmentId 创建统计的部门
	 * @return
	 */
	public PageModel<StaPeriodSecurity> listMainStaPeriodSecurityInPage(
			String startDateString, String endDateString, int staType,
			long staDepartmentId);

	/**
	 * 根据相应的条件，找出对应的反恐周期统计表
	 * @param staPeriodInfoId
	 * @param staDepartmentId 被统计的部门id
	 * @return
	 */
	public StaPeriodSecurity getStaPeriodSecurityByStaPeriodInfoIdStaDepartmentId(
			long staPeriodInfoId, long staDepartmentId);
	
	/**
	 * 根据当前主部门id，检索出其子部门的统计结果，并进行分页，
	 * 在显示时，既可以显示主部门，有可显示子部门
	 * @param staPeriodInfoId 反恐周期类id
	 * @param staParentDepartmentId 当前的主部门id部门
	 * @return
	 */
	public PageModel<StaPeriodSecurity> listSubStaPeriodSecurityInPage(
			long staPeriodInfoId,long staParentDepartmentId);

	/**
	 * 删除统计，会删除每一条反恐周期统计类，及其对应的公共统计类及客运统计类
	 * ，最后删除统计周期类
	 * @param staPeriodInfoId 统计周期id
	 */
	public void deleteStaPeriodSecurityAndInfoByStaPeriodInfoId(
			long staPeriodInfoId);

	/**
	 * 根据反恐周期统计信息，生成excel统计报表，
	 * 取出统计结果，并输出生成excel文件
	 * @param staPeriodInfo 统计周期
	 * @param department 被统计的部门
	 * @param sourceExcelReportTemplateFile 反恐统计报表模板文件
	 * @param currExcelReportFolderPath 当前的统计报表所存放的文件路径
	 */
	public void processDepartmentStaExcelReport(StaPeriodInfo staPeriodInfo,
			Department department,File sourceExcelReportTemplateFile
			, String currExcelReportFolderPath);
	

	/**
	 * 
	 * @param staDepartmentId 被统计的部门id
	 * @return
	 */
	public List<StaPeriodSecurity> getStaPeriodSecurityListByStaDepartmentId(
			long staDepartmentId);

	public void deleteStaPeriodSecurity(StaPeriodSecurity staPeriodSecurity);
	
}
