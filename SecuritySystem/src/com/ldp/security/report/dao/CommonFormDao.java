package com.ldp.security.report.dao;

import com.ldp.security.report.domain.CommonForm;

public interface CommonFormDao {

	public void saveCommonForm(CommonForm commonForm);
	
	public void updateCommonForm(CommonForm commonForm);
	
	public void deleteCommonForm(CommonForm commonForm);
	
}
