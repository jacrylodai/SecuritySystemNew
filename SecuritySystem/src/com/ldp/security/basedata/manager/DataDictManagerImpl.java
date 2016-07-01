package com.ldp.security.basedata.manager;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.basedata.dao.DataDictDao;
import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.util.database.ParameterObject;

public class DataDictManagerImpl extends AbstractManager<DataDict> 
	implements DataDictManager{
	
	private DataDictDao dataDictDao;

	public void setDataDictDao(DataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}

	public DataDict loadDataDictById(String dataDictId) {
		
		return dataDictDao.loadDataDictById(dataDictId);
	}
	
	public List<DataDict> getDataDictListByCategory(String dataDictCategory){
		
		String hql = 
			"from DataDict dataDict" +
			" where dataDict.dataDictCategory=:dataDictCategory" +
			" order by dataDict.dataDictId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("dataDictCategory",dataDictCategory));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

}
