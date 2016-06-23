package com.ldp.security.report.dao;

import com.ldp.security.report.domain.SecurityForm;

public interface SecurityFormDao {

	public void saveSecurityForm(SecurityForm securityForm);
	
	public void updateSecurityForm(SecurityForm securityForm);
	
	public void deleteSecurityForm(SecurityForm securityForm);
	
	public SecurityForm loadSecurityFormById(long securityFormId);
	
}
