package com.ldp.security.sta.dao;

import com.ldp.security.sta.domain.StaPeriodSecurity;

public interface StaPeriodSecurityDao {

	public void save(StaPeriodSecurity staPeriodSecurity);
	
	public void delete(StaPeriodSecurity staPeriodSecurity);
	
	public StaPeriodSecurity loadById(long staPeriodSecurityId);
	
}
