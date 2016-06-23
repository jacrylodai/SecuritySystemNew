package com.ldp.security.sta.manager;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.sta.dao.StaPeriodInfoDao;
import com.ldp.security.sta.domain.StaPeriodInfo;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.database.ParameterObject;
import com.ldp.security.util.validate.ClientValidate;

public class StaPeriodInfoManagerImpl extends AbstractManager<StaPeriodInfo>
	implements StaPeriodInfoManager{

	private StaPeriodInfoDao staPeriodInfoDao;
	
	public void setStaPeriodInfoDao(StaPeriodInfoDao staPeriodInfoDao) {
		this.staPeriodInfoDao = staPeriodInfoDao;
	}

	public StaPeriodInfo loadStaPeriodInfoById(long staPeriodInfoId) {

		return staPeriodInfoDao.loadStaPeriodInfoById(staPeriodInfoId);
	}

	public void saveStaPeriodInfo(StaPeriodInfo staPeriodInfo) {

		staPeriodInfoDao.saveStaPeriodInfo(staPeriodInfo);
	}

	public void deleteStaPeriodInfoById(long staPeriodInfoId) {

		StaPeriodInfo staPeriodInfo = staPeriodInfoDao.loadStaPeriodInfoById(staPeriodInfoId);
		staPeriodInfoDao.deleteStaPeriodInfo(staPeriodInfo);
	}

	public List<StaPeriodInfo> getStaPeriodInfoListByCreatorDepartmentId(
			long creatorDepartmentId) {

		String hql = "from StaPeriodInfo staPeriodInfo" +
				" where staPeriodInfo.creatorDepartment.departmentId=:creatorDepartmentId";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("creatorDepartmentId",creatorDepartmentId));
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public PageModel<StaPeriodInfo> listStaPeriodInfoInPage(
			int staType,long creatorDepartmentId, String startDateString,
			String endDateString) {
		
		StringBuffer sb = new StringBuffer();
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		
		sb.append(
				"from StaPeriodInfo staPeriodInfo" +
				" where staPeriodInfo.staType=:staType");
		paraObjList.add(new ParameterObject("staType",staType));
		
		sb.append(" and staPeriodInfo.creatorDepartment.departmentId=:creatorDepartmentId");
		paraObjList.add(new ParameterObject("creatorDepartmentId",creatorDepartmentId));
		
		if(!ClientValidate.isEmpty(startDateString)){
			sb.append(" and staPeriodInfo.startDateString>=:startDateString");
			paraObjList.add(new ParameterObject("startDateString",startDateString));
		}

		if(!ClientValidate.isEmpty(endDateString)){
			sb.append(" and staPeriodInfo.endDateString<=:endDateString");
			paraObjList.add(new ParameterObject("endDateString",endDateString));
		}
		
		sb.append(" order by staPeriodInfo.staTimeString desc");
		
		return findDataByHqlParameterListInPage(sb.toString(), paraObjList);
	}

}
