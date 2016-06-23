package com.ldp.security.report.manager;

import java.io.File;
import java.util.List;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.util.PageModel;

public interface SecurityFormManager {
	
	public SecurityForm loadSecurityFormById(long securityFormId);

	public void saveSecurityForm(SecurityForm securityForm);
	
	/**
	 * 根据部门id，检查此起始日期到结束日期是否和数据库中的数据冲突
	 * 如果冲突返回true，不冲突返回false
	 * @param departmentId
	 * @param startDateString
	 * @param endDateString
	 * @return
	 */
	public boolean checkConflictDateByDepartmentId(long departmentId
			,String startDateString,String endDateString);

	/**
	 * 根据部门id，检查此起始日期到结束日期是否和数据库中的数据冲突，但要把自己排除在外
	 * ，用于修改功能，如果冲突返回true，不冲突返回false
	 * @param securityFormId 自身id
	 * @param departmentId
	 * @param startDateString
	 * @param endDateString
	 * @return
	 */
	public boolean checkConflictDateWithoutMyselfByDepartmentId(
			long securityFormId,long departmentId
			,String startDateString,String endDateString);

	public void updateSecurityForm(SecurityForm securityForm);

	/**
	 * 根据id删除反恐填报表，彻底删除数据，分别删除公共与客运填报表
	 * @param securityFormId
	 */
	public void deleteSecurityFormByEraseDataById(long securityFormId);
	
	/**
	 * 根据反恐填报表id，修改它的状态
	 * @param securityFormId
	 * @param originState 原始状态
	 * @param newState 新状态
	 */
	public void updateSecurityFormStateById(long securityFormId
			,int originState,int newState);

	public void processSecurityFormImport(User reportUser,File sourceFile);

	/**
	 * 根据条件进行分页查询
	 * @param stationId 车站id
	 * @param departmentId 车间id
	 * @param startDateString 起始日期
	 * @param endDateString 结束日期
	 * @param state 反恐填报表状态
	 * @return
	 */
	public PageModel<SecurityForm> listSecurityFormByParam(Long stationId,
			Long departmentId, String startDateString, String endDateString,
			Integer state);

	/**
	 * 根据条件返回列表数据
	 * @param stationId 车站id
	 * @param departmentId 车间id
	 * @param startDateString 起始日期
	 * @param endDateString 结束日期
	 * @param state 反恐填报表状态
	 * @return
	 */
	public List<SecurityForm> getSecurityFormListByParam(Long stationId,
			Long departmentId, String startDateString, String endDateString,
			Integer state);
}
