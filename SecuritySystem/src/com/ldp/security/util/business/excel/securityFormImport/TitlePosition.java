package com.ldp.security.util.business.excel.securityFormImport;

import com.ldp.security.util.business.excel.common.ContentPosition;

/**
 * 标题位置信息
 * @author Administrator
 *
 */
public class TitlePosition {

	/**
	 * 起始行index
	 */
	protected static final int TITLE_ROW = 0;
	
	/**
	 * 整个标题内容的高度，从“反恐报表导入”一直到“安检查危”的距离
	 */
	protected static final int TITLE_HEIGHT = 9;
	
	/**
	 * 上报单位名称
	 */
	protected static final ContentPosition REPORT_DEPARTMENT = 
		new ContentPosition(1,TITLE_ROW+2);

	/**
	 * 上报单位编码
	 */
	protected static final ContentPosition REPORT_DEPARTMENT_ID = 
		new ContentPosition(1,TITLE_ROW+3);

	/**
	 * 起始日期-年
	 */
	protected static final ContentPosition START_DATE_YEAR = 
		new ContentPosition(1,TITLE_ROW+5);

	/**
	 * 起始日期-月
	 */
	protected static final ContentPosition START_DATE_MONTH = 
		new ContentPosition(2,TITLE_ROW+5);

	/**
	 * 起始日期-日
	 */
	protected static final ContentPosition START_DATE_DAY = 
		new ContentPosition(3,TITLE_ROW+5);

	/**
	 * 终止日期-年
	 */
	protected static final ContentPosition END_DATE_YEAR = 
		new ContentPosition(1,TITLE_ROW+6);

	/**
	 * 终止日期-月
	 */
	protected static final ContentPosition END_DATE_MONTH = 
		new ContentPosition(2,TITLE_ROW+6);

	/**
	 * 终止日期-日
	 */
	protected static final ContentPosition END_DATE_DAY = 
		new ContentPosition(3,TITLE_ROW+6);
}
