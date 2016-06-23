package com.ldp.security.util.business.excel.securityFormImport;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 安检查危仪 位置信息，位于表中客售填报区域
 * @author Administrator
 *
 */
public class CheckDangerousMachinePosition {

	/**
	 * 起始index，是从上一个位置信息来计算
	 */
	protected static final int CHECK_DANGEROUS_MACHINE_ROW = 
		SquareCheckPosition.SQUARE_CHECK_ROW + SquareCheckPosition.SQUARE_CHECK_HEIGHT;
	
	protected static final int CHECK_DANGEROUS_MACHINE_HEIGHT = 12;

	/**
	 * 安检查危仪检查人数，起始列index
	 */
	protected static final int SECU_MACH_CHECK_NUM_FIRST_COL = 1;

	/**
	 * 安检查危仪检查人数，起始行index
	 */
	protected static final int SECU_MACH_CHECK_NUM_FIRST_ROW = 
		CHECK_DANGEROUS_MACHINE_ROW+4;

	/**
	 * 
	 */
	protected static final int SECU_MACH_CHECK_NUM_COL_DISTANCE = 2;

	/**
	 * 
	 */
	protected static final int SECU_MACH_CHECK_NUM_ROW_DISTANCE = 1;
	
	/**
	 * 得到安检查危仪的位置信息列表
	 * @param securityMachineMaxCount 安检查危仪的最大数量
	 * @return
	 */
	protected static List<ContentPosition> getSecurityMachineCheckNumPositionList(
			int securityMachineMaxCount){
		
		List<ContentPosition> securityMachineCheckNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<securityMachineMaxCount;i++){
			
			int colIndex = i%2;
			int rowIndex = i/2;
			int currCol = SECU_MACH_CHECK_NUM_FIRST_COL 
				+ colIndex*SECU_MACH_CHECK_NUM_COL_DISTANCE;
			int currRow = SECU_MACH_CHECK_NUM_FIRST_ROW + 
				rowIndex*SECU_MACH_CHECK_NUM_ROW_DISTANCE;
			
			securityMachineCheckNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return securityMachineCheckNumPositionList;
	}
	
}
