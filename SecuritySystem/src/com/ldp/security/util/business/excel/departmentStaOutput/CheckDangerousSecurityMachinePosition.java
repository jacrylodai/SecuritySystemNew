package com.ldp.security.util.business.excel.departmentStaOutput;

import com.ldp.security.util.business.excel.common.ContentPosition;

public class CheckDangerousSecurityMachinePosition {

	protected static final int CHECK_DANGEROUS_SECURITY_MACHINE_ROW = 
		JianpiaoPosition.JIANPIAO_ROW + JianpiaoPosition.JIANPIAO_HEIGHT;
		
	protected static final int SECU_MACH_CHECK_INFO_FIRST_COL = 0;

	protected static final int SECU_MACH_CHECK_INFO_FIRST_ROW = 
		CHECK_DANGEROUS_SECURITY_MACHINE_ROW+5;
	
	protected static final int SECU_MACH_CHECK_INFO_COL_DISTANCE = 3;

	protected static final int SECU_MACH_CHECK_INFO_ROW_DISTANCE = 1;
		
	protected static ContentPosition getSecurityMachineCheckInfoNamePosition(
			int currIndex){
		
		int colIndex = currIndex%2;
		int rowIndex = currIndex/2;
		int currCol = SECU_MACH_CHECK_INFO_FIRST_COL 
			+ colIndex*SECU_MACH_CHECK_INFO_COL_DISTANCE;
		int currRow = SECU_MACH_CHECK_INFO_FIRST_ROW + 
			rowIndex*SECU_MACH_CHECK_INFO_ROW_DISTANCE;
		
		return new ContentPosition(currCol,currRow);
	}

	protected static ContentPosition getSecurityMachineCheckInfoCheckNumPosition(
			int currIndex){
		
		int colIndex = currIndex%2;
		int rowIndex = currIndex/2;
		int currCol = SECU_MACH_CHECK_INFO_FIRST_COL 
			+ colIndex*SECU_MACH_CHECK_INFO_COL_DISTANCE +2;
		int currRow = SECU_MACH_CHECK_INFO_FIRST_ROW + 
			rowIndex*SECU_MACH_CHECK_INFO_ROW_DISTANCE;
		
		return new ContentPosition(currCol,currRow);
	}
	
}
