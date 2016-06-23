package com.ldp.security.sta.dao;

import com.ldp.security.sta.domain.StaPeriodInfo;

public interface StaPeriodInfoDao {

	public void saveStaPeriodInfo(StaPeriodInfo staPeriodInfo);

	public void deleteStaPeriodInfo(StaPeriodInfo staPeriodInfo);
	
	public StaPeriodInfo loadStaPeriodInfoById(long staPeriodInfoId);
	
}
