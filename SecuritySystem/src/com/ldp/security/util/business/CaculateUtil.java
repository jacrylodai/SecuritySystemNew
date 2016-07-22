package com.ldp.security.util.business;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 计算辅助类
 * @author Administrator
 *
 */
public class CaculateUtil {

	/**
	 * 把源list里的数值加到目标list，
	 * @param capacity 容量
	 * @param desDataList 目标list
	 * @param originDataList 源list
	 */
	public static void addDataFromOriginToDes(
			int capacity,List<Integer> desDataList,List<Integer> originDataList){
		
		for(int i=0;i<capacity;i++){
			int tempValue = desDataList.get(i);
			tempValue += originDataList.get(i);
			desDataList.set(i, tempValue);
		}		
	}
	
	/**
	 * 对指定的list进行初始化
	 * @param capacity
	 * @param dataList
	 */
	public static void initialDataList(int capacity,List<Integer> dataList){

		for(int i=0;i<capacity;i++){
			dataList.add(0);
		}
	}
	
	/**
	 * 把指定list里的所有数据加总
	 * @param dataList
	 * @return
	 */
	public static int addAllDataFromList(List<Integer> dataList){
		
		int result = 0;
		for(Integer data:dataList){
			result += data;
		}
		return result;
	}
	
	/**
	 * 把指定list里的所有数据加总
	 * @param dataList
	 * @return
	 */
	public static long addAllDataFromListIntoLong(List<Integer> dataList){
		
		long result = 0;
		for(Integer data:dataList){
			result += data;
		}
		return result;
	}
	
	/**
	 * 计算numA除以numB的万分比
	 * @param numA
	 * @param numB
	 * @return
	 */
	public static String caculateNumAOnNumB(long numA,long numB){
		
		if(numB == 0){
			return "0";
		}else{
			double numADouble = numA;
			double result = numADouble*10000/numB;
			DecimalFormat decimalF = new DecimalFormat("#.0000");
			String resultStr = decimalF.format(result);
			return resultStr;
		}
	}
	
}
