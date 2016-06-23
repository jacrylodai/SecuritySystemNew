package com.ldp.security.sta.manager;

import java.util.List;

import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.util.PageModel;

public interface StaPeriodInfoManager {

	public void saveStaPeriodInfo(StaPeriodInfo staPeriodInfo);
	
	public StaPeriodInfo loadStaPeriodInfoById(long staPeriodInfoId);

	public void deleteStaPeriodInfoById(long staPeriodInfoId);
	
	/**
	 * 根据条件，得到统计周期类列表
	 * @param creatorDepartmentId 创建统计的部门id
	 * @return
	 */
	public List<StaPeriodInfo> getStaPeriodInfoListByCreatorDepartmentId(
			long creatorDepartmentId);

	/**
	 * 显示指定部门的所有统计周期信息，并进行分页显示
	 * @param creatorDepartmentId
	 * @param startDateString
	 * @param endDateString
	 * @return
	 */
	public PageModel<StaPeriodInfo> listStaPeriodInfoInPage(
			int staType,long creatorDepartmentId, String startDateString,
			String endDateString);

}
