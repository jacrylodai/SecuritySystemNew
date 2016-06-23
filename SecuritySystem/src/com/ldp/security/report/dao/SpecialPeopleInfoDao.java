package com.ldp.security.report.dao;

import com.ldp.security.report.domain.SpecialPeopleInfo;

public interface SpecialPeopleInfoDao {

	public void saveSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo);
	
	public void updateSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo);
	
	public SpecialPeopleInfo loadSpecialPeopleInfo(long specialPeopleInfoId);
	
	public void deleteSpecialPeopleInfo(SpecialPeopleInfo specialPeopleInfo);
	
}
