package com.ldp.security.report.manager;

import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.report.dao.SpecialPeopleInfoDao;
import com.ldp.security.report.domain.SpecialPeopleInfo;
import com.ldp.security.util.PageModel;

public class SpecialPeopleInfoManagerImpl extends AbstractManager<SpecialPeopleInfo>
	implements SpecialPeopleInfoManager {

	private SpecialPeopleInfoDao specialPeopleInfoDao;
	
	public void setSpecialPeopleInfoDao(SpecialPeopleInfoDao specialPeopleInfoDao) {
		this.specialPeopleInfoDao = specialPeopleInfoDao;
	}

	public void deleteSpecialPeopleInfoById(long specialPeopleInfoId) {
		SpecialPeopleInfo specialPeopleInfo = 
			specialPeopleInfoDao.loadSpecialPeopleInfo(specialPeopleInfoId);
		specialPeopleInfoDao.deleteSpecialPeopleInfo(specialPeopleInfo);
	}

	public SpecialPeopleInfo loadSpecialPeopleInfo(long specialPeopleInfoId) {

		return specialPeopleInfoDao.loadSpecialPeopleInfo(specialPeopleInfoId);
	}

	public void saveSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo) {

		specialPeopleInfoDao.saveSpecialPeopleInfo(specialPeopleInfo);
	}

	public void updateSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo) {

		specialPeopleInfoDao.updateSpecialPeopleInfo(specialPeopleInfo);
	}

	public PageModel<SpecialPeopleInfo> listDepartmentSpecialPeopleInfo(
			long departmentId, int state) {

		return findDataByHqlInPage(
				"from SpecialPeopleInfo specialPeopleInfo" +
				" where specialPeopleInfo.department.departmentId=?" +
				" and specialPeopleInfo.state=?" +
				" order by specialPeopleInfo.reportTimeString desc"
				, new Object[]{ departmentId,state });
	}

}
