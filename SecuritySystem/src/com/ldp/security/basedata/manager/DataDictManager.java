package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.DataDict;

public interface DataDictManager {

	public DataDict loadDataDictById(String dataDictId);

	/**
	 * 根据条件，取得数据字典列表
	 * @param dataDictCategory 数据字典类别
	 * @return
	 */
	public List<DataDict> getDataDictListByCategory(String dataDictCategory);
		
}
