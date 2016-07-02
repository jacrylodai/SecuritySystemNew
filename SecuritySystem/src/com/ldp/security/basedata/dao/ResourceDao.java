package com.ldp.security.basedata.dao;

import com.ldp.security.basedata.domain.Resource;

public interface ResourceDao {

	public void saveResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public Resource loadResourceById(long resourceId);

	public void deleteResource(Resource resource);
	
}
