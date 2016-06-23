package com.ldp.security.util.business.excel.securityFormImport;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 安检查危位置信息，位于表中公共区域
 * @author Administrator
 *
 */
public class CheckDangerousPosition {

	/**
	 * 起始index，是从上一个位置信息来计算
	 */
	protected static final int CHECK_DANGEROUS_ROW = 
		TitlePosition.TITLE_ROW + TitlePosition.TITLE_HEIGHT;
	
	protected static final int CHECK_DANGEROUS_HEIGHT = 21;
	
	protected static final ContentPosition SECURITY_MACHINE_TROUBLE_NUM = 
		new ContentPosition(1,CHECK_DANGEROUS_ROW+4);
	
	protected static final int CHECK_DANGEROUS_OBJECT_NUM_COL = 5;

	protected static final int CHECK_DANGEROUS_OBJECT_NUM_FIRST_ROW = 
		CHECK_DANGEROUS_ROW+8;
	
	protected static final int CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE = 1;
	
	protected static List<ContentPosition> getCheckDangerousObjectNumPositionList(
			int dangerousObjectItemCount){
		
		List<ContentPosition> checkDangerousObjectNumPositionList = 
			new ArrayList<ContentPosition>();
		for(int i=0;i<dangerousObjectItemCount;i++){
			
			int rowIndex = i;
			
			int currCol = CHECK_DANGEROUS_OBJECT_NUM_COL;
			int currRow = CHECK_DANGEROUS_OBJECT_NUM_FIRST_ROW + 
				rowIndex*CHECK_DANGEROUS_OBJECT_NUM_ROW_DISTANCE;
			
			checkDangerousObjectNumPositionList.add(new ContentPosition(currCol,currRow));
		}
		return checkDangerousObjectNumPositionList;
	}
}
