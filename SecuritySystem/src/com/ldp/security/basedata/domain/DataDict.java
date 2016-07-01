package com.ldp.security.basedata.domain;

/**
 * 数据字典类
 * @author jacrylodai
 *
 */
public class DataDict {

	//数据字典id
	private String dataDictId;

	//数据字典名称
	private String dataDictName;
	
	//数据字典类别
	private String dataDictCategory;

	public String getDataDictId() {
		return dataDictId;
	}

	public void setDataDictId(String dataDictId) {
		this.dataDictId = dataDictId;
	}

	public String getDataDictName() {
		return dataDictName;
	}

	public void setDataDictName(String dataDictName) {
		this.dataDictName = dataDictName;
	}

	public String getDataDictCategory() {
		return dataDictCategory;
	}

	public void setDataDictCategory(String dataDictCategory) {
		this.dataDictCategory = dataDictCategory;
	}
	
}
