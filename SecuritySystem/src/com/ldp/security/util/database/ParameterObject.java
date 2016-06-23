package com.ldp.security.util.database;

import java.util.List;

/**
 * 变量名-变量值类，用于hql语句设值
 * @author Administrator
 *
 */
public class ParameterObject {

	//变量名
	private String parameterName;
	
	//变量值
	private Object parameterValue;

	public ParameterObject(String parameterName, Object parameterValue) {
		super();
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Object getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * 根据paraObjList里条件参数的数量来判断，是否是hql语句中第一次条件出现
	 * @param paraObjList
	 * @return
	 */
	public static String getWhereOrAndByParaObjListSize(List<ParameterObject> paraObjList){
		
		if(paraObjList.size() == 0){
			return " where";
		}else{
			return " and";
		}
	}
	
}
