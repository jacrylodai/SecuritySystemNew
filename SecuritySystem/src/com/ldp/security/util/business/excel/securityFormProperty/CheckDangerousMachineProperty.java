package com.ldp.security.util.business.excel.securityFormProperty;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 安检查危仪 位置信息，位于表中客售填报区域
 * @author Administrator
 *
 */
public class CheckDangerousMachineProperty {

	public static final String CHECK_DANGEROUS_MACHINE_BASE_POSITION_KEY = 
		"CHECK_DANGEROUS_MACHINE_BASE_POSITION";

	public static final String SECU_MACH_CHECK_NUM_FIRST = "SECU_MACH_CHECK_NUM_FIRST";

	private static final int SECU_MACH_CHECK_NUM_COL_DISTANCE = 2;

	private static final int SECU_MACH_CHECK_NUM_ROW_DISTANCE = 1;
	
	/**
	 * 得到安检查危仪的位置信息列表
	 * @param securityMachineMaxCount 安检查危仪的最大数量
	 * @return
	 */
	public static List<ContentPosition> getSecurityMachineCheckNumPositionList(
			int securityMachineMaxCount,int itemFirstCol,int itemFirstRow){
		
		List<ContentPosition> securityMachineCheckNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<securityMachineMaxCount;i++){
			
			int colIndex = i%2;
			int rowIndex = i/2;
			int currCol = itemFirstCol 
				+ colIndex*SECU_MACH_CHECK_NUM_COL_DISTANCE;
			int currRow = itemFirstRow + 
				rowIndex*SECU_MACH_CHECK_NUM_ROW_DISTANCE;
			
			securityMachineCheckNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return securityMachineCheckNumPositionList;
	}
	
}
