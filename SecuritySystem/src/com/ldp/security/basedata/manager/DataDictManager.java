package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.DataDict;

public interface DataDictManager {

	public DataDict loadDataDictById(String dataDictId);

	public List<DataDict> getDataDictListByCategory(String dataDictCategory);
		
}
