package com.ldp.security.report.manager;

import com.ldp.security.report.domain.SpecialPeopleInfo;
import com.ldp.security.util.PageModel;

public interface SpecialPeopleInfoManager {

	public void saveSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo);
	
	public void updateSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo);
	
	public SpecialPeopleInfo loadSpecialPeopleInfo(long specialPeopleInfoId);
	
	public void deleteSpecialPeopleInfoById(long specialPeopleInfoId);

	public PageModel<SpecialPeopleInfo> listDepartmentSpecialPeopleInfo(
			long departmentId, int state);
	
}
